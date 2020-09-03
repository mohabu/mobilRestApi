package com.companyname.eshop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableAutoConfiguration
@EnableSwagger2
@ComponentScan()
public class MobilesEshopApplication {

	public static void main(String[] args) {
		SpringApplication.run(MobilesEshopApplication.class, args);
	}
	
	/*
	@Bean
	public Docket swaggerConfiguration() {
		
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.paths(PathSelectors.ant("/eshop/*"))
				.apis(RequestHandlerSelectors.basePackage("com"))
				.build();
	} */

}
