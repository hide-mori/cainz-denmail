package com.tcs.denmail.online;

import com.tcs.denmail.common.msg.MsgProperty;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.tcs.denmail.online.domain.repository")
public class Application {

	public static void main(String[] args) {
		MsgProperty.startup();
		SpringApplication.run(Application.class, args);
	}

}
