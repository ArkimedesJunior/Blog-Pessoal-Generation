package com.generation.blogpessoal.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.generation.blogpessoal.model.Postagem;
import com.generation.blogpessoal.repository.PostagemRepository;

import jakarta.validation.Valid; 



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
		@GetMapping("/{id}")
		public ResponseEntity<Postagem> getById(@PathVariable Long id) {
			return postagemRepository.findById(id)
					.map(resposta -> ResponseEntity.ok(resposta))
					.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
			
			
	/*Essa anotação é usada para mapear uma requisição HTTP GET para o método getById.
	O /{id} significa que o valor de id será extraído da URL da requisição. 
	Por exemplo, se a URL da requisição for /postagens/5, o valor 5 será atribuído ao parâmetro id.
			
		O método getById será executado quando uma requisição GET for feita à URL /postagens/{id}.
O parâmetro @PathVariable Long id indica que o valor do parâmetro id na URL será passado para o método.
 No exemplo mencionado, se a URL for /postagens/5, o valor id será 5.	
			*/
			
		
		}
		
		@GetMapping("/titulo/{titulo}")
		public ResponseEntity<List<Postagem>> getByTitulo(@PathVariable String titulo) {
			return ResponseEntity.ok(postagemRepository.findAllByTituloContainingIgnoreCase(titulo));
		}
		
		@PostMapping
		public ResponseEntity<Postagem> post(@Valid @RequestBody Postagem postagem) {
			return ResponseEntity
					.status(HttpStatus.CREATED)
					.body(postagemRepository.save(postagem));
		}
		
		@PutMapping
		public ResponseEntity<Postagem> put(@Valid @RequestBody Postagem postagem) {
			return postagemRepository.findById(postagem.getId())
					.map(resposta -> ResponseEntity.status(HttpStatus.OK)
							.body(postagemRepository.save(postagem)))
					.orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
		}
		
		@ResponseStatus(HttpStatus.NO_CONTENT)
		@DeleteMapping("/{id}")
		public void delete(@PathVariable Long id ) {
			Optional<Postagem> postagem = postagemRepository.findById(id);
		
			if(postagem.isEmpty ())
				throw new ResponseStatusException(HttpStatus.NOT_FOUND);
			
			postagemRepository.deleteById(id);
		}		

}
	


