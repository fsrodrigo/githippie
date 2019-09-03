package br.com.oobj.githippie.menu;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Scanner;

import br.com.oobj.githippie.dao.impl.ManipularRepositorioImpl;
import br.com.oobj.githippie.dao.impl.ManipularUsuarioImpl;
import br.com.oobj.githippie.model.Repositorio;
import br.com.oobj.githippie.model.TipoRepositorio;
import br.com.oobj.githippie.model.Usuario;
import br.com.oobj.githippie.util.Utils;

public class RepositorioMenu implements IMenu {

	private static ManipularUsuarioImpl usuarioDAO = new ManipularUsuarioImpl();
	private static ManipularRepositorioImpl repositorioDAO = new ManipularRepositorioImpl();

	public static void menu() {
		Integer opcaoDigitada = 0;
		Scanner ent = new Scanner(System.in);

		if (ent.hasNextInt()) {
			opcaoDigitada = ent.nextInt();
			if (Arrays.toString(Menu.getOptions().get(3)).contains(opcaoDigitada.toString())) {
				opcaoDigitada = Integer.parseInt("3".concat(opcaoDigitada.toString()));
				Menu.setMenu(opcaoDigitada);
			} else {
				System.out.println("OPÇÃO INVALIDA.");
				Menu.REPOSITORIO.getMenu();
			}
		} else if (!ent.hasNextInt()) {
			System.out.println("OPÇÃO INVALIDA.");
			Menu.REPOSITORIO.getMenu();
		}
	}

	public static void cadastrarRepositorio() {
		Scanner ent = new Scanner(System.in);
		Repositorio repositorioNovo = new Repositorio();
		Date date = new Date();
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		String valorDigitado;

		repositorioNovo.setDataCriacao(dateFormat.format(date).toString());
		Usuario usuarioOwner = null;
		while (usuarioOwner == null) {
			System.out.println("Selecione o Usuário Owner do Repositório. \nDigite o nome ou Email do usuário:");
			valorDigitado = ent.nextLine();

			if (valorDigitado.trim().equals("")) {
				usuarioOwner = obterUsuario(Utils.validarCampoObrigatorio());
			} else {
				usuarioOwner = obterUsuario(valorDigitado);
			}
		}
		repositorioNovo.setUsuarioOwner(usuarioOwner);

		System.out.println("Digite o Nome do Repositório: ");
		valorDigitado = ent.nextLine();
		if (valorDigitado.trim().equals("")) {
			repositorioNovo.setNomeRepositorio(Utils.validarCampoObrigatorio());
		} else
			repositorioNovo.setNomeRepositorio(valorDigitado);

		System.out.println("Digite a Descrição do Repositório: ");
		valorDigitado = ent.nextLine();
		if (valorDigitado.trim().equals("")) {
			repositorioNovo.setDescricao(Utils.validarCampoObrigatorio());
		} else
			repositorioNovo.setDescricao(valorDigitado);

		System.out.println("Selecione o Tipo desse Repositório: \n1 - Público \n2 - Privado");
		repositorioNovo.setTipoRepositorio(escolherTipoRepositorio(""));

		repositorioDAO.criarRepositorio(repositorioNovo);
		Menu.REPOSITORIO.getMenu();
	}

	private static TipoRepositorio escolherTipoRepositorio(String valorInicial) {
		Scanner ent = new Scanner(System.in);
		Boolean flag = false;
		String tipoRepositorio = valorInicial.toUpperCase();
		String[] valoresAceitosPlano = { "1", "2" };
		if (tipoRepositorio.trim().equals("")) {
			while (!flag) {
				tipoRepositorio = ent.nextLine().toUpperCase();
				if (!(Arrays.asList(valoresAceitosPlano).contains(tipoRepositorio))) {
					System.out.println("O Valor digitado está incorreto (" + tipoRepositorio
							+ ") Digite apenas uma das opções: \n1 - Público \n2 - Privado ");
				} else {
					flag = true;
				}
			}
		} else if (!(Arrays.asList(valoresAceitosPlano).contains(tipoRepositorio))) {
			System.out.println("O Valor digitado está incorreto (" + tipoRepositorio
					+ ") Digite apenas uma das opções: \n1 - Público \n2 - Privado ");
			tipoRepositorio = escolherTipoRepositorio("").getTipoRepositorio();
		}
		return TipoRepositorio.obterRepositorio(tipoRepositorio);
	}

	private static Usuario obterUsuario(String valorAConsultar) {
		Scanner ent = new Scanner(System.in);
		Usuario usuario = null;
		int[] usuariosLocalizado = Utils.buscarPorEmail(valorAConsultar);
		if (usuariosLocalizado.length == 1) {
			return usuarioDAO.consultarPorId(usuariosLocalizado[0]);
		}

		usuariosLocalizado = Utils.buscarPorNome(valorAConsultar);
		if (usuariosLocalizado.length == 1) {
			usuario = usuarioDAO.consultarPorId(usuariosLocalizado[0]);
		} else if (usuariosLocalizado.length > 1) {
			String valoresAceitos[] = new String[usuariosLocalizado.length];
			for (int i = 0; i < usuariosLocalizado.length; i++)
				valoresAceitos[i] = String.valueOf(usuariosLocalizado[i]);

			System.out.println("IDs encontrados: " + Arrays.toString(valoresAceitos)
					+ "\nInforme o ID do usuário que deseja selecionar: ");
			Boolean flag = false;
			while (!flag) {
				String idSelect = ent.nextLine().toUpperCase();
				if (!(Arrays.asList(valoresAceitos).contains(idSelect))) {
					System.out.println("O Valor digitado está incorreto (" + idSelect
							+ ") Digite apenas um dos valores abaixo: \n" + Arrays.toString(valoresAceitos));
				} else {
					usuario = usuarioDAO.consultarPorId(Integer.parseInt(idSelect));
					flag = true;
				}
			}
		} else {
			System.out.println("Nenhum usuário localizado com os dados informados.");
		}
		return usuario;

	}
}
