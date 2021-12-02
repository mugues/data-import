package com.sample;

import com.sample.service.ImportService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ManagingTransactionsApplication extends  AppRunner{

	public ManagingTransactionsApplication(ImportService importService) {
		super(importService);
	}

	public static void main(String[] args) {
		SpringApplication.run(ManagingTransactionsApplication.class, args);
	}

}
