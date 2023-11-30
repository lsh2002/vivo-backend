package com.lsh.vivo.util;

import com.lsh.vivo.bean.constant.GlobalConstant;
import com.lsh.vivo.bean.security.UserDetail;
import com.lsh.vivo.jwt.JwtProperties;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * JwtUtils类
 *
 * @author AdolphLv
 * @version 1.0.0
 * 2023/9/23 15:05
 */
@Slf4j
@Component
public class JwtUtils {


    @Resource
    private JwtProperties jwtProperties;

    private volatile byte[] encodeBytes;

    private byte[] getEncodeBytes(){
        if (encodeBytes == null){
            synchronized (JwtUtils.class){
                if (encodeBytes == null){
                    encodeBytes = Decoders.BASE64.decode(jwtProperties.getSecret());
                }
            }
        }
        return encodeBytes;
    }

    /**
     * 生成jwt token
     *
     * @param userDetail 用户登录信息
     * @return token值
     */
    public String generateToken(UserDetail userDetail, String ip) {
        if (userDetail == null) {
            return null;
        }
        byte[] bytes = getEncodeBytes();
        SecretKey key = new SecretKeySpec(bytes, SignatureAlgorithm.HS256.getJcaName());
        String authorities = userDetail.getAuthorities() == null ? "" : userDetail.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.joining(","));
        return Jwts.builder().setSubject(jwtProperties.getSubject())
                .claim(GlobalConstant.HTTP_USER_ID, userDetail.getId())
                .claim(GlobalConstant.HTTP_USER, userDetail.getName())
                .claim("nickname", userDetail.getNickname())
                .claim("authorities", (StringUtils.isNotBlank(authorities) ? GzipUtils.compress(authorities) : ""))
                .claim(GlobalConstant.HTTP_REQUEST_IP, ip)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + jwtProperties.getExpireTime()))
                .signWith(key, SignatureAlgorithm.HS256).compact();
    }

    /**
     * 生成客户端token
     */
    public String generateClientToken() {
        byte[] bytes = getEncodeBytes();
        SecretKey key = new SecretKeySpec(bytes, SignatureAlgorithm.HS256.getJcaName());
        String ip = (String) Objects.requireNonNull(RequestContextHolder.getRequestAttributes()).getAttribute(GlobalConstant.HTTP_REQUEST_IP, RequestAttributes.SCOPE_REQUEST);
        return Jwts.builder().setSubject(jwtProperties.getSubject())
                .claim(GlobalConstant.HTTP_REQUEST_IP, ip)
                .claim("authorities", GzipUtils.compress("message:view"))
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + jwtProperties.getExpireTime()))
                .signWith(key, SignatureAlgorithm.HS256).compact();
    }

    /**
     * 解析token字符串中的加密信息【加密算法&加密密钥】, 提取所有声明的方法
     * @param token token值
     * @return
     */
    public Claims extractAllClaims(String token){
        try {
            return Jwts
                    .parserBuilder()
                    // 获取alg开头的信息
                    .setSigningKey(getSignInKey())
                    .build()
                    // 解析token字符串
                    .parseClaimsJws(token)
                    .getBody();
        }catch (Exception e) {
            if (log.isWarnEnabled()){
                log.warn("Valid token=[{}] error!", token, e);
            }
        }
        return null;
    }

    /**
     * 获取token中的权限值
     * @param claims
     * @return
     */
    public List<String> getAuthorities(Claims claims){
        String authorities = null;
        try {
            authorities = (String) claims.get("authorities");
            authorities = StringUtils.isNotBlank(authorities) ? GzipUtils.uncompress(authorities) : "";
            return !"".equals(authorities) ? Arrays.stream(authorities.split(",")).toList() : new ArrayList<>();
        }catch (Exception e) {
            if (log.isWarnEnabled()){
                log.warn("authorities=[{}] error!", authorities, e);
            }
        }
        return null;
    }


    /**
     * 获取token中的用户ID
     * @param claims
     * @return
     */
    public String getUserId(Claims claims){
        return (String) claims.get(SecurityUtils.getUserId());
    }

    /**
     * 获取token中的用户ID
     * @param claims
     * @return
     */
    public String getUserName(Claims claims){
        return (String) claims.get(SecurityUtils.getUsername());
    }

    /**
     * 获取签名密钥的方法
     * @return 基于指定的密钥字节数组创建用于HMAC-SHA算法的新SecretKey实例
     */
    private Key getSignInKey() {
        byte[] keyBytes = getEncodeBytes();
        return Keys.hmacShaKeyFor(keyBytes);
    }

    /**
     * 解析token字符串中的权限信息
     * @param token token值
     * @return
     */
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        try{
            final Claims claims = extractAllClaims(token);
            return claims == null ? null : claimsResolver.apply(claims);
        } catch (Exception e) {
            if (log.isWarnEnabled()){
                log.warn("Verify token [{}] error!!!", token, e);
            }
        }
        return null;
    }

    /**
     * 从token中解析出username
     * @param token token值
     * @return
     */
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    /**
     * 判断token是否过期
     * @param token token值
     * @return token有效性
     */
    public boolean isTokenValid(String token, String ip) {
        // 从token中获取用户名
        Claims claims = extractAllClaims(token);
        if (claims == null){
            return false;
        }
        String username = extractUsername(token);
        boolean valid = claims.getExpiration() != null && !claims.getExpiration().before(new Date());
        if (valid){
            String loginIp = (String) claims.get(GlobalConstant.HTTP_REQUEST_IP);
            valid = Objects.equals(ip, loginIp);
        }
        return valid;
    }

    /**
     * 验证token是否过期
     * @param token token值
     * @return true-正常， false-过期
     */
    private boolean isTokenExpired(String token) {
        return getExpiration(token).before(new Date());
    }

    /**
     * 从授权信息中获取token过期时间
     * @param token token值
     * @return 过期时间
     */
    public Date getExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    /**
     * 从授权信息中获取token过期时间
     * @param token token值
     * @return 过期时间
     */
    public String getIp(String token) {
        return extractClaim(token, claim->claim.get("ip", String.class));
    }

    public String getHeaderName(){
        return jwtProperties.getHeaderName();
    }

    public String getTokenPrefix(){
        return jwtProperties.getTokenPrefix();
    }

    public Integer getRefreshTime() {
        return jwtProperties.getRefreshExpireTime();
    }
}
