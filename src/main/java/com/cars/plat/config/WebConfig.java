package com.cars.plat.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

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
        /**************************系统功能*************************/
        //index
        registry.addViewController("/").setViewName("plat/sys/index.html");
        registry.addViewController("/index").setViewName("plat/sys/index.html");
        //修改密码
        registry.addViewController("/toChangePassword").setViewName("plat/sys/user/updatePassWord.html");
        //授权
        registry.addViewController("/toAuthorize").setViewName("plat/sys/role/authorize.html");
        //未授权
        registry.addViewController("/403").setViewName("plat/common/403.html");
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
        //添加任务
        registry.addViewController("/sysTask/toAddTask").setViewName("plat/sys/task/addTask.html");
        //修改任务
        registry.addViewController("/sysTask/toUpdateTask").setViewName("plat/sys/task/updateTask.html");
        //图标页面
        registry.addViewController("/iconfont").setViewName("plat/common/iconfont.html");
        /******************************系统功能结束*********************/

        //新增weblogic
        registry.addViewController("/weblogic/toAddWeblogic").setViewName("server/weblogic/addWeblogic.html");
        //修改weblogic
        registry.addViewController("/weblogic/toUpdateWeblogic").setViewName("server/weblogic/updateWeblogic.html");
        //新增weblogicApp
        registry.addViewController("/weblogic/toAddWeblogicApp").setViewName("server/weblogic/addWeblogicApp.html");
        //修改weblogicApp
        registry.addViewController("/weblogic/toUpdateWeblogicApp").setViewName("server/weblogic/updateWeblogicApp.html");
        //新增公司
        registry.addViewController("/company/toAddCompany").setViewName("outSource/company/addCompany.html");
        //修改公司
        registry.addViewController("/company/toUpdateCompany").setViewName("outSource/company/updateCompany.html");
        //新增人员
        registry.addViewController("/person/toAddPerson").setViewName("outSource/person/addPerson.html");
        //修改人员
        registry.addViewController("/person/toUpdatePerson").setViewName("outSource/person/updatePerson.html");

        registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
    }

    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {
        super.configurePathMatch(configurer);
        configurer.setUseSuffixPatternMatch(false);//当此参数设置为true的时候，那么/user.html，/user.aa，/user.*都能是正常访问的。
    }
}
