package com.siripireddy.azureadpractice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@RequestMapping("/api")
@EnableCaching
public class AzureadpracticeApplication {

    @GetMapping("/login")
    public String getName() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            System.out.println("Name "+ authentication.getName());
            System.out.println("principle: "+authentication.getPrincipal());
            System.out.println("authorities: "+authentication.getAuthorities());
            System.out.println("details: "+authentication.getDetails());

        }

        return "Azure AD Practice Application";
    }

    public static void main(String[] args) {
        SpringApplication.run(AzureadpracticeApplication.class, args);
    }

}
