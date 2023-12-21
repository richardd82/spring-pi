package com.henry.pijava;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"com.henry.pijava"})
public class PiJavaApplication {

	public static void main(String[] args) {
		SpringApplication.run(PiJavaApplication.class, args);
	}

}
