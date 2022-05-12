package com.example.cscmusic.config;


import com.example.cscmusic.exception.RestAuthenticationEntryPoint;
import com.example.cscmusic.filter.JwtAuthenticationFilter;
import com.example.cscmusic.filter.JwtAuthorizationFilter;
import com.example.cscmusic.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    public static final String SECRET = "cscMusic"; //密钥
    public static final long EXPIRATION_TIME = 864000000; // 10 days
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization"; // jwt会放到header里边
    public static final String SING_UP_URL = "/users/";

    UserService userService;

    RestAuthenticationEntryPoint restAuthenticationEntryPoint;


    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setRestAuthenticationEntryPoint(RestAuthenticationEntryPoint restAuthenticationEntryPoint) {
        this.restAuthenticationEntryPoint = restAuthenticationEntryPoint;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable()//开启跨域请求，关闭csrf验证
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, SING_UP_URL).permitAll() //增加request 白名单
                .anyRequest().authenticated() //剩余 其他的 request 都需要进行鉴权
                .and()
                .addFilter(new JwtAuthenticationFilter(authenticationManager())) // 用户名 密码 鉴权
                .addFilter(new JwtAuthorizationFilter(authenticationManager())) // token 鉴权
                .exceptionHandling()
                .authenticationEntryPoint(restAuthenticationEntryPoint)
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS); //session 更改为无状态
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService);
    }
}
