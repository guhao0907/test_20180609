package com.foresee.gpz.spring.boot.dubbo.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.foresee.gpz.api.IHelloWorld;

/**
 * @Auther: guhao
 * @Date: 2018/6/10 12:52
 * @Description: 服务实现类
 */
@Service(version = "1.0.0")
public class HelloService implements IHelloWorld {

    @Override
    public void sayHello(String name) {
        System.err.println("hello " + name);
    }
}
