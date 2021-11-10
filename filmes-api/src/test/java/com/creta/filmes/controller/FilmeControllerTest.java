package com.creta.filmes.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.creta.filmes.entity.Filme;
import com.creta.filmes.entity.Usuario;
import com.creta.filmes.exception.BusinessException;
import com.creta.filmes.repository.FilmeRepository;
import com.creta.filmes.service.FilmeService;
import com.creta.filmes.service.impl.FilmeServiceImpl;

@ExtendWith(SpringExtension.class)
public class FilmeControllerTest {
	
	FilmeService service;
	
	@MockBean
	FilmeRepository repository;

    @BeforeEach
    public void setUp() {
        this.service = new FilmeServiceImpl(repository);
    }

    @Test
    @DisplayName("Deve salvar um filme")
    public void saveFilmeTest() {
        //cenario
        Filme filme = createValidFilme();
        when(repository.existsByCodigo(Mockito.anyString()) ).thenReturn(false);
        when(repository.save(filme)).thenReturn(
        		Filme.builder().id(1l)
                        .codigo(123l)
                        .nome("Fulano")
                        .build()
        );

        //execucao
        Usuario usuario = new Usuario();
        Filme savedFilme = service.saveFilme(usuario,filme);

        //verificacao
        assertThat(savedFilme.getId()).isNotNull();
        assertThat(savedFilme.getCodigo()).isEqualTo("123");
        assertThat(savedFilme.getNome()).isEqualTo("As aventuras");
    }

    @Test
    @DisplayName("Deve lançar erro de negocio ao tentar salvar um filme com código duplicado")
    public void shouldNotSaveAMovieWithDuplicatedCode(){
        //cenario
    	Usuario usuario = new Usuario();
        Filme filme = createValidFilme();
        when( repository.existsByCodigo(Mockito.anyString()) ).thenReturn(true);

        //execucao
        Throwable exception = Assertions.catchThrowable(() -> service.saveFilme(usuario,filme));

        //verificacoes
        assertThat(exception)
                .isInstanceOf(BusinessException.class)
                .hasMessage("Código já cadastrado!");

        Mockito.verify(repository, Mockito.never()).save(filme);
    }

    @Test
    @DisplayName("Deve filtrar filmes pelas propriedades")
    public void findFilmeTest(){
        //cenario
    	Filme filme = createValidFilme();

        PageRequest pageRequest = PageRequest.of(0, 10);
        String nome = "titanic";
        List<Filme> lista = Arrays.asList(filme);
        Page<Filme> page = new PageImpl<Filme>(lista, pageRequest, 1);
        when( repository.findAll(Mockito.any(Example.class), Mockito.any(PageRequest.class)))
                .thenReturn(page);

        //execucao
        List<Filme> result = service.getFilmePorNome(nome);


        //verificacoes
        assertThat(result.isEmpty()).isFalse();
        assertThat(result.get(0).getNome()).isEqualTo(nome);
        //
        
    }

    private Filme createValidFilme() {
        return Filme
        		.builder()
        		.codigo(123l)
        		.nome("titanic")
        		.build();
    }
}
