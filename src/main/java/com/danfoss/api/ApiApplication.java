package com.danfoss.api;

import com.danfoss.api.Utils.ApiConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.filter.CorsFilter;

@SpringBootApplication
@Configuration
public class ApiApplication {

    @Bean
    public FilterRegistrationBean<CorsFilter> simpleCorsFilter() {
        FilterRegistrationBean<CorsFilter> bean = ApiConfig.corsFilterApi();
        bean.setOrder(Ordered.HIGHEST_PRECEDENCE);
        return bean;
    }

    public static void main(String[] args) {
        SpringApplication.run(ApiApplication.class, args);
    }

}
