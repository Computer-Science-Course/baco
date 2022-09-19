package model.entities.login;

public class Login {
	private Boolean autorizado;

	public Login(Boolean autorizado) {
		this.autorizado = autorizado;
	}
	
	public Boolean isAutorizado() {
		return autorizado;
	}

	public void setAutorizado(Boolean autorizado) {
		this.autorizado = autorizado;
	}
	
	
}
