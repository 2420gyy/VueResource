package com.chen.hellosecurity.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                //允许基于使用HttpServletRequest限制访问
                .authorizeRequests()
                .antMatchers("/", "/home").permitAll()
                .anyRequest().authenticated()
                .and()
                //指定支持基于表单的身份验证。如果未指定FormLoginConfigurer#loginPage(String)，则将生成默认登录页面
                .formLogin()
                .loginPage("/login")
                .permitAll()
                .and()
                //添加退出登录支持。当使用WebSecurityConfigurerAdapter时，这将自动应用。默认情况是，访问URL”/ logout”，使HTTP Session无效来清除用户，清除已配置的任何#rememberMe()身份验证，清除SecurityContextHolder，然后重定向到”/login?success”
                .logout()
                .permitAll();
    }
//     WebSecurityConfig类使用了@EnableWebSecurity注解 ，以启用Spring Security的Web安全支持，
//     并提供Spring MVC集成。它还扩展了WebSecurityConfigurerAdapter，并覆盖了一些方法来设置Web安全配置的一些细节。
//     configure(HttpSecurity)方法定义了哪些URL路径应该被保护，哪些不应该。
//     具体来说，“/”和“/ home”路径被配置为不需要任何身份验证。所有其他路径必须经过身份验证。
//     当用户成功登录时，它们将被重定向到先前请求的需要身份认证的页面。有一个由 loginPage()指定的自定义“/登录”页面，每个人都可以查看它。

//     对于configureGlobal(AuthenticationManagerBuilder) 方法，它将单个用户设置在内存中。
//     该用户的用户名为“user”，密码为“password”，角色为“USER”。
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth
//                .inMemoryAuthentication()
//                .withUser("user").password("password").roles("USER");
                .inMemoryAuthentication()
                .passwordEncoder(new BCryptPasswordEncoder())
                .withUser("user").password(new BCryptPasswordEncoder().encode("password"))
                .roles("USER");
    }
}
