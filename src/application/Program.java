package application;

import java.sql.Connection;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import controller.dao.atividade.AtividadeDaoJDBC;
import controller.dao.evento.EventoDaoJDBC;
import controller.dao.participante.ParticipanteDaoJDBC;
import controller.dao.usuario.UsuarioDaoJDBC;
import model.entities.atividade.Atividade;
import model.entities.evento.Evento;
import model.entities.participante.Participante;
import model.entities.usuario.Adm;
import model.entities.usuario.Usuario;
import model.enums.TipoAtividade;
import model.enums.TipoDocumento;
import model.service.DataBase;
import view.UI.MenuAdm;
import view.UI.MenuGestor;

public class Program {

	public static void main(String[] args) {
		Locale.setDefault(Locale.US);
		Scanner scanner = new Scanner(System.in);
		Integer option = 0;
		Connection conn = DataBase.getConnection();
		
		// ------------ GESTOR ------------
		String senha1 = "", senha2 = "";
		String editGestor_numero_documento_old, gestor_numero_documento, gestor_nome;
		List<Usuario> gestores;
		List<Evento> eventos;
		List<Atividade> atividades;
		EventoDaoJDBC eventoDaoJDBC = new EventoDaoJDBC(conn);
		Participante participante = new Participante();
		Atividade atividade = new Atividade();
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		DateTimeFormatter formatterWithHour = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
		
		UsuarioDaoJDBC usuarioDaoJDBC = new UsuarioDaoJDBC(conn);
		AtividadeDaoJDBC atividadeDaoJDBC = new AtividadeDaoJDBC(conn);
		ParticipanteDaoJDBC participanteDaoJDBC = new ParticipanteDaoJDBC(conn);
		
		// ------------ ADM ------------
		// Variável mocada para definir se o usuário é ADM
		Boolean isAdm = false;
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
													editedAdm.setSenha(adm.getSenha());
													adm = editedAdm;
													break;
												case 2:
													// Editar adm-senha
													scanner = new Scanner(System.in);
													System.out.print("Senha antiga: ");
													senha1 = scanner.nextLine();
													if(senha1.equals(adm.getSenha())) {
														do {
															System.out.print("Senha: ");
															senha1 = scanner.nextLine();
															System.out.print("Senha novamente: ");
															senha2 = scanner.nextLine();
														}while(!senha1.equals(senha2));
														
														usuarioDaoJDBC.editarSenhaUsuario(
																adm.getNumeroDocumento(),
																new Usuario(
																		null,
																		null,
																		null,
																		senha1,
																		false,
																		null
																		)
																);
														adm.setSenha(senha1);
													}else {
														System.out.println("[Senha inválida!]");
													}
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
										scanner = new Scanner(System.in);
										Evento newEvento = new Evento();
										
										System.out.print("Nome do evento: ");
										newEvento.setNome(scanner.nextLine());
										
										System.out.print("Título: ");
										newEvento.setTitulo(scanner.nextLine());
										
										System.out.print("Descrição: ");
										newEvento.setDescricao(scanner.nextLine());
										
										System.out.print("Data de início (dd/MM/yyyy HH:mm): ");
										newEvento.setDataInicio(LocalDateTime.parse(scanner.nextLine(), formatterWithHour));
										
										System.out.print("Data de término (dd/MM/yyyy HH:mm): ");
										newEvento.setDataTermino(LocalDateTime.parse(scanner.nextLine(), formatterWithHour));
										
										eventoDaoJDBC.criarEvento(newEvento);
										break;
									case 2:
										// Editar evento
										scanner = new Scanner(System.in);
										Evento editEvento = new Evento();
										
										System.out.print("Id do evento: ");
										editEvento.setId(scanner.nextInt());
										
										System.out.print("Novo nome do evento: ");
										editEvento.setNome(scanner.nextLine());
										
										System.out.print("Novo título: ");
										editEvento.setTitulo(scanner.nextLine());
										
										System.out.print("Nova descrição: ");
										editEvento.setDescricao(scanner.nextLine());
										
										System.out.print("Nova data de início (dd/MM/yyyy HH:mm): ");
										editEvento.setDataInicio(LocalDateTime.parse(scanner.nextLine(), formatterWithHour));
										
										System.out.print("Nova data de término (dd/MM/yyyy HH:mm): ");
										editEvento.setDataTermino(LocalDateTime.parse(scanner.nextLine(), formatterWithHour));
										
										eventoDaoJDBC.editarEvento(editEvento);
										break;
									case 3:
										// Excluir evento
										scanner = new Scanner(System.in);

										System.out.print("Id do evento: ");
										Integer id_deleteEvent = scanner.nextInt();
										eventoDaoJDBC.excluirEvento(id_deleteEvent);
										break;
									case 4:
										// Listar todos os eventos
										eventos = eventoDaoJDBC.listarTodos();
										for(Evento evento: eventos) {
											System.out.println(evento);
										}
										break;
									case 5:
										// Listar por nome
										scanner = new Scanner(System.in);

										System.out.print("Nome: ");
										String eventName = scanner.nextLine();
										eventos = eventoDaoJDBC.listarTodosPorNome(eventName);
										for(Evento evento: eventos) {
											System.out.println(evento);
										}
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
							while(option != 9){
								MenuGestor.atividade.showMenu();
								option = scanner.nextInt();
								switch(option) {
									case 1:
										// Criar atividade
										Atividade newAtividade = new Atividade();
										
										scanner = new Scanner(System.in);
										System.out.print("Titulo da atividade: ");
										String titulo = scanner.nextLine();

										System.out.print("Descrição: ");
										String descricao = scanner.nextLine();

										System.out.print("Tipo de Atividade (OFICINA, PALESTRA, WORKSHOP): ");
										TipoAtividade tipo = TipoAtividade.valueOf(scanner.next().toUpperCase());
										
										scanner = new Scanner(System.in);
										System.out.print("Data de início (dd/MM/yyyy HH:mm): ");
										LocalDateTime data_inicio = LocalDateTime.parse(scanner.nextLine(), formatterWithHour);
										
										System.out.print("Data de término (dd/MM/yyyy HH:mm): ");
										LocalDateTime data_termino = LocalDateTime.parse(scanner.nextLine(), formatterWithHour);

										System.out.print("Duracao: ");
										Double duracao = scanner.nextDouble();
										
										scanner = new Scanner(System.in);
										System.out.print("Nome do responsavel: ");
										String nome = scanner.nextLine();
										
										System.out.print("Digite o ID do evento que ele pertence: ");
										int id_evento = scanner.nextInt();
										
										Evento newEvento = new Evento(id_evento, null, null, null, null, null);
										newAtividade = new Atividade(null, titulo, descricao, tipo, data_inicio, data_termino, duracao, nome, newEvento);
										atividadeDaoJDBC.criarAtividade(newAtividade);
										System.out.println("Cadrastado com sucesso!");
										break;
									case 2:
										// Check-in em atividade
										scanner = new Scanner(System.in);
										participante = new Participante();
										atividade = new Atividade();
										System.out.print("Id do participante:");
										participante.setId(scanner.nextInt());
										
										System.out.print("Id do atividade:");
										atividade.setId(scanner.nextInt());
										
										atividadeDaoJDBC.checkin(participante, atividade);
										break;
									case 3:
										// Gerar certificado
										break;
									case 4:
										// Editar atividade
										
										Atividade editAtividade = new Atividade();
										
										System.out.print("Id da atividade: ");
										editAtividade.setId(scanner.nextInt());
										
										scanner = new Scanner(System.in); System.out.print("Novo título: ");
										editAtividade.setTitulo(scanner.nextLine());
										
										System.out.print("Nova descrição: ");
										editAtividade.setDescricao(scanner.nextLine());
										
										System.out.print("Tipo de Atividade (OFICINA, PALESTRA, WORKSHOP): ");
										editAtividade.setTipoAtividade(TipoAtividade.valueOf(scanner.next().toUpperCase()));
										
										scanner = new Scanner(System.in);
										System.out.print("Nova data de início (dd/MM/yyyy HH:mm): ");
										editAtividade.setDataInicio(LocalDateTime.parse(scanner.nextLine(),
										formatterWithHour));
										
										System.out.print("Nova data de término (dd/MM/yyyy HH:mm): ");
										editAtividade.setDataTermino(LocalDateTime.parse(scanner.nextLine(),
										formatterWithHour));
										
										System.out.print("Duração: ");
										editAtividade.setDuracao(scanner.nextDouble());
										
										scanner = new Scanner(System.in); System.out.print("Novo Responsável: ");
										editAtividade.setNomeResponsavel(scanner.nextLine());
										
										System.out.print("ID do evento: "); Integer id_event = scanner.nextInt();
										newEvento = new Evento();
										newEvento.setId(id_event);
										editAtividade.setEvento(newEvento);
										
										atividadeDaoJDBC.editarAtividade(editAtividade);
										System.out.println("Atualizado!");
										break;
									case 5:
										// Excluir atividade
										System.out.print("Id da atividade que será excluída: ");
										Integer id_deleteAtividade = scanner.nextInt();
										atividadeDaoJDBC.excluirAtividade(id_deleteAtividade);
										System.out.println("Deletado com sucesso! ");
										break;
									case 6:
										//Inscrição
										scanner = new Scanner(System.in);
										participante = new Participante();
										atividade = new Atividade();
										System.out.print("Id do participante:");
										participante.setId(scanner.nextInt());
										
										System.out.print("Id do atividade:");
										atividade.setId(scanner.nextInt());
										
										atividadeDaoJDBC.inscricao(participante, atividade);
										break;
									case 7:
										// Listar todas as atividades
										atividades = atividadeDaoJDBC.listarTodos();
										for(Atividade one_atividade: atividades) {
											System.out.println(one_atividade);
										}
										break;
									case 8:
										// Listar atividades por nome
										scanner = new Scanner(System.in);

										System.out.print("Nome da atividade: ");
										String nomeAtividade = scanner.nextLine();
										atividades = atividadeDaoJDBC.listarTodosPorNome(nomeAtividade);
										for(Atividade one_atividade: atividades) {
											System.out.println(one_atividade);
										}

										break;
									case 9:
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
										scanner = new Scanner(System.in);
										System.out.print("Nome do Participante: ");
										participante.setNome(scanner.nextLine());
										
										System.out.print("Numero do documento: ");
										participante.setNumeroDocumento(scanner.nextLine());
																				
										System.out.print("Tipo de documento (CPF, MATRICULA, RG): ");
										participante.setTipoDocumento(TipoDocumento.valueOf(scanner.nextLine().toUpperCase()));
										
										participanteDaoJDBC.criarParticipante(participante);
										
										break;
									case 2:
										// Editar participante
										scanner = new Scanner(System.in);
										participante = new Participante();
										System.out.print("Id do Participante: ");
										participante.setId(scanner.nextInt());
										scanner.nextLine();
										
										System.out.print("Nome do Participante: ");
										participante.setNome(scanner.nextLine());
										
										System.out.print("Numero do documento: ");
										participante.setNumeroDocumento(scanner.nextLine());
																				
										System.out.print("Tipo de documento (CPF, MATRICULA, RG): ");
										participante.setTipoDocumento(TipoDocumento.valueOf(scanner.nextLine().toUpperCase()));
										
										participanteDaoJDBC.editarParticipante(participante);
										break;
									case 3:
										// Excluir participante
										scanner = new Scanner(System.in);
										participante = new Participante();
										System.out.print("Id do Participante: ");
										participante.setId(scanner.nextInt());
										
										participanteDaoJDBC.excluirParticipante(participante.getId());
										break;
									case 4:
										option = 0;
										while(option != 4) {
											MenuGestor.buscar_participante.showMenu();
											option = scanner.nextInt();
											switch(option) {
												case 1:
													// Listar todos participantes
													for(Participante one_participante:
															participanteDaoJDBC.listarTodos()
														) {
														System.out.println(one_participante);
													}
													break;
												case 2:
													// Listar participante por número de documento
													scanner = new Scanner(System.in);
													participante = new Participante();
													System.out.println("Numero de acesso: ");
													participante.setNumeroDocumento(scanner.nextLine());
													
													for(Participante one_participante: 
														participanteDaoJDBC.listarPorDocumento(participante.getNumeroDocumento())
													) {
														System.out.println(one_participante);
													}
													break;
												case 3:
													// Listar participantes por nome
													scanner = new Scanner(System.in);
													participante = new Participante();
													System.out.println("Nome: ");
													participante.setNome(scanner.nextLine());
													
													for(Participante one_participante: 
														participanteDaoJDBC.listarPorNome(participante.getNome())
													) {
														System.out.println(one_participante);
													}
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