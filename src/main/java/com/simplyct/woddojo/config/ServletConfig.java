package com.simplyct.woddojo.config;

import com.simplyct.woddojo.servlets.IgCallbackServlet;
import org.springframework.boot.context.embedded.MultipartConfigFactory;
import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.MultipartConfigElement;

/**
 * Created by cyril on 7/6/15.
 */
@Configuration
public class ServletConfig {

    @Bean
    public ServletRegistrationBean servletRegistrationBean() {
        return new ServletRegistrationBean(new IgCallbackServlet(), "/callbackig/*");
    }

    @Bean
    MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        factory.setMaxFileSize("500MB");
        factory.setMaxRequestSize("500MB");
        return factory.createMultipartConfig();
    }

}
