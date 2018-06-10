package com.foresee.gpz.boot.consumer;

import com.alibaba.dubbo.config.annotation.Reference;
import com.foresee.gpz.api.IHelloWorld;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: guhao
 * @Date: 2018/6/10 15:21
 * @Description:
 */
@RestController
public class BootConsumerController {

    @Reference(version = "1.0.0")
    private IHelloWorld helloWorld;

    @GetMapping(value = "/hello.do")
    public void hello(){
        helloWorld.sayHello("spring boot dubbo！");
    }
}
