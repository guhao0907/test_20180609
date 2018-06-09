package com.foresee.gpz.service;

import com.foresee.gpz.api.IHelloWorld;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Auther: guhao
 * @Date: 2018/6/3 17:03
 * @Description: IHelloService 实现类
 */
public class HelloServiceImpl implements IHelloWorld {

    /**
     * 日志
     */
    private Logger log = LoggerFactory.getLogger(HelloServiceImpl.class);

    @Override
    public void sayHello(String name) {
        System.err.println("hello " + name);
    }
}
