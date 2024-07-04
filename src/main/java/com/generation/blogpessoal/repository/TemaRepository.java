package com.generation.blogpessoal.repository;


import com.generation.blogpessoal.model.Postagem;
import com.generation.blogpessoal.model.Tema;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TemaRepository extends JpaRepository<Tema, Integer> {

    public List<Tema> findAllByDescricaoContainingIgnoreCase(@Param("descricao") String descricao);
}
