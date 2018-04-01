package com.cars.plat.config;

import nz.net.ultraq.thymeleaf.LayoutDialect;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.UrlTemplateResolver;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by wangyupeng on 2017/8/19.
 */
@Configuration
public class WebConfig  extends WebMvcConfigurerAdapter {
    /*@Bean
    public TemplateEngine templateEngine() {
        TemplateEngine templateEngine = new TemplateEngine();
        templateEngine.addDialect(new LayoutDialect());
        return templateEngine;
    }*/

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        //修改密码
        registry.addViewController("/toChangePassword").setViewName("plat/sys/user/updatePassWord.html");
        //授权
        registry.addViewController("/toAuthorize").setViewName("plat/sys/role/authorize.html");
        //添加用户
        registry.addViewController("/sysUser/toAddUser").setViewName("plat/sys/user/addUser.html");
        //修改用户
        registry.addViewController("/sysUser/toUpdateUser").setViewName("plat/sys/user/updateUser.html");
        //添加角色
        registry.addViewController("/sysRole/toAddRole").setViewName("plat/sys/role/addRole.html");
        //修改角色
        registry.addViewController("/sysRole/toUpdateRole").setViewName("plat/sys/role/updateRole.html");
        //添加资源
        registry.addViewController("/sysResource/toAddResource").setViewName("plat/sys/resource/addResource.html");
        //修改资源
        registry.addViewController("/sysResource/toUpdateResource").setViewName("plat/sys/resource/updateResource.html");

        registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
    }

    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {
        super.configurePathMatch(configurer);
        configurer.setUseSuffixPatternMatch(false);//当此参数设置为true的时候，那么/user.html，/user.aa，/user.*都能是正常访问的。
    }
}
