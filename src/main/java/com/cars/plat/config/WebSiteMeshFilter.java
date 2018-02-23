package com.cars.plat.config;

import org.sitemesh.builder.SiteMeshFilterBuilder;
import org.sitemesh.config.ConfigurableSiteMeshFilter;
import org.sitemesh.content.tagrules.html.DivExtractingTagRuleBundle;

/**
 * Created by wangyupeng on 2017/8/19.
 */
public class WebSiteMeshFilter extends ConfigurableSiteMeshFilter {
    @Override
    protected void applyCustomConfiguration(SiteMeshFilterBuilder builder) {
        /*指明满足“/*”的页面，将被“/WEB-INF/views/decorators/decorator.jsp”所装饰*/
        builder.addDecoratorPath("/*", "/WEB-INF/views/plat/decorators/decorator.jsp")
                .addExcludedPath("/login")
                .addExcludedPath("/druid/**")
                .addExcludedPath("/sys/user/searchUserLogin");
        builder.addTagRuleBundles(new DivExtractingTagRuleBundle());
    }
}
