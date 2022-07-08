package com.cursojava.curso;

import com.cursojava.curso.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CursoApplication {
	@Autowired
	static
	JWTUtil util ;

	public static void main(String[] args) {
		SpringApplication.run(CursoApplication.class, args);




	}

}
