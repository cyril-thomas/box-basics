package com.simplyct.woddojo.config;

import com.simplyct.woddojo.servlets.IgCallbackServlet;
import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by cyril on 7/6/15.
 */
@Configuration
public class ServletConfig {

    @Bean
    public ServletRegistrationBean servletRegistrationBean() {
        return new ServletRegistrationBean(new IgCallbackServlet(), "/callbackig/*");
    }
}
