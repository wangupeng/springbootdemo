package com.cars.plat.common.pub;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import java.io.IOException;
import java.util.Properties;

/**
 * 全局参数配置，用于在后端获取参数
 * Created by wangyupeng on 2017/8/18.
 */
@Component
public class PubConfig {
    public PubConfig(InternalResourceViewResolver resolver) {

        Resource resource = new ClassPathResource("application.properties");
        Properties props = null;
        try {
            props = PropertiesLoaderUtils.loadProperties(resource);
        } catch (IOException e) {
            e.printStackTrace();
        }
        resolver.setAttributes(props);
    }
}
