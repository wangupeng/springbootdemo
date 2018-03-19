package com.cars.plat.config;

import nz.net.ultraq.thymeleaf.LayoutDialect;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.templateresolver.UrlTemplateResolver;

/**
 * Created by wangyupeng on 2017/8/19.
 */
//@Configuration
public class WebConfig  extends WebMvcConfigurerAdapter {
    /*@Override
    public void configurePathMatch(PathMatchConfigurer configurer) {
        configurer.setUseSuffixPatternMatch(false)
                .setUseTrailingSlashMatch(true);
    }*/
    @Bean
    public TemplateEngine templateEngine() {
        TemplateEngine templateEngine = new TemplateEngine();
        templateEngine.addDialect(new LayoutDialect());
        return templateEngine;
    }
}
