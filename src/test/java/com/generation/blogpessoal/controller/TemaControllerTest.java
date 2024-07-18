package com.generation.blogpessoal.controller;

import com.generation.blogpessoal.model.Tema;
import com.generation.blogpessoal.model.Usuario;
import com.generation.blogpessoal.repository.TemaRepository;
import com.generation.blogpessoal.service.UsuarioService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TemaControllerTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private TemaRepository temaRepository;

    @BeforeAll
    void start(){
        temaRepository.deleteAll();
        usuarioService.cadastrarUsuario(new Usuario(0L, "Root", "root@root.com", "rootroot", "-"));
    }

    @Test
    @DisplayName("Cadastrar Um Tema")
    public void deveCriarUmTema() {

        HttpEntity<Tema> corpoRequisicao = new HttpEntity<>(new Tema(0l, "Paulo Antunes"));

        ResponseEntity<Tema> corpoResposta = testRestTemplate.withBasicAuth("root@root.com", "rootroot").exchange("/temas", HttpMethod.POST, corpoRequisicao,Tema.class);

        assertEquals(HttpStatus.CREATED, corpoResposta.getStatusCode());
    }

    @Test
    @DisplayName("Listar todos os Temas")
    public void deveMostrarTodosTemas() {

        HttpEntity<Tema> corpoRequisicao = new HttpEntity<>(new Tema(0l, "Animais"));
        HttpEntity<Tema> corpoRequisicao2 = new HttpEntity<>(new Tema(1l, "jogos"));

        ResponseEntity<String> resposta = testRestTemplate.withBasicAuth("root@root.com","rootroot").exchange("/temas", HttpMethod.GET,null,String.class);
        assertEquals(HttpStatus.OK,resposta.getStatusCode());
    }


}

