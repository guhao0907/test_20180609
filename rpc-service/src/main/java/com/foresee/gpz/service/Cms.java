package com.foresee.gpz.service;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.concurrent.TimeUnit;

/**
 * @Auther: guhao
 * @Date: 2018/6/3 20:18
 * @Description:
 */
public class Cms {

    /**
     * 启动spring容器
     */
    public static void main(String[] args) {
        ClassPathXmlApplicationContext cat = new ClassPathXmlApplicationContext("/META-INF/conf/spring/*.xml");
        cat.start();
        System.out.println("容器启动了！");
        while (true){
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
