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
import br.com.oobj.githippie.util.Utils;

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
				System.out.println("OP��O INVALIDA... " + opcaoDigitada);
				Menu.USUARIO.getMenu();
			}
		} else {
			System.out.println("OP��O INVALIDA.");
			Menu.MAIN.getMenu();
		}
	}

	// Menu 1-1
	public static void cadastrarUsuario() {
		Scanner ent = new Scanner(System.in);
		Usuario usuarioNovo = new Usuario();
		Date date = new Date();
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		String valorDigitado;

		System.out.println("Digite o Nome do Usu�rio: ");
		valorDigitado = ent.nextLine();
		if (valorDigitado.trim().equals("")) {
			usuarioNovo.setNomeUsuario(Utils.validarCampoObrigatorio());
		} else
			usuarioNovo.setNomeUsuario(valorDigitado);

		System.out.println("Digite o Sobrenome do Usu�rio: ");
		valorDigitado = ent.nextLine();
		if (valorDigitado.trim().equals("")) {
			usuarioNovo.setSobreNomeUsuario(Utils.validarCampoObrigatorio());
		} else
			usuarioNovo.setSobreNomeUsuario(valorDigitado);

		System.out.println("Digite o Email do usu�rio: ");
		valorDigitado = ent.nextLine();
		if (valorDigitado.trim().equals("")) {
			usuarioNovo.setEmailUsuario(Utils.validarCampoObrigatorio());
		} else
			usuarioNovo.setEmailUsuario(valorDigitado);

		System.out.println("Digite o Sexo do Usu�rio: \nM - Masculino \nF - Feminino ");
		usuarioNovo.setSexo(escolherSexoDigitado(""));

		System.out.println("Digite a URL do Linkedin: ");
		usuarioNovo.setUrlLinkedin(ent.nextLine());

		System.out.println("Digite o Plano desejado para esse usu�rio: \nF - FREE \nP - PRO \nE - ENTERPRISE ");
		usuarioNovo.setPlano(escolherPlanoDigitado(""));

		usuarioNovo.setDataCadastro(dateFormat.format(date).toString());
		usuarioDAO.cadastrarUsuario(usuarioNovo);
		Menu.USUARIO.getMenu();
	}

	// Menu 1-2
	public static void buscarUsuario() {
		Scanner ent = new Scanner(System.in);

		System.out.println("Digite o Nome ou Email do usu�rio que deseja localizar: ");
		String valorAConsultar = ent.nextLine();

		int[] usuariosLocalizado = Utils.buscarPorEmail(valorAConsultar);
		if (usuariosLocalizado.length > 0) {
			Menu.USUARIO.getMenu();
		}
		usuariosLocalizado = Utils.buscarPorNome(valorAConsultar);
		if (usuariosLocalizado.length == 0) {
			System.out.println("Nenhum usu�rio localizado com os dados informados.");
		}
		Menu.USUARIO.getMenu();
	}

	// Menu 1-3
	public static void editarUsuario() {
		Scanner ent = new Scanner(System.in);

		System.out.println("Digite o Nome ou Email do usu�rio que deseja editar: ");
		String valorAConsultar = ent.nextLine();

		int[] usuariosLocalizado = Utils.buscarPorEmail(valorAConsultar);
		if (usuariosLocalizado.length == 1) {
			System.out.println("Confirmar a edi��o desse usu�rio? \nS - Sim \nN - N�o");
			if (confirmarOperacoesSimNao()) {
				Usuario usuarioEditado = editorUsuario(usuariosLocalizado[0]);
			}
			Menu.USUARIO.getMenu();
		}

		usuariosLocalizado = Utils.buscarPorNome(valorAConsultar);
		if (usuariosLocalizado.length == 1) {
			System.out.println("Confirmar a edi��o desse usu�rio? \nS - Sim \nN - N�o");
			if (confirmarOperacoesSimNao()) {
				Usuario usuarioEditado = editorUsuario(usuariosLocalizado[0]);
			}
		} else if (usuariosLocalizado.length > 1) {
			String valoresAceitos[] = new String[usuariosLocalizado.length];
			for (int i = 0; i < usuariosLocalizado.length; i++)
				valoresAceitos[i] = String.valueOf(usuariosLocalizado[i]);

			System.out.println("IDs encontrados: " + Arrays.toString(valoresAceitos)
					+ "\nInforme o ID do usu�rio que deseja editar: ");
			Boolean flag = false;
			while (!flag) {
				String idEditar = ent.nextLine().toUpperCase();
				if (!(Arrays.asList(valoresAceitos).contains(idEditar))) {
					System.out.println("O Valor digitado est� incorreto (" + idEditar
							+ ") Digite apenas um dos valores abaixo: \n" + Arrays.toString(valoresAceitos));
				} else {
					Usuario usuarioEditado = editorUsuario(Integer.parseInt(idEditar));
					flag = true;
				}
			}
		} else {
			System.out.println("Nenhum usu�rio localizado com os dados informados.");
		}
		Menu.USUARIO.getMenu();
	}

	// Menu 1-4
	public static void deletarUsuario() {
		Scanner ent = new Scanner(System.in);

		System.out.println("Digite o Nome ou Email do usu�rio que deseja deletar: ");
		String valorAConsultar = ent.nextLine();

		int[] usuariosLocalizado = Utils.buscarPorEmail(valorAConsultar);
		if (usuariosLocalizado.length == 1) {
			System.out.println("Confirmar a exclus�o desse usu�rio? \nS - Sim \nN - N�o");
			if (confirmarOperacoesSimNao()) {
				usuarioDAO.desativarUsuario(usuarioDAO.consultarPorId(usuariosLocalizado[0]));
			}
			Menu.USUARIO.getMenu();
		}
		usuariosLocalizado = Utils.buscarPorNome(valorAConsultar);
		if (usuariosLocalizado.length == 1) {
			System.out.println("Confirmar a exclus�o desse usu�rio? \nS - Sim \nN - N�o");
			if (confirmarOperacoesSimNao()) {
				usuarioDAO.desativarUsuario(usuarioDAO.consultarPorId(usuariosLocalizado[0]));
			}
		} else if (usuariosLocalizado.length > 1) {
			String valoresAceitos[] = new String[usuariosLocalizado.length];
			for (int i = 0; i < usuariosLocalizado.length; i++)
				valoresAceitos[i] = String.valueOf(usuariosLocalizado[i]);

			System.out.println("IDs encontrados: " + Arrays.toString(valoresAceitos)
					+ "\nInforme o ID do usu�rio que deseja excluir: ");
			Boolean flag = false;
			while (!flag) {
				String idDeletar = ent.nextLine().toUpperCase();
				if (!(Arrays.asList(valoresAceitos).contains(idDeletar))) {
					System.out.println("O Valor digitado est� incorreto (" + idDeletar
							+ ") Digite apenas um dos valores abaixo: \n" + Arrays.toString(valoresAceitos));
				} else {
					buscarPorID(Integer.parseInt(idDeletar));
					System.out.println("Confirmar a exclus�o desse usu�rio? \nS - Sim \nN - N�o");
					if (confirmarOperacoesSimNao()) {
						usuarioDAO.desativarUsuario(usuarioDAO.consultarPorId(usuariosLocalizado[0]));
					}
				}
			}
		} else {
			System.out.println("Nenhum usu�rio localizado com os dados informados.");
		}
		Menu.USUARIO.getMenu();
	}

	// Menu 1-5
	public static void listarUsuarios() {
		List<Usuario> usuarios = usuarioDAO.listarTodos();
		System.out.println("Usu�rios cadastrados Localizados: " + usuarios.size());

		for (Usuario usuario : usuarios) {
			System.out.println("------------------------------------------------------------------------------");
			System.out.println("ID usu�rio: " + usuario.getIdUsuario());
			System.out.println("Nome usu�rio: " + usuario.getNomeUsuario() + " " + usuario.getSobreNomeUsuario());
			System.out.println("Email usu�rio: " + usuario.getEmailUsuario());
			System.out.println("Sexo usu�rio: " + usuario.getSexo());
			System.out.println("URL Linkedin usu�rio: " + usuario.getUrlLinkedin());
			System.out.println("Plano usu�rio: " + usuario.getPlano().name());
			System.out.println("Data de cadastro usu�rio: " + usuario.getDataCadastro());
			System.out.println("------------------------------------------------------------------------------");
		}
		Menu.USUARIO.getMenu();
	}

	private static Usuario editorUsuario(int idUsuario) {
		Scanner ent = new Scanner(System.in);
		Usuario usuarioEditar = usuarioDAO.consultarPorId(idUsuario);
		String valorDigitado;

		System.out.println("Nome atual do usu�rio: " + usuarioEditar.getNomeUsuario()
				+ " Digite o novo nome ou Enter para manter o atual: ");
		valorDigitado = ent.nextLine();
		if (!valorDigitado.equals("")) {
			usuarioEditar.setNomeUsuario(valorDigitado);
		}

		System.out.println("Sobre Nome atual do usu�rio: " + usuarioEditar.getSobreNomeUsuario()
				+ " Digite o novo Sobre Nome ou Enter para manter o atual: ");
		valorDigitado = ent.nextLine();
		if (!valorDigitado.equals("")) {
			usuarioEditar.setSobreNomeUsuario(valorDigitado);
		}

		System.out.println("Email atual do usu�rio: " + usuarioEditar.getEmailUsuario()
				+ " Digite o novo Email ou Enter para manter o atual: ");
		valorDigitado = ent.nextLine();
		if (!valorDigitado.equals("")) {
			usuarioEditar.setEmailUsuario(valorDigitado);
		}

		System.out.println("URL Linkedin atual do usu�rio: " + usuarioEditar.getUrlLinkedin()
				+ " Digite a nova URL Linkedin ou Enter para manter o atual: ");
		valorDigitado = ent.nextLine();
		if (!valorDigitado.equals("")) {
			usuarioEditar.setUrlLinkedin(valorDigitado);
		}

		System.out.println("Sexo atual do usu�rio: " + usuarioEditar.getSexo()
				+ " Digite a inicial do novo sexo: \nM - Masculino \nF - Feminino \nOu Enter para manter o atual: ");
		valorDigitado = ent.nextLine();
		if (!valorDigitado.equals("")) {
			usuarioEditar.setSexo(escolherSexoDigitado(valorDigitado));
		}

		System.out.println("Plano atual do usu�rio: " + usuarioEditar.getPlano().name()
				+ " Digite o novo Plano ou Enter para manter o atual F - FREE ou P - PRO ou E - ENTERPRISE : ");
		valorDigitado = ent.nextLine();
		if (!valorDigitado.equals("")) {
			usuarioEditar.setPlano(escolherPlanoDigitado(valorDigitado));
		}

		usuarioDAO.editarUsuario(usuarioEditar);

		return usuarioEditar;
	}

	private static Plano escolherPlanoDigitado(String valorInicial) {
		Scanner ent = new Scanner(System.in);
		Boolean flag = false;
		String plano = valorInicial.toUpperCase();
		String[] valoresAceitosPlano = { "F", "P", "E" };
		if (plano.trim().equals("")) {
			while (!flag) {
				plano = ent.nextLine().toUpperCase();
				if (!(Arrays.asList(valoresAceitosPlano).contains(plano))) {
					System.out.println("O Valor digitado est� incorreto (" + plano
							+ ") Digite apenas uma das iniciais de: \nF - FREE \nP - PRO \nE - ENTERPRISE ");
				} else {
					flag = true;
				}
			}
		} else if (!(Arrays.asList(valoresAceitosPlano).contains(plano))) {
			System.out.println("O Valor digitado est� incorreto (" + plano
					+ ") Digite apenas uma das iniciais de: \nF - FREE \nP - PRO \nE - ENTERPRISE ");
			plano = escolherPlanoDigitado("").getPlano();
		}
		return Plano.obterPlano(plano);
	}

	private static Character escolherSexoDigitado(String valorInicial) {
		Scanner ent = new Scanner(System.in);
		Boolean flag = false;
		String sexo = valorInicial.toUpperCase();
		String[] valoresAceitosSexo = { "M", "F" };
		if (sexo.trim().equals("")) {
			while (!flag) {
				sexo = ent.nextLine().toUpperCase();
				if (!(Arrays.asList(valoresAceitosSexo).contains(sexo))) {
					System.out.println("O Valor digitado est� incorreto (" + sexo
							+ ") Digite apenas uma das iniciais de: \nM - Masculino \nF - Feminino ");
				} else {
					flag = true;
				}
			}
		} else if (!(Arrays.asList(valoresAceitosSexo).contains(sexo))) {
			System.out.println("O Valor digitado est� incorreto (" + sexo
					+ ") Digite apenas uma das iniciais de: \nM - Masculino \nF - Feminino ");
			sexo = escolherSexoDigitado("").toString();
		}
		return sexo.charAt(0);
	}

	private static Boolean confirmarOperacoesSimNao() {
		Scanner ent = new Scanner(System.in);
		Boolean flag = false;
		String[] confirmarDelete = { "S", "N" };
		while (!flag) {
			String valorConfirmar = ent.nextLine().toUpperCase();
			if (!(Arrays.asList(confirmarDelete).contains(valorConfirmar))) {
				System.out.println("O Valor digitado est� incorreto (" + valorConfirmar
						+ ") Digite apenas um dos valores abaixo: \nS - Sim \nN - N�o");
			} else if (valorConfirmar.equals("N")) {
				Menu.USUARIO.getMenu();
			} else {
				flag = true;
			}
		}
		return true;
	}

	private static int[] buscarPorID(int idUsuario) {
		Usuario usuarioEncontrado;
		usuarioEncontrado = usuarioDAO.consultarPorId(idUsuario);
		int[] ids = new int[1];

		if (usuarioEncontrado.getIdUsuario() > 0) {
			System.out.println("------------------------------------------------------------------------------");
			System.out.println("ID usu�rio: " + usuarioEncontrado.getIdUsuario());
			System.out.println("Nome usu�rio: " + usuarioEncontrado.getNomeUsuario() + " "
					+ usuarioEncontrado.getSobreNomeUsuario());
			System.out.println("Email usu�rio: " + usuarioEncontrado.getEmailUsuario());
			System.out.println("Sexo usu�rio: " + usuarioEncontrado.getSexo());
			System.out.println("URL Linkedin usu�rio: " + usuarioEncontrado.getUrlLinkedin());
			System.out.println("Plano usu�rio: " + usuarioEncontrado.getPlano().name());
			System.out.println("Data de cadastro usu�rio: " + usuarioEncontrado.getDataCadastro());
			System.out.println("------------------------------------------------------------------------------");
			ids[0] = usuarioEncontrado.getIdUsuario();
		}
		return ids;
	}
}
