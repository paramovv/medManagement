package org.perscholas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
//import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication //before disabling security
//@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
//@ComponentScan(basePackages = "org.perscholas")
public class MedManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(MedManagementApplication.class, args);
	}

}
