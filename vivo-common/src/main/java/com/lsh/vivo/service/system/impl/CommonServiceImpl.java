package com.lsh.vivo.service.system.impl;

import com.lsh.vivo.bean.constant.GlobalConstant;
import com.lsh.vivo.mapper.system.CommonMapper;
import com.lsh.vivo.service.system.CommonService;
import com.mybatisflex.core.query.QueryChain;
import com.mybatisflex.core.query.QueryWrapper;
import com.mybatisflex.spring.service.impl.ServiceImpl;
import jakarta.annotation.Resource;
import lombok.Getter;
import org.springframework.context.ApplicationContext;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Collection;
import java.util.List;

import static com.mybatisflex.core.query.QueryMethods.number;

/**
 * service基类实现
 *
 * @author AdolphLv
 * @version 1.0.0
 * 2023/9/21
 */
@Getter
public class CommonServiceImpl<M extends CommonMapper<T>, T> extends ServiceImpl<M, T> implements CommonService<T> {

    /**
     * "SELECT *"查询所有列
     */
    private static final String ALL = "SELECT *";

    @Resource
    private ApplicationContext applicationContext;

    @Override
    public boolean existByCondition(String select) {
        return GlobalConstant.SELECT_NUMBER.equals(select);
    }

    @Override
    public List<String> listPermissions(String... prefixs) {
        Collection<? extends GrantedAuthority> authorities = SecurityContextHolder
                .getContextHolderStrategy().getContext()
                .getAuthentication().getAuthorities();
        return authorities.stream().map(GrantedAuthority::getAuthority)
                .filter(item -> {
                    boolean match = false;
                    for (String prefix : prefixs) {
                        match = item.startsWith(prefix);
                        if (match) {
                            break;
                        }
                    }
                    return match;
                }).toList();
    }
}
