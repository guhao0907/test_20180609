package com.foresee.gpz.spring.boot.dubbo;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

/**
 * @Auther: guhao
 * @Date: 2018/6/20 23:57
 * @Description: 在servlet容器启东时，指定启动类
 */
public class SpringbootWebInit extends SpringBootServletInitializer{

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(ApplicationStartMain.class);
    }
}
