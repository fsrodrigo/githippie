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
		List<Usuario> usuarioEncontrado;

		System.out.println("Digite o Nome ou Email do usuário que deseja localizar: ");
		String valorAConsultar = ent.nextLine();

		usuarioEncontrado = usuarioDAO.consultarPorQualquerColuna("email_usuario", valorAConsultar);

		if (usuarioEncontrado.size() == 1) {
			System.out.println("Foi encontrado 1 usuário: ");
			System.out.println("ID usuário: " + usuarioEncontrado.get(0).getIdUsuario());
			System.out.println("Nome usuário: " + usuarioEncontrado.get(0).getNomeUsuario() + " "
					+ usuarioEncontrado.get(0).getSobreNomeUsuario());
			System.out.println("Email usuário: " + usuarioEncontrado.get(0).getEmailUsuario());
			System.out.println("Sexo usuário: " + usuarioEncontrado.get(0).getSexo());
			System.out.println("URL Linkedin usuário: " + usuarioEncontrado.get(0).getUrlLinkedin());
			System.out.println("Plano usuário: " + usuarioEncontrado.get(0).getPlano().getPlano());
			System.out.println("Data de cadastro usuário: " + usuarioEncontrado.get(0).getDataCadastro());

			Menu.USUARIO.getMenu();
		} else {
			usuarioEncontrado = usuarioDAO.consultarPorQualquerColuna("nome_usuario", valorAConsultar);
			if (usuarioEncontrado.size() == 0) {
				System.out.println("Nenhum usuário encontrado.");
				Menu.USUARIO.getMenu();
			}
			System.out.println("Usuário(s) Encontrado(s): ");
			for (Usuario usuario : usuarioEncontrado) {
				System.out.println("------------------------------------------------------------------------------");
				System.out.println("Foi encontrado 1 usuário: ");
				System.out.println("ID usuário: " + usuario.getIdUsuario());
				System.out.println("Nome usuário: " + usuario.getNomeUsuario() + " " + usuario.getSobreNomeUsuario());
				System.out.println("Email usuário: " + usuario.getEmailUsuario());
				System.out.println("Sexo usuário: " + usuario.getSexo());
				System.out.println("URL Linkedin usuário: " + usuario.getUrlLinkedin());
				System.out.println("Plano usuário: " + usuario.getPlano().getPlano());
				System.out.println("Data de cadastro usuário: " + usuario.getDataCadastro());
			}
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

}
