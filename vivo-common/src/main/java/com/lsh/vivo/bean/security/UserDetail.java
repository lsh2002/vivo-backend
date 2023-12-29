package com.lsh.vivo.bean.security;

import com.lsh.vivo.enumerate.CommonStatusEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Objects;

/**
 * @author AdolphLv
 * @version 1.0.0
 * 2023/9/21 11:24
 */
@Data
@EqualsAndHashCode
public class UserDetail implements UserDetails {

    private String id;

    private String password;

    private String username;

    private String nickname;

    private String status;

    private LocalDate effectiveDate;

    private Collection<? extends GrantedAuthority> authorities;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    /**
     * 账号是否过期
     *
     * @return true-正常 false-过期
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * 账号是否锁定
     *
     * @return true-正常 false-锁定
     */
    @Override
    public boolean isAccountNonLocked() {
        return !Objects.equals(status, CommonStatusEnum.S.name());
    }

    /**
     * 密码是否已过期
     *
     * @return true-正常 false-过期
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * 是否可用
     *
     * @return true-可用 false-不可用
     */
    @Override
    public boolean isEnabled() {
        return Objects.equals(status, CommonStatusEnum.I.name());
    }
}
