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
	
	
	
	public Evento() {}
	public Evento(Integer id, String nome, String titulo, String descricao, LocalDateTime data_inicio, LocalDateTime data_termino) {
		this.id = id;
		this.nome = nome;
		this.titulo = titulo;
		this.descricao = descricao;
		this.data_inicio = data_inicio;
		this.data_termino = data_termino;
	}
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
	public LocalDateTime getDataInicio() {
		return data_inicio;
	}
	public void setDataInicio(LocalDateTime data_inicio) {
		this.data_inicio = data_inicio;
	}
	public LocalDateTime getDataTermino() {
		return data_termino;
	}
	public void setDataTermino(LocalDateTime data_termino) {
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
		stringBuilder.append(", Data inicio: " + this.getDataInicio().format(formatacao));
		stringBuilder.append(", Data termino: " + this.getDataTermino().format(formatacao));
		
		return stringBuilder.toString();
	}
	
	

}
