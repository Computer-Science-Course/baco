package model.entities.atividade;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import model.entities.evento.Evento;
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
	
	private Evento evento;

	public Atividade() {
		
	}
	
	public Atividade(Integer id, String titulo, String descricao, TipoAtividade tipoAtividade,
			LocalDateTime data_inicio, LocalDateTime data_termino, Double duracao, String nome_responsavel,
			Evento evento) {
		this.id = id;
		this.titulo = titulo;
		this.descricao = descricao;
		this.tipoAtividade = tipoAtividade;
		this.data_inicio = data_inicio;
		this.data_termino = data_termino;
		this.duracao = duracao;
		this.nome_responsavel = nome_responsavel;
		this.setEvento(evento);
	}
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
	public Double getDuracao() {
		return duracao;
	}
	public void setDuracao(Double duracao) {
		this.duracao = duracao;
	}
	public String getNomeResponsavel() {
		return nome_responsavel;
	}
	public void setNomeResponsavel(String nome_responsavel) {
		this.nome_responsavel = nome_responsavel;
	}
	
	public Evento getEvento() {
		return evento;
	}

	public void setEvento(Evento evento) {
		this.evento = evento;
	}
	
	@Override
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder();
		DateTimeFormatter formatacao = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
		
		stringBuilder.append("Id: " + this.getId());
		stringBuilder.append(", Titulo: " + this.getTitulo());
		stringBuilder.append(", Descrição: " + this.getDescricao());
		stringBuilder.append(", Tipo de atividade: " + this.getTipoAtividade());
		stringBuilder.append(", Data inicio: " + this.getDataInicio().format(formatacao));
		stringBuilder.append(", Data termino: " + this.getDataTermino().format(formatacao));
		stringBuilder.append(", Druração:" + this.getDuracao());
		stringBuilder.append(", Nome do responsável:" + this.getNomeResponsavel());
		stringBuilder.append(", " + this.getEvento());
		
		return stringBuilder.toString();
	}

	
	
	
	
	

}
