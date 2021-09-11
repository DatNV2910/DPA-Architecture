package com.fa.DPA;

import com.fa.DPA.constant.Constant;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import java.util.Base64;

@SpringBootApplication
public class DpaApplication extends SpringBootServletInitializer {


	public static void main(String[] args) {

		SpringApplication.run(DpaApplication.class, args);
	}

}
