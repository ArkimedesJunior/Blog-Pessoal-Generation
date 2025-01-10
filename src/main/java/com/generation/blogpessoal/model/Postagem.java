package com.generation.blogpessoal.model;

import java.time.LocalDateTime;

import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity //  tratar ela como uma tabela
@Table(name = "tb_postagens") // indica o nome da tabela no banco de dados
public class Postagem {
	
	
    @Id // Ela é a chave primaria (PRIMARY KEY)
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto incremento
	private Long id;
    
    @NotBlank(message = "Esse Campo é Obrigatorio") // Titulo da postagem
    @Size(min = 5, max = 100, message = "Digite no minimo 5 e no maximo 100 caracteres")
    private String titulo;
    
    @NotBlank(message = "Esse Campo é Obrigatorio") // Titulo da postagem
    @Size(min = 10, max = 1000, message = "Digite no minimo 10 e no maximo 1000 caracteres")
	private String texto;
	
	@UpdateTimestamp
	private LocalDateTime data; // 10/1025   18:25:58
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getTexto() {
		return texto;
	}
	public void setTexto(String texto) {
		this.texto = texto;
	}
	public LocalDateTime getData() {
		return data;
	}
	public void setData(LocalDateTime data) {
		this.data = data;
	}
	
	
}