package application;

import java.sql.Connection;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import controller.dao.usuario.UsuarioDaoJDBC;
import model.entities.usuario.Adm;
import model.entities.usuario.Usuario;
import model.enums.TipoDocumento;
import model.service.DataBase;
import view.UI.MenuAdm;
import view.UI.MenuGestor;

public class Program {

	public static void main(String[] args) {
		Locale.setDefault(Locale.US);
		Scanner scanner = new Scanner(System.in);
		Integer option = 0;
		
		// ------------ GESTOR ------------
		String senha1 = "", senha2 = "";
		String editGestor_numero_documento_old, gestor_numero_documento, gestor_nome;
		List<Usuario> gestores;
		
		Connection conn = DataBase.getConnection();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		DateTimeFormatter formatterWithHour = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
		
		UsuarioDaoJDBC usuarioDaoJDBC = new UsuarioDaoJDBC(conn);
		
		// ------------ ADM ------------
		// Variável mocada para definir se o usuário é ADM
		Boolean isAdm = true;
		// Variável mocada para definir ADM
		Adm adm = new Adm("123456", TipoDocumento.valueOf("CPF"), "usuario master", "123", true, null);
		List<Usuario> adms;
		String editAdm_numero_documento_old, adm_numero_documento, adm_nome;
		
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
										adms = usuarioDaoJDBC.listarTodosPorDocumento(true, adm.getNumeroDocumento());
										for(Usuario ADM: adms) {
											System.out.println(ADM);
										}
										break;
									case 2:
										option = 0;
										while(option != 3) {
											MenuAdm.editar_perfil.showMenu();
											option = scanner.nextInt();
											switch(option) {
												case 1:
													// Editar adm-perfil
													// Editar informações do gestor
													scanner = new Scanner(System.in);
													System.out.print("Numero do documento do ADM a ser editado: ");
													editAdm_numero_documento_old = scanner.nextLine();
													System.out.print("Novo numero do documento: ");
													String editAdm_numero_documento = scanner.nextLine();
													
													System.out.print("Novo tipo de documento (CPF, MATRICULA, RG): ");
													String editAdm_tipo_documento = scanner.nextLine().toUpperCase();
													
													System.out.print("Novo nome completo do gestor: ");
													String editAdm_nome = scanner.nextLine();
													
													Adm editedAdm = new Adm(
															editAdm_numero_documento,
															TipoDocumento.valueOf(editAdm_tipo_documento),
															editAdm_nome,
															null,
															true,
															null
														);
													usuarioDaoJDBC.editarUsuario(
															editAdm_numero_documento_old,
															editedAdm
													);
													adm = editedAdm;
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
										scanner = new Scanner(System.in);
										System.out.print("Numero do documento: ");
										String newGestor_numero_documento = scanner.nextLine();
										
										System.out.print("Tipo de documento (CPF, MATRICULA, RG): ");
										String newGestor_tipo_documento = scanner.nextLine().toUpperCase();
										
										System.out.print("Nome completo do gestor: ");
										String newGestor_nome = scanner.nextLine();
										
										do {
											System.out.print("Senha: ");
											senha1 = scanner.nextLine();
											System.out.print("Senha novamente: ");
											senha2 = scanner.nextLine();
										}while(!senha1.equals(senha2));
										
										usuarioDaoJDBC.criarUsuario(new Usuario(
												newGestor_numero_documento,
												TipoDocumento.valueOf(newGestor_tipo_documento),
												newGestor_nome,
												senha1,
												false,
												null
										));
										break;
									case 2:
										option = 0;
										while(option != 3) {
											MenuAdm.editar_gestor.showMenu();
											option = scanner.nextInt();
											switch(option) {
												case 1:
													// Editar informações do gestor
													scanner = new Scanner(System.in);
													System.out.print("Numero do documento do gestor a ser editado: ");
													editGestor_numero_documento_old = scanner.nextLine();
													System.out.print("Novo numero do documento: ");
													String editGestor_numero_documento = scanner.nextLine();
													
													System.out.print("Novo tipo de documento (CPF, MATRICULA, RG): ");
													String editGestor_tipo_documento = scanner.nextLine().toUpperCase();
													
													System.out.print("Novo nome completo do gestor: ");
													String editGestor_nome = scanner.nextLine();
													
													usuarioDaoJDBC.editarUsuario(
															editGestor_numero_documento_old,
															new Usuario(
																	editGestor_numero_documento,
																	TipoDocumento.valueOf(editGestor_tipo_documento),
																	editGestor_nome,
																	null,
																	false,
																	null
																)
													);
													break;
												case 2:
													// Editar senha do gestor
													scanner = new Scanner(System.in);
													System.out.print("Numero do documento do gestor a ser editado: ");
													editGestor_numero_documento_old = scanner.nextLine();
													
													do {
														System.out.print("Senha: ");
														senha1 = scanner.nextLine();
														System.out.print("Senha novamente: ");
														senha2 = scanner.nextLine();
													}while(!senha1.equals(senha2));
													
													usuarioDaoJDBC.editarSenhaUsuario(
															editGestor_numero_documento_old,
															new Usuario(
																	null,
																	null,
																	null,
																	senha1,
																	false,
																	null
																)
													);
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
										scanner = new Scanner(System.in);
										System.out.print("Numero do documento do gestor a ser editado: ");
										String deleteGestor_numero_documento = scanner.nextLine();
										
										usuarioDaoJDBC.excluirUsuario(deleteGestor_numero_documento);
										break;
									case 4:
										// Listar todos gestores
										gestores = usuarioDaoJDBC.listarTodos(false);
										for(Usuario gestor: gestores) {
											System.out.println(gestor);
										}
										break;
									case 5:
										option = 0;
										while(option != 3) {
											MenuAdm.listar_gestor_por_categoria.showMenu();
											option = scanner.nextInt();
											switch(option) {
												case 1:
													// Listar gestores pelo número de documento
													scanner = new Scanner(System.in);
													System.out.print("Número de acesso: ");
													gestor_numero_documento = scanner.nextLine();
													
													gestores = usuarioDaoJDBC.listarTodosPorDocumento(false, gestor_numero_documento);
													for(Usuario gestor: gestores) {
														System.out.println(gestor);
													}
													break;
												case 2:
													// Listar gestores pelo nome
													scanner = new Scanner(System.in);
													System.out.print("Nome: ");
													gestor_nome = scanner.nextLine();
													
													gestores = usuarioDaoJDBC.listarTodosPorNome(false, gestor_nome);
													for(Usuario gestor: gestores) {
														System.out.println(gestor);
													}
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
										// Cadastrar participante INCOMPLETO!!!
										scanner = new Scanner(System.in);
										System.out.print("Nome do Participante: ");
										String newParticipante_nome = scanner.nextLine();
										
										System.out.print("Numero do documento: ");
										String newParticipante_numero_documento = scanner.nextLine();
																				
										System.out.print("Tipo de documento (CPF, MATRICULA, RG): ");
										String newParticipante_tipo_documento = scanner.nextLine().toUpperCase();
										
//										newParticipante = new Participante(null, descricao, preco);
//										participanteDao.CriarParticipante(newParticipante);
//										System.out.println("Cadrastado com sucesso!");
										
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