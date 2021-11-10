package com.creta.filmes.entity.dto;

import java.time.LocalDate;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import com.creta.filmes.enums.TipoUsuario;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDTO {
	
	private Long id;	
	
	@NotBlank
	@Size(min=2, max=40, message = "Nome deve ter ao menos 2 caracteres")
	private String nome;
	
	@NotEmpty
	@Past
	@DateTimeFormat(pattern = "dd-MM-yyyy")
	private LocalDate dataNascimento;
	
	@NotNull
	private TipoUsuario tipoUsuario;
	
	@Email(message = "Deve ser um email válido")
	private String email;
	
	@NotEmpty
	@Size(min=5, max=40, message = "Nome deve ter ao menos 5 caracteres")
	private String senha;
}
