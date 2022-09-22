package model.entities.participante;

import model.enums.TipoDocumento;

public class Participante {
	private Integer id;
	private String nome;
	private String numero_documento;
	private TipoDocumento tipo_documento;
	
	
	public Participante() {} 
	
	public Participante(String nome, String numero_documento, TipoDocumento tipo_documento) 
	{
		this.nome = nome;
		this.numero_documento = numero_documento;
		this.tipo_documento = tipo_documento;
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

	public String getNumeroDocumento() {
		return numero_documento;
	}

	public void setNumeroDocumento(String numero_documento) {
		this.numero_documento = numero_documento;
	}

	public TipoDocumento getTipoDocumento() {
		return tipo_documento;
	}

	public void setTipoDocumento(TipoDocumento tipo_documento) {
		this.tipo_documento = tipo_documento;
	}
	
	@Override
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder();
		
		stringBuilder.append(", Nome: " + this.getNome());
		stringBuilder.append(", Numero Documento: " + this.getNumeroDocumento());
		stringBuilder.append(", Descrição: " + this.getTipoDocumento());
		
		return stringBuilder.toString();
	}
	
	
}
