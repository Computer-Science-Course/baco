package model.entities.inscricao;

import model.entities.atividade.Atividade;
import model.entities.participante.Participante;

public class Inscricao {
	private Participante participante;
	private Atividade atividade;	
	private Boolean checkin;
		
	public Inscricao() {}
	
	public Inscricao(Participante participante, Atividade atividade, Boolean checkin) {
		this.participante = participante;
		this.atividade = atividade;
		this.checkin = checkin;
	}
	public Participante getParticipante() {
		return participante;
	}
	public void setParticipante(Participante participante) {
		this.participante = participante;
	}
	public Atividade getAtividade() {
		return atividade;
	}
	public void setAtividade(Atividade atividade) {
		this.atividade = atividade;
	}
	public Boolean getCheckin() {
		return checkin;
	}
	public void setCheckin(Boolean checkin) {
		this.checkin = checkin;
	}
	
	
}
