package com.sbogdanschi.springboot.config;

import com.sbogdanschi.springboot.service.impl.CustomDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import static com.sbogdanschi.springboot.util.PageUrl.ACCESS_DENIED;
import static com.sbogdanschi.springboot.util.PageUrl.Admin.ADMIN;
import static com.sbogdanschi.springboot.util.PageUrl.Admin.ADMIN_SUB_DIRECTORY;
import static com.sbogdanschi.springboot.util.PageUrl.ERROR_PAGE;
import static com.sbogdanschi.springboot.util.PageUrl.INDEX;
import static com.sbogdanschi.springboot.util.PageUrl.User.*;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private CustomDetailsService customDetailsService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    private static final String USERNAME_FIELD = "username";

    private static final String PASSWORD_PARAMETER = "password";

    @Override
    protected void configure(AuthenticationManagerBuilder auth)
            throws Exception {
        auth
                .userDetailsService(customDetailsService)
                .passwordEncoder(bCryptPasswordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.
                authorizeRequests()
                .antMatchers(INDEX).permitAll()
                .antMatchers(LOGIN).permitAll()
                .antMatchers(REGISTRATION).permitAll()
                .antMatchers(ADMIN_SUB_DIRECTORY).hasAuthority(ADMIN).anyRequest()
                .authenticated()
                .and()
                .httpBasic()
                .and()
                .csrf().disable().formLogin()
                .loginPage(LOGIN).failureUrl(LOGIN_ERROR_PAGE)
                .defaultSuccessUrl(ADMIN)
                .usernameParameter(USERNAME_FIELD)
                .passwordParameter(PASSWORD_PARAMETER)
                .and().logout()
                .logoutRequestMatcher(new AntPathRequestMatcher(LOGOUT))
                .logoutSuccessUrl(INDEX)
                .and().exceptionHandling()
                .accessDeniedPage(ACCESS_DENIED)
                .accessDeniedPage(ERROR_PAGE);

    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web
                .ignoring()
                .antMatchers("/resources/**", "/static/**", "/css/**", "/js/**", "/images/**");
    }

    @Bean
    @Qualifier("bCryptPasswordEncoder")
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}