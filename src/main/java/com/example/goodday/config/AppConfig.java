package com.example.goodday.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@ComponentScan
public class AppConfig implements WebMvcConfigurer {
//
//    @Override
//    public void configureViewResolvers(ViewResolverRegistry registry) {
//        registry.jsp("/WEB-INF/jsp/", ".jsp");
//    }
//

    @Override
    public void configureDefaultServletHandling(
          DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
  }
}
