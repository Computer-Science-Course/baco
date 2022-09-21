package model.entities;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import model.enums.TipoAtividade;

public class Atividade {
	
	private Integer id;
	private String titulo;
	private String descricao;
	private TipoAtividade tipoAtividade;
	private LocalDateTime data_inicio;
	private LocalDateTime data_termino;
	private Double duracao;
	private String nome_responsavel;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
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
	public TipoAtividade getTipoAtividade() {
		return tipoAtividade;
	}
	public void setTipoAtividade(TipoAtividade tipoAtividade) {
		this.tipoAtividade = tipoAtividade;
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
	public Double getDuracao() {
		return duracao;
	}
	public void setDuracao(Double duracao) {
		this.duracao = duracao;
	}
	public String getNome_responsavel() {
		return nome_responsavel;
	}
	public void setNome_responsavel(String nome_responsavel) {
		this.nome_responsavel = nome_responsavel;
	}
	
	@Override
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder();
		DateTimeFormatter formatacao = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
		
		stringBuilder.append("Id: " + this.getId());
		stringBuilder.append(", Titulo: " + this.getTitulo());
		stringBuilder.append(", Descrição: " + this.getDescricao());
		stringBuilder.append(", Tipo de atividade: " + this.getTipoAtividade());
		stringBuilder.append(", Data inicio: " + this.getData_inicio().format(formatacao));
		stringBuilder.append(", Data termino: " + this.getData_termino().format(formatacao));
		stringBuilder.append(", Druração:" + this.getDuracao());
		stringBuilder.append(", Nome do responsável:" + this.getNome_responsavel());
		
		return stringBuilder.toString();
	}
	
	
	
	

}
