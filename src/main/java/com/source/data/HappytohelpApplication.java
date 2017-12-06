package com.source.data;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.source.util.CommonMethod;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@ComponentScan("com.source.*")
public class HappytohelpApplication {

	public static void main(String[] args) {
		SpringApplication.run(HappytohelpApplication.class, args);
		
	}
}
