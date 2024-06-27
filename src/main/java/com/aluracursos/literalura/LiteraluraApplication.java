package com.aluracursos.literalura;


import com.aluracursos.literalura.main.Main;
import com.aluracursos.literalura.repository.AuthorRepository;
import com.aluracursos.literalura.repository.BookRepository;
import com.aluracursos.literalura.service.ApiService;
import com.aluracursos.literalura.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class LiteraluraApplication implements CommandLineRunner {


	@Autowired
	private BookService bookService;

	@Autowired
	private ApiService apiService;



	public static void main(String[] args) {
		SpringApplication.run(LiteraluraApplication.class, args);
	}



	public void run(String... args) throws Exception {

		Main main = new Main(bookService, apiService);
		main.menu();
	}
}

