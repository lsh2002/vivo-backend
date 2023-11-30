package com.lsh.vivo.util;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.lionsoul.ip2region.xdb.Searcher;
import org.springframework.util.FileCopyUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;
import java.util.Objects;

/**
 * IpUtils类
 *
 * @author AdolphLv
 * @version 1.0.0
 * 2023/9/22 21:49
 */
@Slf4j
public class IpUtils {
    private static final String IP_UTILS_FLAG = ",";
    private static final String UNKNOWN = "unknown";
    private static final String LOCALHOST_IP = "0:0:0:0:0:0:0:1";
    private static final String LOCALHOST_IP1 = "127.0.0.1";

    private static Searcher searcher = null;

    /**
     *   在服务启动时加载 ip2region.db 到内存中
     *   解决打包jar后找不到 ip2region.db 的问题
     */
    static {
        try {
            InputStream ris = IpUtils.class.getResourceAsStream("/ip2region/ip2region.xdb");
            byte[] dbBinStr = FileCopyUtils.copyToByteArray(ris);
            searcher = Searcher.newWithBuffer(dbBinStr);
            //注意：不能使用文件类型，打成jar包后，会找不到文件
            log.debug("缓存成功！！！！");
        } catch (IOException e) {
            log.error("解析ip地址失败,无法创建搜索器", e);
            throw new RuntimeException(e);
        }
    }

    /**
     * 获取IP地址
     * <p>
     * 使用Nginx等反向代理软件， 则不能通过request.getRemoteAddr()获取IP地址
     * 如果使用了多级反向代理的话，X-Forwarded-For的值并不止一个，而是一串IP地址，X-Forwarded-For中第一个非unknown的有效IP字符串，则为真实IP地址
     */
    public static String getIpAddr(HttpServletRequest request) {
        String ip = null;
        try {
            if (log.isDebugEnabled()){
                Enumeration<String> headerNames = request.getHeaderNames();
                while (headerNames.hasMoreElements()){
                    String headerName = headerNames.nextElement();
                    log.debug("HttpServletRequest.header['{}']={}", headerName, request.getHeader(headerName));
                }
            }
            //以下两个获取在k8s中，将真实的客户端IP，放到了x-Original-Forwarded-For。而将WAF的回源地址放到了 x-Forwarded-For了。
            ip = request.getHeader("X-Original-Forwarded-For");
            if (StringUtils.isEmpty(ip) || UNKNOWN.equalsIgnoreCase(ip)) {
                ip = request.getHeader("X-Forwarded-For");
            }
            //获取nginx等代理的ip
            if (StringUtils.isEmpty(ip) || UNKNOWN.equalsIgnoreCase(ip)) {
                ip = request.getHeader("x-forwarded-for");
            }
            if (StringUtils.isEmpty(ip) || UNKNOWN.equalsIgnoreCase(ip)) {
                ip = request.getHeader("Proxy-Client-IP");
            }
            if (StringUtils.isEmpty(ip) || UNKNOWN.equalsIgnoreCase(ip)) {
                ip = request.getHeader("WL-Proxy-Client-IP");
            }
            if (StringUtils.isEmpty(ip) || UNKNOWN.equalsIgnoreCase(ip)) {
                ip = request.getHeader("HTTP_CLIENT_IP");
            }
            if (StringUtils.isEmpty(ip) || UNKNOWN.equalsIgnoreCase(ip)) {
                ip = request.getHeader("HTTP_X_FORWARDED_FOR");
            }
            if (StringUtils.isEmpty(ip) || UNKNOWN.equalsIgnoreCase(ip)) {
                ip = request.getHeader("X-Real-IP");
            }

            //兼容k8s集群获取ip
            if (StringUtils.isEmpty(ip) || UNKNOWN.equalsIgnoreCase(ip)) {
                ip = request.getRemoteAddr();
            }
        } catch (Exception e) {
            log.error("IPUtils ERROR ", e);
        }
        //使用代理，则获取第一个IP地址
        if (!StringUtils.isEmpty(ip) && ip.indexOf(IP_UTILS_FLAG) > 0) {
            ip = ip.substring(0, ip.indexOf(IP_UTILS_FLAG));
        }
        if (Objects.equals(LOCALHOST_IP, ip)){
            ip = LOCALHOST_IP1;
        }
        return ip;
    }

    public static InetAddress getLocalHostExactAddress() {
        try {
            InetAddress candidateAddress = null;

            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            while (networkInterfaces.hasMoreElements()) {
                NetworkInterface networkInterface = networkInterfaces.nextElement();
                // 该网卡接口下的ip会有多个，也需要一个个的遍历，找到自己所需要的
                //是否回调接口
                if (networkInterface.isLoopback()) {
                    continue;
                }
                //排除虚拟机ip
                if (networkInterface.isVirtual()) {
                    continue;
                }
                //是否正常在工作
                if (!networkInterface.isUp()) {
                    continue;
                }
                if (networkInterface.isPointToPoint()) {
                    continue;
                }
                //只读取WiFi或者有线地址
                if (!networkInterface.getDisplayName().contains("Intel")
                        && !networkInterface.getDisplayName().contains("Realtek")) {
                    continue;
                }
                for (Enumeration<InetAddress> inetAddrs = networkInterface.getInetAddresses(); inetAddrs.hasMoreElements(); ) {
                    InetAddress inetAddr = inetAddrs.nextElement();
                    // 排除loopback回环类型地址（不管是IPv4还是IPv6 只要是回环地址都会返回true）
                    if (inetAddr.isLoopbackAddress() || inetAddr.isAnyLocalAddress() || inetAddr.isMulticastAddress()) {
                        continue;
                    }

                    if (inetAddr.isSiteLocalAddress()) {
                        // 如果是site-local地址，就是它了 就是我们要找的
                            return inetAddr;
                    }

                    // 若不是site-local地址 那就记录下该地址当作候选
                    if (candidateAddress == null) {
                        candidateAddress = inetAddr;
                        break;
                    }
                }
            }
            // 如果出去loopback回环地之外无其它地址了，那就回退到原始方案吧
            return candidateAddress == null ? InetAddress.getLocalHost() : candidateAddress;
        } catch (Exception e) {
            log.error("", e);
        }
        return null;
    }

    /**
     * 根据ip获取 城市信息
     *
     * @param ipAddress
     * @return
     */
    public static String getCityInfo(String ipAddress) {
        try {
            return searcher.search(ipAddress);
        } catch (Exception e) {
            log.error("搜索:{}失败", ipAddress, e);
        }
        return null;
    }

    /**
     * 根据ip2region解析ip地址
     *
     * @param ip ip地址
     * @return 解析后的ip地址信息
     */
    public static String getIp2region(String ip) {
        if (searcher == null) {
            log.error("Error: DbSearcher is null");
            return "";
        }

        try {
            String ipInfo = searcher.search(ip);
            if (!StringUtils.isEmpty(ipInfo)) {
                ipInfo = ipInfo.replace("|0", "");
                ipInfo = ipInfo.replace("0|", "");
            }
            return ipInfo == null ? "" : ipInfo;
        } catch (Exception e) {
            log.error("getIp2region error,ip={}", ip, e);
        }
        return "";
    }

}
