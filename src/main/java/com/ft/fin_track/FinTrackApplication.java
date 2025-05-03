package com.ft.fin_track;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class FinTrackApplication {
	public static void main(String[] args) {
		SpringApplication.run(FinTrackApplication.class, args);
	}
}