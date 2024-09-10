package com.siripireddy.azureadpractice.config;

import org.springframework.cache.concurrent.ConcurrentMapCacheFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.acls.domain.AclAuthorizationStrategyImpl;
import org.springframework.security.acls.domain.ConsoleAuditLogger;
import org.springframework.security.acls.domain.DefaultPermissionGrantingStrategy;
import org.springframework.security.acls.domain.SpringCacheBasedAclCache;
import org.springframework.security.acls.domain.AclAuthorizationStrategy;
import org.springframework.security.acls.jdbc.BasicLookupStrategy;
import org.springframework.security.acls.jdbc.JdbcMutableAclService;
import org.springframework.security.acls.jdbc.LookupStrategy;
import org.springframework.security.acls.model.AclCache;
import org.springframework.security.acls.model.AclService;
import org.springframework.security.acls.model.PermissionGrantingStrategy;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.sql.DataSource;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class AclConfig {

    @Bean
    public LookupStrategy lookupStrategy(DataSource dataSource, AclCache aclCache) {
        return new BasicLookupStrategy(dataSource, aclCache, aclAuthorizationStrategy(), permissionGrantingStrategy());
    }

    @Bean
    public AclService aclService(DataSource dataSource, LookupStrategy lookupStrategy, AclCache aclCache) {
        return new JdbcMutableAclService(dataSource, lookupStrategy, aclCache);
    }

    @Bean
    public AclCache aclCache() {
        // Initialize the cache factory bean
        ConcurrentMapCacheFactoryBean cacheFactoryBean = new ConcurrentMapCacheFactoryBean();
        cacheFactoryBean.setName("aclCache");
        cacheFactoryBean.afterPropertiesSet(); // Initialize the cache

        // Create the ACL cache using the initialized cache
        return new SpringCacheBasedAclCache(
                cacheFactoryBean.getObject(),
                permissionGrantingStrategy(),
                aclAuthorizationStrategy()
        );
        //return new SpringCacheBasedAclCache(new ConcurrentMapCacheFactoryBean().getObject(), permissionGrantingStrategy(), aclAuthorizationStrategy());
    }

    @Bean
    public PermissionGrantingStrategy permissionGrantingStrategy() {
        return new DefaultPermissionGrantingStrategy(new ConsoleAuditLogger());
    }

    @Bean
    public AclAuthorizationStrategy aclAuthorizationStrategy() {
        return new AclAuthorizationStrategyImpl(new SimpleGrantedAuthority("ROLE_ADMIN"));
    }
}

