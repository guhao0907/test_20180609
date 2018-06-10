package com.foresee.gpz.boot.consumer.config;

import com.foresee.gpz.boot.consumer.filters.RemoteHostFilter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;

/**
 * @Auther: guhao
 * @Date: 2018/6/10 22:38
 * @Description:
 */
@Configuration
public class WebConfiguration {

    @Value("${hostFilter.isEnabled}")
    private String isEnabled;

    /**
     * RemoteHostFilter bean
     */
    @Bean
    public FilterRegistrationBean hostFilter(){
        FilterRegistrationBean bean = new FilterRegistrationBean(new RemoteHostFilter());
        bean.setName("hostFilter");
        //拦截url
        bean.addUrlPatterns("/*");
        bean.addInitParameter("isEnabled", isEnabled);
        //order值越小，越优先执行
        bean.setOrder(1);
        return bean;
    }


}
