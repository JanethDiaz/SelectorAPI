package com.danfoss.api;

import com.danfoss.api.Utils.ApiConfig;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;
import org.springframework.web.filter.CorsFilter;


public class ServletInitializer extends SpringBootServletInitializer {
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(ApiApplication.class);
    }


    @Bean
    public FilterRegistrationBean<CorsFilter> simpleCorsFilter() {
        FilterRegistrationBean<CorsFilter> bean = ApiConfig.corsFilterApi();
        bean.setOrder(Ordered.HIGHEST_PRECEDENCE);
        return bean;
    }
}
