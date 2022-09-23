package model.entities.certificado;

import model.entities.atividade.Atividade;
import model.entities.evento.Evento;
import model.entities.participante.Participante;
import model.enums.TipoAtividade;

public class Certificado {
	
	private Atividade Responsavel;
	private Atividade dataCertificado;
	private TipoAtividade tipoAtividade;
	private Atividade titulo;
	private Evento tituloEvento;
	private Atividade duracao;
	private Participante participante;
	private Boolean checkin;
	
	public Certificado() {}

	public Certificado(Atividade responsavel, Atividade dataCertificado, TipoAtividade tipoAtividade, Atividade titulo,
			Evento tituloEvento, Atividade duracao, Participante participante, Boolean checkin) {
		super();
		Responsavel = responsavel;
		this.dataCertificado = dataCertificado;
		this.tipoAtividade = tipoAtividade;
		this.titulo = titulo;
		this.tituloEvento = tituloEvento;
		this.duracao = duracao;
		this.participante = participante;
		this.checkin = checkin;
	}

	public Atividade getResponsavel() {
		return Responsavel;
	}
	public void setResponsavel(Atividade responsavel) {
		Responsavel = responsavel;
	}
	public Atividade getDataCertificado() {
		return dataCertificado;
	}
	public void setDataCertificado(Atividade dataCertificado) {
		this.dataCertificado = dataCertificado;
	}
	public TipoAtividade getTipoAtividade() {
		return tipoAtividade;
	}
	public void setTipoAtividade(TipoAtividade tipoAtividade) {
		this.tipoAtividade = tipoAtividade;
	}
	
	public Atividade getTitulo() {
		return titulo;
	}

	public void setTitulo(Atividade titulo) {
		this.titulo = titulo;
	}
	
	public Evento getTituloEvento() {
		return tituloEvento;
	}

	public void setTituloEvento(Evento tituloEvento) {
		this.tituloEvento = tituloEvento;
	}

	public Atividade getDuracao() {
		return duracao;
	}
	public void setDuracao(Atividade duracao) {
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
