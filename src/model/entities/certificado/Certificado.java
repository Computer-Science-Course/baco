package model.entities.certificado;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import model.enums.TipoAtividade;

public class Certificado {
	
	private String responsavel;
	private LocalDate dataCertificado;
	private TipoAtividade tipoAtividade;
	private String titulo;
	private String tituloEvento;
	private Double duracao;
	private String participante;
	private Boolean checkin;
	
	public Certificado() {}


	public Certificado(String responsavel, LocalDate dataCertificado, TipoAtividade tipoAtividade, String titulo,
			String tituloEvento, Double duracao, String participante, Boolean checkin) {
		this.responsavel = responsavel;
		this.dataCertificado = dataCertificado;
		this.tipoAtividade = tipoAtividade;
		this.titulo = titulo;
		this.tituloEvento = tituloEvento;
		this.duracao = duracao;
		this.participante = participante;
		this.checkin = checkin;
	}
	
	
	public String getResponsavel() {
		return responsavel;
	}


	public void setResponsavel(String responsavel) {
		this.responsavel = responsavel;
	}


	public LocalDate getDataCertificado() {
		return dataCertificado;
	}


	public void setDataCertificado(LocalDate dataCertificado) {
		this.dataCertificado = dataCertificado;
	}


	public TipoAtividade getTipoAtividade() {
		return tipoAtividade;
	}


	public void setTipoAtividade(TipoAtividade tipoAtividade) {
		this.tipoAtividade = tipoAtividade;
	}


	public String getTitulo() {
		return titulo;
	}


	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}


	public String getTituloEvento() {
		return tituloEvento;
	}


	public void setTituloEvento(String tituloEvento) {
		this.tituloEvento = tituloEvento;
	}


	public Double getDuracao() {
		return duracao;
	}


	public void setDuracao(Double duracao) {
		this.duracao = duracao;
	}


	public String getParticipante() {
		return participante;
	}


	public void setParticipante(String participante) {
		this.participante = participante;
	}


	public Boolean getCheckin() {
		return checkin;
	}


	public void setCheckin(Boolean checkin) {
		this.checkin = checkin;
	}


	@Override
	public String toString() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		StringBuilder stringBuilder = new StringBuilder();
		
		stringBuilder.append("========================\n");
		stringBuilder.append("\tCERTIFICADO\n\n");
		stringBuilder.append("Certificamos que " + this.getParticipante());
		stringBuilder.append(" \nparticipou com êxito da atividade " + this.getTipoAtividade());
		stringBuilder.append(": " + this.getTitulo());
		stringBuilder.append(", \nrealizada em " + this.getDataCertificado().format(formatter));
		stringBuilder.append(", durante a " + this.getTituloEvento());
		stringBuilder.append(", \ncom carga horária total de " + this.getDuracao());		
		stringBuilder.append(" horas.\n");
		stringBuilder.append("========================\n");
		
		return stringBuilder.toString();
	}
}
