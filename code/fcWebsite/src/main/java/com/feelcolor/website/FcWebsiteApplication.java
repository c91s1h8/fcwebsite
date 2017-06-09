package com.feelcolor.website;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@EnableTransactionManagement    //开启事物
//@MapperScan("com.feelcolor.website.dao.mapper")    //Mybatis mapper扫描2
public class FcWebsiteApplication {

	public static void main(String[] args) {
		SpringApplication.run(FcWebsiteApplication.class, args);
	}
}
