package model.entities.usuario;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import model.enums.TipoDocumento;

public class Usuario {
	private Integer id;
	private String numero_documento;
	private TipoDocumento tipo_documento;
	private String nome_completo;
	private String senha;
	private Boolean isAdm;
	private LocalDateTime ultimo_acesso;
	
	
	public Usuario() {}
	public Usuario(
			String numero_documento, TipoDocumento tipo_documento, String nome_completo, String senha,
			Boolean isAdm, LocalDateTime ultimo_acesso
	) {
		this.numero_documento = numero_documento;
		this.tipo_documento = tipo_documento;
		this.nome_completo = nome_completo;
		this.senha = senha;
		this.isAdm = isAdm;
		this.ultimo_acesso = ultimo_acesso;
	}
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
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
	public String getNomeCompleto() {
		return nome_completo;
	}
	public void setNomeCompleto(String nome_completo) {
		this.nome_completo = nome_completo;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public Boolean isAdm() {
		return isAdm;
	}
	public void setIsAdm(Boolean isAdm) {
		this.isAdm = isAdm;
	}
	public LocalDateTime getUltimoAcesso() {
		return ultimo_acesso;
	}
	public void setUltimoAcesso(LocalDateTime ultimo_acesso) {
		this.ultimo_acesso = ultimo_acesso;
	}


	@Override
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder();
		DateTimeFormatter formatacao = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
		
		stringBuilder.append("Id: " + this.getId());
		stringBuilder.append(", Numero de documento: " + this.getNumeroDocumento());
		stringBuilder.append(", Tipo de documento: " + this.getTipoDocumento());
		stringBuilder.append(", Nome Completo: " + this.getNomeCompleto());
		stringBuilder.append(", Senha: " + this.getSenha());
		stringBuilder.append(", Adm: " + this.isAdm());
		if(this.getUltimoAcesso() != null) {
			stringBuilder.append(", ??ltimo acesso: " + this.getUltimoAcesso().format(formatacao));			
		}else {
			stringBuilder.append(", ??ltimo acesso: --/--/--");						
		}
		
		return stringBuilder.toString();
	}

	
	
}
