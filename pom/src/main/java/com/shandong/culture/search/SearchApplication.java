package com.shandong.culture.search;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
* @Description:    主配置类
 * EnableTransactionManagement 开启事务管理,需要保证操作数据库和elasticSearch同步进行,防止数据不一致
 * MapperScan 添加手动扫描mapper文件
* @Author:         moon
* @CreateDate:     2019/5/9 0009 10:43
* @UpdateUser:     moon
* @UpdateDate:     2019/5/9 0009 10:43
* @UpdateRemark:   修改内容
* @Version:        1.0
*/
@EnableTransactionManagement
@MapperScan("com.shandong.culture.search.mapper")
@SpringBootApplication
public class SearchApplication {

    public static void main(String[] args) {
        SpringApplication.run(SearchApplication.class, args);
    }

}
