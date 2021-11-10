package com.creta.filmes;

import java.time.LocalDate;

import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.creta.filmes.entity.Usuario;
import com.creta.filmes.enums.TipoUsuario;

@SpringBootApplication
public class FilmesApiApplication implements CommandLineRunner  {
	
	@Bean
	public ModelMapper modelMapper(){
		return new ModelMapper();
	}

	public static void main(String[] args) {
		SpringApplication.run(FilmesApiApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Usuario enos = new Usuario();
		enos.setNome("enos");
		enos.setEmail("enos@gmail.com");
		enos.setSenha("123");
		enos.setDataNascimento(LocalDate.now());
		enos.setTipoUsuario(TipoUsuario.ADMIN);
		
	}

}
