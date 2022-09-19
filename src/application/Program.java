package application;

import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Scanner;

import model.service.DataBase;
import view.UI.MenuAdm;
import view.UI.MenuGestor;

public class Program {

	public static void main(String[] args) {
		Locale.setDefault(Locale.US);
		Scanner scanner = new Scanner(System.in);
		Integer option = 0;
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		DateTimeFormatter formatterWithHour = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
		
		// Variável mocada para definir se o usuário é ADM
		Boolean isAdm = false;
		
		try {
			// Se for ADM, vai entrar nesse menu
			if(isAdm) {
				Integer adm_options_number = 3;
				while(option != adm_options_number) {
					MenuAdm.principal.showMenu();
					option = scanner.nextInt();
					switch(option) {
						case 1:
							option = 0;
							while(option != 3) {
								MenuAdm.perfil.showMenu();
								option = scanner.nextInt();
								switch(option) {
									case 1:
										// Visualizar adm-perfil
										break;
									case 2:
										option = 0;
										while(option != 3) {
											MenuAdm.editar_perfil.showMenu();
											option = scanner.nextInt();
											switch(option) {
												case 1:
													// Editar adm-perfil
													break;
												case 2:
													// Editar adm-senha
													break;
												case 3:
													break;
													// Sair do menu
												default:
													System.out.println("[OPÇÃO INVÁLIDA]");
											}
										}
										// Reseta opção pra não sair do programa
										option = 0;
										break;
									case 3:
										// Sair do menu
										break;
									default:
										System.out.println("[OPÇÃO INVÁLIDA]");
								}
							}
							option = 0;
							break;
						case 2:
							option = 0;
							while(option != 6) {
								MenuAdm.gestor.showMenu();
								option = scanner.nextInt();
								switch(option) {
									case 1:
										// Criar gestor
										break;
									case 2:
										option = 0;
										while(option != 3) {
											MenuAdm.editar_gestor.showMenu();
											option = scanner.nextInt();
											switch(option) {
												case 1:
													// Editar informações do gestor 
													break;
												case 2:
													// Editar senha do gestor 
													break;
												case 3:
													// Sair do menu
													break;
												default:
													System.out.println("[OPÇÃO INVÁLIDA]");
											}
										}
										option = 0;
										break;
									case 3:
										// Excluir gestor
										break;
									case 4:
										// Listar todos gestores
										break;
									case 5:
										option = 0;
										while(option != 3) {
											MenuAdm.listar_gestor_por_categoria.showMenu();
											option = scanner.nextInt();
											switch(option) {
												case 1:
													// Listar gestores pelo número de documento
													break;
												case 2:
													// Listar gestores pelo nome
													break;
												case 3:
													// Sair do menu
													break;
												default:
													System.out.println("[OPÇÃO INVÁLIDA]");
											}
										}
										option = 0;
										break;
									case 6:
										break;
									default:
										System.out.println("[OPÇÃO INVÁLIDA]");
								}
							}
							option = 0;
							break;
						case 3:
							// Sair do menu
							break;
						default:
							System.out.println("[OPÇÃO INVÁLIDA]");
					}
				}
				option = 0;
			// Se não for ADM, vai entrar nesse menu
			}else {
				while(option != 4) {
					MenuGestor.principal.showMenu();
					option = scanner.nextInt();
					switch(option) {
						case 1:
							option = 0;
							while(option != 6) {
								MenuGestor.evento.showMenu();
								option = scanner.nextInt();
								switch(option) {
									case 1:
										// Criar evento
										break;
									case 2:
										// Editar evento
										break;
									case 3:
										// Excluir evento
										break;
									case 4:
										// Listar todos os eventos
										break;
									case 5:
										// Listar por nome
										break;
									case 6:
										// Sair do menu
										break;
									default:
										System.out.println("[OPÇÃO INVÁLIDA]");
										
								}
							}
							option = 0;
							break;
						case 2:
							option = 0;
							while(option != 8){
								MenuGestor.atividade.showMenu();
								option = scanner.nextInt();
								switch(option) {
									case 1:
										// Criar atividade
										break;
									case 2:
										// Check-in em atividade
										break;
									case 3:
										// Gerar certificado
										break;
									case 4:
										// Editar atividade
										break;
									case 5:
										// Excluir atividade
										break;
									case 6:
										// Listar todas as atividades
										break;
									case 7:
										// Listar atividades por nome
										break;
									case 8:
										// Sair do menu
										break;
									default:
										System.out.println("[OPÇÃO INVÁLIDA]");
								}
							}
							option = 0;
							break;
						case 3:
							option = 0;
							while(option != 5) {
								MenuGestor.participante.showMenu();
								option = scanner.nextInt();
								switch(option) {
									case 1:
										// Cadastrar participante
										break;
									case 2:
										// Editar participante
										break;
									case 3:
										// Excluir participante
										break;
									case 4:
										option = 0;
										while(option != 4) {
											MenuGestor.buscar_participante.showMenu();
											option = scanner.nextInt();
											switch(option) {
												case 1:
													// Listar todos participantes
													break;
												case 2:
													// Listar participante por número de documento
													break;
												case 3:
													// Listar participantes por nome
													break;
												case 4:
													// Sair do menu
													break;
												default:
													System.out.println("[OPÇÃO INVÁLIDA]");
											}
										}
										option = 0;
										break;
									case 5:
										// Sair do menu
										break;
									default:
										System.out.println("[OPÇÃO INVÁLIDA]");
								}
							}
							option = 0;
							break;
						case 4:
							break;
						default:
							System.out.println("[OPÇÃO INVÁLIDA]");
					}
				}
				option = 0;
			}
			
		}catch (RuntimeException error) {
			System.out.println(error);
		}finally {
			scanner.close();
			System.out.println("Desconectando do banco de dados...");
			DataBase.closeConnection();
			System.out.println("Desconectado!");
		}
		

	}
}