package br.com.oobj.githippie.menu;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import br.com.oobj.githippie.dao.impl.ManipularUsuarioImpl;
import br.com.oobj.githippie.model.Plano;
import br.com.oobj.githippie.model.Usuario;

public class UsuarioMenu implements IMenu {

	static ManipularUsuarioImpl usuarioDAO = new ManipularUsuarioImpl();

	public static void menu() {
		Integer opcaoDigitada = 0;
		Scanner ent = new Scanner(System.in);

		if (ent.hasNextInt()) {
			opcaoDigitada = ent.nextInt();
			if (Arrays.toString(Menu.getOptions().get(1)).contains(opcaoDigitada.toString())) {
				opcaoDigitada = Integer.parseInt("1".concat(opcaoDigitada.toString()));
				Menu.setMenu(opcaoDigitada);
			} else {
				System.out.println("OPÇÃO INVALIDA... " + opcaoDigitada);
				Menu.USUARIO.getMenu();
			}
		} else {
			System.out.println("OPÇÃO INVALIDA.");
			Menu.MAIN.getMenu();
		}
	}

	public static void cadastrarUsuario() {
		Scanner ent = new Scanner(System.in);
		Usuario usuarioNovo = new Usuario();
		Date date = new Date();
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

		System.out.println("Digite o Nome do Usuário: ");
		usuarioNovo.setNomeUsuario(ent.nextLine());

		System.out.println("Digite o Sobrenome do Usuário: ");
		usuarioNovo.setSobreNomeUsuario(ent.nextLine());

		System.out.println("Digite o Email do usuário: ");
		usuarioNovo.setEmailUsuario(ent.nextLine());

		System.out.println("Digite o Sexo do Usuário: \nM - Masculino \nF - Feminino ");
		Boolean flag = false;
		String sexo;
		String[] valoresAceitosSexo = { "M", "F" };
		while (!flag) {
			sexo = ent.nextLine().toUpperCase();
			if (!(Arrays.asList(valoresAceitosSexo).contains(sexo))) {
				System.out.println("O Valor digitado está incorreto (" + sexo
						+ ") Digite apenas uma das iniciais de: \nM - Masculino \nF - Feminino ");
			} else {
				usuarioNovo.setSexo(sexo.charAt(0));
				flag = true;
			}
		}
		System.out.println("Digite a URL do Linkedin: ");
		usuarioNovo.setUrlLinkedin(ent.nextLine());
		System.out.println("Digite o Plano desejado para esse usuário: \nF - FREE \nP - PRO \nE - ENTERPRISE ");
		flag = false;
		String plano;
		String[] valoresAceitosPlano = { "F", "P", "E" };
		while (!flag) {
			plano = ent.nextLine().toUpperCase();
			if (!(Arrays.asList(valoresAceitosPlano).contains(plano))) {
				System.out.println("O Valor digitado está incorreto (" + plano
						+ ") Digite apenas uma das iniciais de: \nF - FREE \nP - PRO \nE - ENTERPRISE ");
			} else {
				usuarioNovo.setPlano(Plano.obterPlano(plano));
				flag = true;
			}
		}
		usuarioNovo.setDataCadastro(dateFormat.format(date).toString());
		usuarioDAO.cadastrarUsuario(usuarioNovo);
		Menu.USUARIO.getMenu();
	}

	public static void buscarUsuario() {
		Scanner ent = new Scanner(System.in);

		System.out.println("Digite o Nome ou Email do usuário que deseja localizar: ");
		String valorAConsultar = ent.nextLine();

		int[] usuariosLocalizado = buscarPorEmail(valorAConsultar);
		if (usuariosLocalizado.length > 0) {
			Menu.USUARIO.getMenu();
		}
		usuariosLocalizado = buscarPorNome(valorAConsultar);
		if (usuariosLocalizado.length == 0) {
			System.out.println("Nenhum usuário localizado com os dados informados.");
		}
		Menu.USUARIO.getMenu();
	}

	public static void editarUsuario() {
		Scanner ent = new Scanner(System.in);

		System.out.println("Digite o Nome ou Email do usuário que deseja editar: ");
		String valorAConsultar = ent.nextLine();

		int[] usuariosLocalizado = buscarPorEmail(valorAConsultar);
		if (usuariosLocalizado.length == 1) {
			System.out.println("Confirmar a edição desse usuário? \nS - Sim \nN - Não");
			Boolean flag = false;
			String[] valoresAceitos = { "S", "N" };
			while (!flag) {
				String valorConfirmar = ent.nextLine().toUpperCase();
				if (!(Arrays.asList(valoresAceitos).contains(valorConfirmar))) {
					System.out.println("O Valor digitado está incorreto (" + valorConfirmar
							+ ") Digite apenas um dos valores abaixo: \nS - Sim \nN - Não");
				} else if (valorConfirmar.equals("N")) {
					Menu.USUARIO.getMenu();
				} else {
					Usuario usuarioEditado = editorUsuario(usuariosLocalizado[0]);
					flag = true;
				}
			}
			Menu.USUARIO.getMenu();
		}

		usuariosLocalizado = buscarPorNome(valorAConsultar);
		if (usuariosLocalizado.length == 1) {
			System.out.println("Confirmar a edição desse usuário? \nS - Sim \nN - Não");
			Boolean flag = false;
			String[] valoresAceitos = { "S", "N" };
			while (!flag) {
				String valorConfirmar = ent.nextLine().toUpperCase();
				if (!(Arrays.asList(valoresAceitos).contains(valorConfirmar))) {
					System.out.println("O Valor digitado está incorreto (" + valorConfirmar
							+ ") Digite apenas um dos valores abaixo: \nS - Sim \nN - Não");
				} else if (valorConfirmar.equals("N")) {
					Menu.USUARIO.getMenu();
				} else {
					Usuario usuarioEditado = editorUsuario(usuariosLocalizado[0]);
					flag = true;
				}
			}
		} else if (usuariosLocalizado.length > 1) {
			String valoresAceitos[] = new String[usuariosLocalizado.length];
			for (int i = 0; i < usuariosLocalizado.length; i++)
				valoresAceitos[i] = String.valueOf(usuariosLocalizado[i]);

			System.out.println("IDs encontrados: " + Arrays.toString(valoresAceitos)
					+ "\nInforme o ID do usuário que deseja editar: ");
			Boolean flag = false;
			while (!flag) {
				String idEditar = ent.nextLine().toUpperCase();
				if (!(Arrays.asList(valoresAceitos).contains(idEditar))) {
					System.out.println("O Valor digitado está incorreto (" + idEditar
							+ ") Digite apenas um dos valores abaixo: \n" + Arrays.toString(valoresAceitos));
				} else {
					Usuario usuarioEditado = editorUsuario(Integer.parseInt(idEditar));
					flag = true;
				}
			}
		} else {
			System.out.println("Nenhum usuário localizado com os dados informados.");
		}
		Menu.USUARIO.getMenu();
	}

	public static void deletarUsuario() {
		Scanner ent = new Scanner(System.in);

		System.out.println("Digite o Nome ou Email do usuário que deseja deletar: ");
		String valorAConsultar = ent.nextLine();

		int[] usuariosLocalizado = buscarPorEmail(valorAConsultar);
		if (usuariosLocalizado.length == 1) {
			System.out.println("Confirmar a exclusão desse usuário? \nS - Sim \nN - Não");
			Boolean flag = false;
			String[] valoresAceitos = { "S", "N" };
			while (!flag) {
				String valorConfirmar = ent.nextLine().toUpperCase();
				if (!(Arrays.asList(valoresAceitos).contains(valorConfirmar))) {
					System.out.println("O Valor digitado está incorreto (" + valorConfirmar
							+ ") Digite apenas um dos valores abaixo: \nS - Sim \nN - Não");
				} else if (valorConfirmar.equals("N")) {
					Menu.USUARIO.getMenu();
				} else {
					usuarioDAO.desativarusuario(usuarioDAO.consultarPorId(usuariosLocalizado[0]));
					flag = true;
				}
			}
			Menu.USUARIO.getMenu();
		}
		usuariosLocalizado = buscarPorNome(valorAConsultar);
		if (usuariosLocalizado.length == 1) {
			System.out.println("Confirmar a exclusão desse usuário? \nS - Sim \nN - Não");
			Boolean flag = false;
			String[] valoresAceitos = { "S", "N" };
			while (!flag) {
				String valorConfirmar = ent.nextLine().toUpperCase();
				if (!(Arrays.asList(valoresAceitos).contains(valorConfirmar))) {
					System.out.println("O Valor digitado está incorreto (" + valorConfirmar
							+ ") Digite apenas um dos valores abaixo: \nS - Sim \nN - Não");
				} else if (valorConfirmar.equals("N")) {
					Menu.USUARIO.getMenu();
				} else {
					usuarioDAO.desativarusuario(usuarioDAO.consultarPorId(usuariosLocalizado[0]));
					flag = true;
				}
			}
		} else if (usuariosLocalizado.length > 1) {
			String valoresAceitos[] = new String[usuariosLocalizado.length];
			for (int i = 0; i < usuariosLocalizado.length; i++)
				valoresAceitos[i] = String.valueOf(usuariosLocalizado[i]);

			System.out.println("IDs encontrados: " + Arrays.toString(valoresAceitos)
					+ "\nInforme o ID do usuário que deseja excluir: ");
			Boolean flag = false;
			while (!flag) {
				String idDeletar = ent.nextLine().toUpperCase();
				if (!(Arrays.asList(valoresAceitos).contains(idDeletar))) {
					System.out.println("O Valor digitado está incorreto (" + idDeletar
							+ ") Digite apenas um dos valores abaixo: \n" + Arrays.toString(valoresAceitos));
				} else {
					buscarPorID(Integer.parseInt(idDeletar));
					System.out.println("Confirmar a exclusão desse usuário? \nS - Sim \nN - Não");
					flag = false;
					String[] confirmarDelete = { "S", "N" };
					while (!flag) {
						String valorConfirmar = ent.nextLine().toUpperCase();
						if (!(Arrays.asList(confirmarDelete).contains(valorConfirmar))) {
							System.out.println("O Valor digitado está incorreto (" + valorConfirmar
									+ ") Digite apenas um dos valores abaixo: \nS - Sim \nN - Não");
						} else if (valorConfirmar.equals("N")) {
							Menu.USUARIO.getMenu();
						} else {
							usuarioDAO.desativarusuario(usuarioDAO.consultarPorId(usuariosLocalizado[0]));
							flag = true;
						}
					}
				}
			}
		} else {
			System.out.println("Nenhum usuário localizado com os dados informados.");
		}
		Menu.USUARIO.getMenu();
	}

	public static void listarUsuarios() {
		List<Usuario> usuarios = usuarioDAO.listarTodos();
		System.out.println("Usuários cadastrados Localizados: " + usuarios.size());

		for (Usuario usuario : usuarios) {
			System.out.println("ID: " + usuario.getIdUsuario() + "\nNome Usuário: " + usuario.getNomeUsuario()
					+ "\nEmail Usuário: " + usuario.getEmailUsuario() + "\n\n");
		}
		Menu.USUARIO.getMenu();
	}

	private static Usuario editorUsuario(int idUsuario) {
		Scanner ent = new Scanner(System.in);
		Usuario usuarioEditar = usuarioDAO.consultarPorId(idUsuario);

		System.out.println("Nome atual do usuário: " + usuarioEditar.getNomeUsuario() + " Digite o novo nome: ");
		usuarioEditar.setNomeUsuario(ent.nextLine());

		System.out.println(
				"Sobre Nome atual do usuário: " + usuarioEditar.getSobreNomeUsuario() + " Digite o novo Sobre Nome: ");
		usuarioEditar.setSobreNomeUsuario(ent.nextLine());

		System.out.println("Email atual do usuário: " + usuarioEditar.getEmailUsuario() + " Digite o novo Email: ");
		usuarioEditar.setEmailUsuario(ent.nextLine());

		System.out.println(
				"URL Linkedin atual do usuário: " + usuarioEditar.getUrlLinkedin() + " Digite a nova URL Linkedin: ");
		usuarioEditar.setUrlLinkedin(ent.nextLine());

		System.out.println("Sexo atual do usuário: " + usuarioEditar.getSexo() + " Digite o novo Sexo: ");
		usuarioEditar.setSexo(ent.nextLine().charAt(0));

		System.out.println("Plano atual do usuário: " + usuarioEditar.getPlano().name() + " Digite o novo Plano: ");
		usuarioEditar.setPlano(Plano.ENTERPRISE);

		usuarioDAO.editarUsuario(usuarioEditar);

		return usuarioEditar;
	}

	private static int[] buscarPorEmail(String email) {
		List<Usuario> usuarioEncontrado;
		usuarioEncontrado = usuarioDAO.consultarPorQualquerColuna("email_usuario", email);
		int[] ids = new int[usuarioEncontrado.size()];

		if (usuarioEncontrado.size() == 1) {
			System.out.println("Usuário(s) Encontrado(s): " + usuarioEncontrado.size());
			System.out.println("------------------------------------------------------------------------------");
			System.out.println("ID usuário: " + usuarioEncontrado.get(0).getIdUsuario());
			System.out.println("Nome usuário: " + usuarioEncontrado.get(0).getNomeUsuario() + " "
					+ usuarioEncontrado.get(0).getSobreNomeUsuario());
			System.out.println("Email usuário: " + usuarioEncontrado.get(0).getEmailUsuario());
			System.out.println("Sexo usuário: " + usuarioEncontrado.get(0).getSexo());
			System.out.println("URL Linkedin usuário: " + usuarioEncontrado.get(0).getUrlLinkedin());
			System.out.println("Plano usuário: " + usuarioEncontrado.get(0).getPlano().name());
			System.out.println("Data de cadastro usuário: " + usuarioEncontrado.get(0).getDataCadastro());
			System.out.println("------------------------------------------------------------------------------");
			ids[0] = usuarioEncontrado.get(0).getIdUsuario();
		}
		return ids;
	}

	private static int[] buscarPorNome(String nome) {
		List<Usuario> usuarioEncontrado;

		usuarioEncontrado = usuarioDAO.consultarPorQualquerColuna("nome_usuario", nome);
		int[] ids = new int[usuarioEncontrado.size()];
		if (usuarioEncontrado.size() == 0) {
			return ids;
		}
		System.out.println("Usuário(s) Encontrado(s): " + usuarioEncontrado.size());
		int x = 0;
		for (Usuario usuario : usuarioEncontrado) {
			System.out.println("------------------------------------------------------------------------------");
			System.out.println("ID usuário: " + usuario.getIdUsuario());
			System.out.println("Nome usuário: " + usuario.getNomeUsuario() + " " + usuario.getSobreNomeUsuario());
			System.out.println("Email usuário: " + usuario.getEmailUsuario());
			System.out.println("Sexo usuário: " + usuario.getSexo());
			System.out.println("URL Linkedin usuário: " + usuario.getUrlLinkedin());
			System.out.println("Plano usuário: " + usuario.getPlano().name());
			System.out.println("Data de cadastro usuário: " + usuario.getDataCadastro());
			System.out.println("------------------------------------------------------------------------------");
			ids[x] = usuario.getIdUsuario();
			x++;
		}
		return ids;
	}

	private static int[] buscarPorID(int idUsuario) {
		Usuario usuarioEncontrado;
		usuarioEncontrado = usuarioDAO.consultarPorId(idUsuario);
		int[] ids = new int[1];

		if (usuarioEncontrado.getIdUsuario() > 0) {
			System.out.println("------------------------------------------------------------------------------");
			System.out.println("ID usuário: " + usuarioEncontrado.getIdUsuario());
			System.out.println("Nome usuário: " + usuarioEncontrado.getNomeUsuario() + " "
					+ usuarioEncontrado.getSobreNomeUsuario());
			System.out.println("Email usuário: " + usuarioEncontrado.getEmailUsuario());
			System.out.println("Sexo usuário: " + usuarioEncontrado.getSexo());
			System.out.println("URL Linkedin usuário: " + usuarioEncontrado.getUrlLinkedin());
			System.out.println("Plano usuário: " + usuarioEncontrado.getPlano().name());
			System.out.println("Data de cadastro usuário: " + usuarioEncontrado.getDataCadastro());
			System.out.println("------------------------------------------------------------------------------");
			ids[0] = usuarioEncontrado.getIdUsuario();
		}
		return ids;
	}
}
