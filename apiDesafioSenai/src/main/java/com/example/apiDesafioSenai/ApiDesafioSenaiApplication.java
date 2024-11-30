package com.example.apiDesafioSenai;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {
		"com.example.apiDesafioSenai.controllers",
		"com.example.apiDesafioSenai.exception",
		"com.example.apiDesafioSenai.services",
		"com.example.apiDesafioSenai.repository",
		"com.example.apiDesafioSenai.dto",
		"com.example.apiDesafioSenai.model",
		"com.example.apiDesafioSenai.parser"})
public class ApiDesafioSenaiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiDesafioSenaiApplication.class, args);
	}

}
