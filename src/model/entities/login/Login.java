package model.entities.login;

public class Login {
	
	private Boolean autorizado;
	private String nick; 
	private String senha;	
	
	public Login() {}
	
	
	public String getNick() {
		return nick;
	}


	public void setNick(String nick) {
		this.nick = nick;
	}


	public String getSenha() {
		return senha;
	}


	public void setSenha(String senha1) {
		this.senha = senha1;
	}


	public Boolean isAutorizado() {
		return autorizado;
	}

	public void setAutorizado(Boolean autorizado) {
		this.autorizado = autorizado;
	}
	
	
}
