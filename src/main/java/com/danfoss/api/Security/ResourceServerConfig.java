package com.danfoss.api.Security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/producto/").authenticated()
                .antMatchers("/usuario/").authenticated()
                .antMatchers("/modelo/").authenticated()
                .antMatchers("/excel/").authenticated()
                .anyRequest().authenticated()
                .and().cors().disable();
    }
}
