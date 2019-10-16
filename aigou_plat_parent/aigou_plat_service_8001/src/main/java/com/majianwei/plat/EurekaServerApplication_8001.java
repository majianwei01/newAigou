package com.majianwei.plat;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

//@EnableAutoConfiguration(exclude = DataSourceAutoConfiguration.class)这个注解是排除自动注入，会导致mapper不能注入
@SpringBootApplication
@EnableEurekaClient
@MapperScan("com.majianwei.plat.mapper")
@EnableFeignClients(basePackages = "com.majianwei.common")
public class EurekaServerApplication_8001 {
    public static void main(String[] args) {
        SpringApplication.run(EurekaServerApplication_8001.class, args);
    }
}
