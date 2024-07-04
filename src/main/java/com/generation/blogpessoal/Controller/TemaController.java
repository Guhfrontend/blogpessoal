package com.generation.blogpessoal.Controller;

import com.generation.blogpessoal.model.Tema;
import com.generation.blogpessoal.repository.TemaRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/temas")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class TemaController {

    @Autowired
    private TemaRepository temaRepository;

    @GetMapping
    public ResponseEntity<List<Tema>> getAll(){
            return ResponseEntity.ok(temaRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tema> getById(@PathVariable Integer id){
            return temaRepository.findById(id).map(resposta -> ResponseEntity.ok(resposta)).orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @GetMapping("/{descricao}")
    public ResponseEntity<List<Tema>> getByDescricao(@PathVariable String descricao){
        return ResponseEntity.ok(temaRepository.findAllByDescricaoContainingIgnoreCase(descricao));
    }

    @PostMapping
    public ResponseEntity<Tema> post(@Valid @RequestBody Tema Tema){
        return ResponseEntity.status(HttpStatus.CREATED).body(temaRepository.save(Tema));
    }

    @PutMapping
    public ResponseEntity<Tema> put(@Valid @RequestBody Tema Tema){
        return temaRepository.findById(Tema.getId()).map(resposta -> ResponseEntity.status(HttpStatus.OK).body(temaRepository.save(Tema))).orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(value = "/{id}")
    public void delete(@PathVariable Integer id){
        Optional<Tema> Tema = temaRepository.findById(id);
        if (Tema.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        temaRepository.deleteById(id);
    }
}
