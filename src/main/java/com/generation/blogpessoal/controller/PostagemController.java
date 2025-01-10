package com.generation.blogpessoal.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.generation.blogpessoal.model.Postagem;
import com.generation.blogpessoal.repository.PostagemRepository;

@RestController   // Define ao spring que essa classe é uma controller
@RequestMapping("/postagens") // Define qual endpoint vai ser tratado por essa classe
@CrossOrigin(origins  = "*", allowedHeaders = "*")  // Libera o acesso a qualquer front
public class PostagemController {

	@Autowired // O spring dá autonomia para a Interface poder invocar os metodos
	private PostagemRepository postagemRepository;
	
	@GetMapping // Indica que essa método é chamado em verbos/Metodos HTTP do tipo Get
	public ResponseEntity<List<Postagem>> getAll(){
		return ResponseEntity.ok(postagemRepository.findAll()); // SELECT * FROM tb_postagens
	}
	
}

