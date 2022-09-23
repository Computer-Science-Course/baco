package model.entities.certificado;

import java.time.LocalDate;

import model.entities.atividade.Atividade;
import model.entities.evento.Evento;
import model.entities.participante.Participante;
import model.enums.TipoAtividade;

public class Certificado {
	
	private String responsavel;
	private LocalDate dataCertificado;
	private TipoAtividade tipoAtividade;
	private String titulo;
	private String tituloEvento;
	private Double duracao;
	private Participante participante;
	private Boolean checkin;
	
	public Certificado() {}

	public Certificado(String responsavel, LocalDate dataCertificado, TipoAtividade tipoAtividade, String titulo,
			String tituloEvento, Double duracao, Participante participante, Boolean checkin) {
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

	public Participante getParticipante() {
		return participante;
	}

	public void setParticipante(Participante participante) {
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
		StringBuilder stringBuilder = new StringBuilder();
		
		stringBuilder.append("\tCertificado\n");
		stringBuilder.append("Certificamos que " + this.getParticipante());
		stringBuilder.append(" participou com êxito da atividade " + this.getTipoAtividade());
		stringBuilder.append(": " + this.getTitulo());
		stringBuilder.append(", realizada em " + this.getDataCertificado());
		stringBuilder.append(", durante a " + this.getTituloEvento());
		stringBuilder.append(", com carga horária total de " + this.getDuracao());		
		stringBuilder.append(" horas.");
		
		return stringBuilder.toString();
	}

	
	
	
	
	
	
	
	
	
	
	
	

}
