package model.entities.evento;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Evento {
	
	private Integer id;
	private String nome;
	private String titulo;
	private String descricao;
	private LocalDateTime data_inicio;
	private LocalDateTime data_termino;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public LocalDateTime getData_inicio() {
		return data_inicio;
	}
	public void setData_inicio(LocalDateTime data_inicio) {
		this.data_inicio = data_inicio;
	}
	public LocalDateTime getData_termino() {
		return data_termino;
	}
	public void setData_termino(LocalDateTime data_termino) {
		this.data_termino = data_termino;
	}
	@Override
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder();
		DateTimeFormatter formatacao = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
		
		stringBuilder.append("Id: " + this.getId());
		stringBuilder.append(", Nome: " + this.getNome());
		stringBuilder.append(", Titulo: " + this.getTitulo());
		stringBuilder.append(", Descrição: " + this.getDescricao());
		stringBuilder.append(", Data inicio: " + this.getData_inicio().format(formatacao));
		stringBuilder.append(", Data termino: " + this.getData_termino().format(formatacao));
		
		return stringBuilder.toString();
	}
	
	

}
