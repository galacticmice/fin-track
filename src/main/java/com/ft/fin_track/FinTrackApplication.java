package com.ft.fin_track;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class FinTrackApplication {

	public static void main(String[] args) {
		SpringApplication.run(FinTrackApplication.class, args);
	}

}

/// run startFresh() to reset database
/// create