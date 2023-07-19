package com.projet.banque;

import com.projet.banque.entities.Client;
import com.projet.banque.services.ClientServices;
import com.projet.banque.services.CompteServices;
import com.projet.banque.services.EmployeServices;
import jakarta.persistence.Entity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;

@SpringBootApplication
public class BanqueApplication {


	public static void main(String[] args) {
		SpringApplication.run(BanqueApplication.class, args);
	}

}
