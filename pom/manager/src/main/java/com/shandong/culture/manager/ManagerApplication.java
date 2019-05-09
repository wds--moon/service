package com.shandong.culture.manager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

//import tk.mybatis.spring.annotation.MapperScan;

//@MapperScan("com.shandong.culture.manager.mapper")
@SpringBootApplication
@EntityScan
@EnableJpaRepositories
public class ManagerApplication {
	public static void main(String[] args) {
		SpringApplication.run(ManagerApplication.class, args);
	}
}
