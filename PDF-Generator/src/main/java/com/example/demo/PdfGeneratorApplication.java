package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Import;

import com.example.demo.DTOs.KafkaConsumerConfig;


@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@Import(KafkaConsumerConfig.class)

public class PdfGeneratorApplication {

	public static void main(String[] args) {
		SpringApplication.run(PdfGeneratorApplication.class, args);
	}

}
