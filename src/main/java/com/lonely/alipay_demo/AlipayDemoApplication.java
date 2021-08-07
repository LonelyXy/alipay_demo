package com.lonely.alipay_demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author xiyang
 */
@SpringBootApplication
@MapperScan("com.lonely.alipay_demo.dao")
@EnableTransactionManagement
public class AlipayDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(AlipayDemoApplication.class, args);
    }

}
