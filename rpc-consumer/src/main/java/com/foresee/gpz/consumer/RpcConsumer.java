package com.foresee.gpz.consumer;

import com.foresee.gpz.api.IHelloWorld;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Auther: guhao
 * @Date: 2018/6/3 20:15
 * @Description:
 */
public class RpcConsumer {

    public static void main(String[] args) {

        ApplicationContext act = new ClassPathXmlApplicationContext("/META-INF/conf/spring/*.xml");
        IHelloWorld bean = (IHelloWorld)act.getBean("helloWorldServiceImpl");
        bean.sayHello("kitty");

    }

}
