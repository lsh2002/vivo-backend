package com.lsh.vivo.security.configuration;

import com.lsh.vivo.security.filter.RequestFilter;
import com.lsh.vivo.security.filter.TokenAuthenticationFilter;
import com.lsh.vivo.security.filter.VerifyCaptchaFilter;
import com.lsh.vivo.security.handle.*;
import com.lsh.vivo.security.provider.CustomPasswordEncoder;
import jakarta.annotation.Resource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.savedrequest.NullRequestCache;

/**
 * security配置
 *
 * @author AdolphLv
 * @version 1.0.0
 * 2023/9/17 22:15
 */
@Configuration
public class SecurityConfiguration {

    @Resource
    private UserDetailsService userService;
    @Resource
    private CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;
    @Resource
    private CustomAuthenticationFailureHandler customAuthenticationFailureHandler;
    @Resource
    private TokenAuthenticationFilter tokenAuthenticationFilter;

    @Resource
    private VerifyCaptchaFilter verifyCaptchaFilter;

    @Resource
    private RequestFilter requestFilter;

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider(passwordEncoder());
        daoAuthenticationProvider.setUserDetailsService(userService);
        return daoAuthenticationProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorize -> authorize
                        .anyRequest().authenticated()
                )
                .cors(AbstractHttpConfigurer::disable)
                .csrf(AbstractHttpConfigurer::disable)
                .httpBasic(Customizer.withDefaults())
                .anonymous(AbstractHttpConfigurer::disable)
                .formLogin(t -> t
                        .successHandler(customAuthenticationSuccessHandler)
                        .failureHandler(customAuthenticationFailureHandler)
                )
                .addFilterBefore(tokenAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(verifyCaptchaFilter, TokenAuthenticationFilter.class)
                .addFilterBefore(requestFilter, VerifyCaptchaFilter.class)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .requestCache(cache -> cache
                        .requestCache(new NullRequestCache())
                ).exceptionHandling(exceptions -> exceptions
                        .authenticationEntryPoint(new SecurityAuthenticationEntryPoint())
                        .accessDeniedHandler(new SecurityAccessDeniedHandler())
                )
                .logout(out -> out.logoutSuccessHandler(new CustomLogoutSuccessHandler())
                        .invalidateHttpSession(true)
                        .clearAuthentication(true)
                        .addLogoutHandler((request, response, authentication) -> SecurityContextHolder.clearContext())
                );
        return http.build();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return web -> web.ignoring().requestMatchers(
                "/public/*", "/error"
        );
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new CustomPasswordEncoder();
    }
}
