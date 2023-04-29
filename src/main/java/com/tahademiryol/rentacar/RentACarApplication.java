package com.tahademiryol.rentacar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;


@EnableCaching
@SpringBootApplication
public class RentACarApplication {

    public static void main(String[] args) {
        SpringApplication.run(RentACarApplication.class, args);
    }

//	ApplicationContext apc = SpringApplication.run(RentACarApplication.class, args);
//        for (String s : apc.getBeanDefinitionNames()) {
//		System.out.println(s);
//	}


}
