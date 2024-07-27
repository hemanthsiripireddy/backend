package com.example.mailservicedemo.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class ProjectSecurityConfig {

    @Bean

    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {

        http.csrf((csrf)->csrf.disable())
                .authorizeHttpRequests((requests)->requests
                       // requestMatchers("/api/v*/registration/**").permitAll()
                        .anyRequest().permitAll())
                .formLogin(Customizer.withDefaults())
                .formLogin(Customizer.withDefaults());
       return http.build();
        // SecurityFilterChain.class.accessFlags()
//        http.authorizeHttpRequests((requests)->requests.anyRequest().denyAll())
//                .formLogin(Customizer.withDefaults())
//                .formLogin(Customizer.withDefaults());
//        return http.build();



    }
    //@Bean
//    public UserDetailsService userDetailsService(DataSource dataSource) {
//        return new JdbcUserDetailsManager(dataSource);
//    }
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
}
