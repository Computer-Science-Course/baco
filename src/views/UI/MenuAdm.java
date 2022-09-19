package views.UI;

import java.util.Arrays;

public final class MenuAdm {
	public final static UiMenu principal = new UiMenu(
			"B A C O\ngerenciador de eventos\nMenu principal",
			Arrays.asList("Perfil", "Gestor", "Sair")
	);
	
	// --------- PERFIL --------- 
	public final static UiMenu perfil = new UiMenu(
			"Perfil",
			Arrays.asList("Visualizar perfil", "Editar perfil", "Voltar")
	);
	
	public final static UiMenu editar_perfil = new UiMenu(
			"Editar peril",
			Arrays.asList("Editar perfil", "Editar senha", "Voltar")
	);
	
	// --------- GESTOR --------- 	
	public final static UiMenu gestor = new UiMenu(
			"Gestor",
			Arrays.asList("Criar gestor", "Editar gestor", "Excluir gestor", "Listar todos", "Listar por categoria",  "Voltar")
	);
	
	public final static UiMenu editar_gestor = new UiMenu(
			"Editar gestor",
			Arrays.asList("Editar informações do perfil", "Editar senha", "Voltar")
	);
	
	public final static UiMenu listar_gestor_por_categoria = new UiMenu(
			"Listar gestor por categoria",
			Arrays.asList("Por número de documento", "Por nome", "Voltar")
	);
}
