package com.tcs.denmail.online;

import com.tcs.denmail.common.msg.MsgProperty;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.tcs.denmail.online.domain.repository")
public class OnlineApplication {

	public static void main(String[] args) {
		// 初期化 メッセージ/ログAPI
		MsgProperty.startup();
		SpringApplication.run(OnlineApplication.class, args);
	}

}
