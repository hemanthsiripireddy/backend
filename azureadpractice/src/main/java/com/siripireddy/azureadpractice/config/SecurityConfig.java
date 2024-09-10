package com.siripireddy.azureadpractice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean

    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {


            http.csrf((csrf)->csrf.disable())
                    .authorizeHttpRequests((requests)->requests.
                            requestMatchers("/documents/test","/documents/post","/api/login").authenticated()
                           );

               // .formLogin(Customizer.withDefaults());
                //.formLogin(Customizer.withDefaults());
      //  http.authorizeHttpRequests()
        return http.build();

    }
}
