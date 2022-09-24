package view.UI;

import java.util.Arrays;

public final class MenuGestor {
	public final static UiMenu principal = new UiMenu(
			"B A C O\ngerenciador de eventos\nMenu principal",
			Arrays.asList("Evento", "Atividade", "Participante", "Sair")
	);
	
	// --------- EVENTO --------- 
	public final static UiMenu evento = new UiMenu(
			"Evento",
			Arrays.asList("Criar evento", "Editar evento", "Excluir evento", "Listar todos eventos", "Listar eventos por nome",  "Voltar")
	);
	
	// --------- ATIVIDADE --------- 
	public final static UiMenu atividade = new UiMenu(
			"Atividade",
			Arrays.asList("Criar atividade", "Check-in", "Gerar certificado", "Editar atividade", "Excluir atividade", "Inscrição", "Listar todas atividade", "Listar atividades por nome",  "Voltar")
	);
	
	public final static UiMenu certificado = new UiMenu(
			"Certificado",
			Arrays.asList("Por Atividade", "Por participante", "Voltar")
	);
	
	// --------- PARTICIPANTE --------- 
	public final static UiMenu participante = new UiMenu(
			"Participante",
			Arrays.asList("Cadastrar participante", "Editar participante", "Excluir participante", "Buscar participantes", "Voltar")
	);
	
	public final static UiMenu buscar_participante = new UiMenu(
			"Buscar participantes",
			Arrays.asList("Listar todos participante", "Listar por número de acesso", "Listar por nome", "Voltar")
	);
	
	
}
