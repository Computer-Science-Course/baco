package model.entities.usuario;

import java.time.LocalDateTime;

import model.enums.TipoDocumento;

public class Usuario {
	private String numero_documento;
	private TipoDocumento tipo_documento;
	private String nome_completo;
	private String senha;
	private Boolean isAdm;
	private LocalDateTime ultimo_acesso;
	
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
	public String getNumero_documento() {
		return numero_documento;
	}
	public void setNumero_documento(String numero_documento) {
		this.numero_documento = numero_documento;
	}
	public TipoDocumento getTipo_documento() {
		return tipo_documento;
	}
	public void setTipo_documento(TipoDocumento tipo_documento) {
		this.tipo_documento = tipo_documento;
	}
	public String getNome_completo() {
		return nome_completo;
	}
	public void setNome_completo(String nome_completo) {
		this.nome_completo = nome_completo;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public Boolean getIsAdm() {
		return isAdm;
	}
	public void setIsAdm(Boolean isAdm) {
		this.isAdm = isAdm;
	}
	public LocalDateTime getUltimo_acesso() {
		return ultimo_acesso;
	}
	public void setUltimo_acesso(LocalDateTime ultimo_acesso) {
		this.ultimo_acesso = ultimo_acesso;
	}
	
	
}
