package com.alurachallenge.literalura;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.alurachallenge.literalura.principal.Menu;
import com.alurachallenge.literalura.repository.AutorRepository;
import com.alurachallenge.literalura.repository.LibrosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

@SpringBootApplication
public class ChallengeLiteraluraApplication implements CommandLineRunner{
	@Autowired
	private LibrosRepository repository;
	@Autowired
	private AutorRepository autorRepository;

	public static void main(String[] args) {
		SpringApplication.run(ChallengeLiteraluraApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		System.out.println("\nBienvenido/a a la biblioteca llamada LiterAlura.");
		Menu menu = new Menu(repository,autorRepository);
		menu.mostrarMenu();

	}
}

