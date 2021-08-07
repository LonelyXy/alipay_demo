package com.lonely.alipay_demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Author: xiyang
 * @FileName: CrosConfig
 * @Date: Created in 2021/7/22 14:38
 * @Vserion:
 * @Description: TODO
 */
@Configuration
public class CrosConfig implements WebMvcConfigurer {

    public static final  String[] METHODS ={"GET","POST","PUT","PATCH","DELETE"};

    @Override
    public void addCorsMappings(CorsRegistry registry){
        registry.addMapping("/**")
                .allowedOriginPatterns("*")
                .allowedHeaders("*")
                .allowedMethods(METHODS)
                .allowCredentials(true)
                .maxAge(6000);
    }
}
