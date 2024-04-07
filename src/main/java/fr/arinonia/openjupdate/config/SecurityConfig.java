package fr.arinonia.openjupdate.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final SetupCheckFilter setupCheckFilter;

    public SecurityConfig(final SetupCheckFilter setupCheckFilter) {
        this.setupCheckFilter = setupCheckFilter;
    }

    @Bean
    public FilterRegistrationBean<SetupCheckFilter> setupCheckFilterRegistration() {
        final FilterRegistrationBean<SetupCheckFilter> filterRegistrationBean = new FilterRegistrationBean<>();
        filterRegistrationBean.setFilter(this.setupCheckFilter);
        filterRegistrationBean.addUrlPatterns("/*");
        return filterRegistrationBean;
    }
    @Bean
    public SecurityFilterChain securityFilterChain(final HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeRequests(authorise -> authorise.requestMatchers( "/setup/**","/login", "/css/**", "/images/**", "/js/**").permitAll()//TODO @Deprecated have to look how to replace that
                        .anyRequest().authenticated())
                .formLogin(httpSecurityFormLoginConfigurer -> {
                    httpSecurityFormLoginConfigurer.loginPage("/login");
                    httpSecurityFormLoginConfigurer.loginProcessingUrl("/perform_login");
                    httpSecurityFormLoginConfigurer.defaultSuccessUrl("/home", true);
                    httpSecurityFormLoginConfigurer.failureUrl("/login?error=true");
                })
                .logout(httpSecurityLogoutConfigurer -> {
                    httpSecurityLogoutConfigurer.logoutUrl("/perform_logout");
                    httpSecurityLogoutConfigurer.deleteCookies("JSESSIONID");
                })
                .build();
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
