package br.com.fiap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("br.com.fiap")
@ComponentScan("br.com.fiap")
public class TrabalhoSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(TrabalhoSpringApplication.class, args);
	}
}
