package com.example.bank;

import com.example.bank.config.AccountProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;

@SpringBootApplication
public class BankApplication  {
	//implements CommandLineRunner


	public static void main(String[] args) {
		SpringApplication.run(BankApplication.class, args);
	}

/*	@Override
	public void run(String... args) throws Exception {
    System.out.println("Using environment: " + config.getEnvironment());
    System.out.println("name: " + config.getName());
    System.out.println("enabled: " + config.isEnabled());
    System.out.println("Services: " + config.getServers());
	}*/
}
