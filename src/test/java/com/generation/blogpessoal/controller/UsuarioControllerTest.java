package com.generation.blogpessoal.controller;

import com.generation.blogpessoal.model.Usuario;
import com.generation.blogpessoal.repository.UsuarioRepository;
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
public class UsuarioControllerTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @BeforeAll
    void start(){

        usuarioRepository.deleteAll();

        usuarioService.cadastrarUsuario(new Usuario(0L, "Root", "root@root.com", "rootroot", "-"));
    }

    @Test
    @DisplayName("Cadastrar Um Usuário")
    public void deveCriarUmUsuario() {

        HttpEntity<Usuario> corpoRequisicao = new HttpEntity<>(new Usuario(0l, "Paulo Antunes", "paulo_antunes@gmail.com.br", "12345678", "-"));

        ResponseEntity<Usuario> corpoResposta = testRestTemplate.exchange("/usuarios/cadastrar", HttpMethod.POST, corpoRequisicao,Usuario.class);

        assertEquals(HttpStatus.CREATED, corpoResposta.getStatusCode());
    }

    @Test
    @DisplayName("Não deve permitir duplicação de Usuário")
    public void naoDeveDuplicarUsuario() {

        usuarioService.cadastrarUsuario(new Usuario(0L, "Maria da Silva", "maria_silva@email.com.br", "123456789", "-"));

        HttpEntity<Usuario> corpoRequisicao = new HttpEntity<>(new Usuario(0L, "Maria da Silva", "maria_silva@email.com.br", "123456789", "-"));

        ResponseEntity<Usuario> corpoResposta = testRestTemplate.exchange("/usuarios/cadastrar", HttpMethod.POST, corpoRequisicao, Usuario.class);

        assertEquals(HttpStatus.BAD_REQUEST, corpoResposta.getStatusCode());
    }

    @Test
    @DisplayName("Atualizar um Usuário")
    public void deveAtualizarUmUsuario() {

        Optional<Usuario> usuarioCadastrado = usuarioService.cadastrarUsuario(new Usuario(0L, "Juliana Andrews", "juliana_andrews@email.com.br", "juliana123", "-"));

        Usuario usuarioUpdate = new Usuario(usuarioCadastrado.get().getId(), "Juliana Andrews Ramos", "juliana_ramos@email.com.br", "juliana123", "-");

        HttpEntity<Usuario> corpoRequisicao = new HttpEntity<Usuario>(usuarioUpdate);

        ResponseEntity<Usuario> corpoResposta = testRestTemplate.withBasicAuth("root@root.com", "rootroot").exchange("/usuarios/atualizar", HttpMethod.PUT, corpoRequisicao, Usuario.class);

        assertEquals(HttpStatus.OK, corpoResposta.getStatusCode());
    }

    @Test
    @DisplayName("Listar todos os Usuários")
    public void deveMostrarTodosUsuarios() {

        usuarioService.cadastrarUsuario(new Usuario(0L, "Maria da Silva", "maria_silva@email.com.br", "123456789", "-"));

        usuarioService.cadastrarUsuario(new Usuario(1L, "Paulo Antunes", "paulo_antunes@email.com.br", "123456789", "-"));

        ResponseEntity<String> resposta = testRestTemplate.withBasicAuth("root@root.com","rootroot").exchange("/usuarios/all", HttpMethod.GET,null,String.class);
        assertEquals(HttpStatus.OK,resposta.getStatusCode());
    }
}

