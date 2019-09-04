package br.com.oobj.githippie.menu;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
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

	public static void menuEditar() {
		Integer opcaoDigitada = 0;
		Scanner ent = new Scanner(System.in);

		if (ent.hasNextInt()) {
			opcaoDigitada = ent.nextInt();
			if (Arrays.toString(Menu.getOptions().get(4)).contains(opcaoDigitada.toString())) {
				opcaoDigitada = Integer.parseInt("33".concat(opcaoDigitada.toString()));
				Menu.setMenu(opcaoDigitada);
			} else {
				System.out.println("OPÇÃO INVALIDA.");
				Menu.REPOSITORIO_EDITAR.getMenu();
			}
		} else if (!ent.hasNextInt()) {
			System.out.println("OPÇÃO INVALIDA.");
			Menu.REPOSITORIO_EDITAR.getMenu();
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

	public static void consultarRepositorio() {
		Scanner ent = new Scanner(System.in);
		int registrosLocalizados = 0;
		System.out.println("Digite o Nome ou ID do repositório que deseja localizar: ");
		String valorAConsultar = ent.nextLine();
		Boolean ehNumero;
		try {
			Integer.parseInt(valorAConsultar);
			ehNumero = true;
		} catch (NumberFormatException e) {
			ehNumero = false;
		}

		if (ehNumero) {
			registrosLocalizados += buscarPorID(Integer.parseInt(valorAConsultar)).length;
			registrosLocalizados += buscarPorNome(valorAConsultar).length;
		} else {
			registrosLocalizados += buscarPorNome(valorAConsultar).length;
		}
		System.out.println("Registros encontrados: " + registrosLocalizados);
		if (registrosLocalizados == 0) {
			System.out.println("Nenhum usuário localizado com os dados informados.");
		}
		Menu.REPOSITORIO.getMenu();

	}

	public static void deletarRepositorio() {
		Scanner ent = new Scanner(System.in);
		List<int[]> idsConsultados = new ArrayList<int[]>();
		int registrosLocalizados = 0;
		int[] repositorioLocalizado;
		System.out.println("Digite o Nome ou ID do repositório que deseja deletar: ");
		String valorAConsultar = ent.nextLine();
		Boolean ehNumero;
		try {
			Integer.parseInt(valorAConsultar);
			ehNumero = true;
		} catch (NumberFormatException e) {
			ehNumero = false;
		}

		if (ehNumero) {
			repositorioLocalizado = buscarPorID(Integer.parseInt(valorAConsultar));
			idsConsultados.add(repositorioLocalizado);
			repositorioLocalizado = buscarPorNome(valorAConsultar);
			idsConsultados.add(repositorioLocalizado);

			registrosLocalizados = idsConsultados.get(0).length + idsConsultados.get(1).length;

			if (registrosLocalizados == 1) {
				if (idsConsultados.get(0).length == 1) {
					deletarRepositorioPorID(idsConsultados.get(0)[0]);
				} else
					deletarRepositorioPorID(idsConsultados.get(1)[0]);
			} else if (registrosLocalizados > 1) {
				Integer opcaoDigitada = 0;
				boolean flag = false;
				System.out.println(
						"Foi encontrado alguns registros com o valor informado.\n Digite o ID do repositório que deseja deletar: ");
				while (!flag) {
					if (ent.hasNextInt()) {
						opcaoDigitada = ent.nextInt();
						flag = true;
					} else if (!ent.hasNextInt()) {
						System.out.println("OPÇÃO INVALIDA. Digite Apenas o ID.");
					}
				}
				deletarRepositorioPorID(opcaoDigitada);
			}
			Menu.REPOSITORIO.getMenu();
		} else {

		}

	}

	private static void deletarRepositorioPorID(int idDeletar) {
		Scanner ent = new Scanner(System.in);
		Repositorio repositorioDelete = new Repositorio();
		repositorioDelete.setIdRepositorio(idDeletar);

		repositorioDelete = repositorioDAO.buscarPorID(repositorioDelete);
		if (!repositorioDelete.getDataCriacao().isEmpty()) {
			System.out.println("Atenção!!! \nO Repositório: ID " + idDeletar +" - "+ repositorioDelete.getNomeRepositorio()
					+ " será deletado. \nEssa ação não pode ser desfeita. \nConfirmar essa ação: \nS - Sim \nN - Não");
			if (confirmarOperacoesSimNao()) {
				repositorioDAO.desativarRepositorio(repositorioDelete);
			}
		} else {
			System.out.println("Falha ao deletar repositório: Não encontrado nenhum repositório com ID " + idDeletar);
		}
	}

	private static int[] buscarPorNome(String nomeRepositorio) {
		List<Repositorio> repositorioEncontrado;// = new Repositorio();

		repositorioEncontrado = repositorioDAO.buscarPorQualquerCampo("nome_repositorio", nomeRepositorio);
		int[] ids = new int[repositorioEncontrado.size()];
		if (repositorioEncontrado.size() == 0) {
			return ids;
		}
		System.out.println(
				"Repositório(s) Encontrado(s) com o nome " + nomeRepositorio + ": " + repositorioEncontrado.size());
		int x = 0;
		for (Repositorio repositorio : repositorioEncontrado) {
			printarRepositorio(repositorio);
			ids[x] = repositorio.getIdRepositorio();
			x++;
		}
		return ids;
	}

	private static int[] buscarPorID(int idRepositorio) {
		Repositorio repositorioEncontrado = new Repositorio();
		repositorioEncontrado.setIdRepositorio(idRepositorio);
		repositorioEncontrado = repositorioDAO.buscarPorID(repositorioEncontrado);
		int[] ids;// = new int[1];
		if (repositorioEncontrado.getIdRepositorio() > 0) {
			ids = new int[1];
			System.out.println("Repositório Encontrado com o ID " + idRepositorio + ": ");
			printarRepositorio(repositorioEncontrado);
			ids[0] = repositorioEncontrado.getIdRepositorio();

		} else {
			ids = new int[0];
		}
		return ids;
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
		int[] usuariosLocalizado = UsuarioMenu.buscarPorEmail(valorAConsultar);
		if (usuariosLocalizado.length == 1) {
			return usuarioDAO.consultarPorId(usuariosLocalizado[0]);
		}

		usuariosLocalizado = UsuarioMenu.buscarPorNome(valorAConsultar);
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

	private static Boolean confirmarOperacoesSimNao() {
		Scanner ent = new Scanner(System.in);
		Boolean flag = false;
		String[] confirmarDelete = { "S", "N" };
		while (!flag) {
			String valorConfirmar = ent.nextLine().toUpperCase();
			if (!(Arrays.asList(confirmarDelete).contains(valorConfirmar))) {
				System.out.println("O Valor digitado está incorreto (" + valorConfirmar
						+ ") Digite apenas um dos valores abaixo: \nS - Sim \nN - Não");
			} else if (valorConfirmar.equals("N")) {
				Menu.REPOSITORIO.getMenu();
			} else {
				flag = true;
			}
		}
		return true;
	}

	public static void printarRepositorio(Repositorio repositorio) {
		System.out.println("------------------------------------------------------------------------------");
		System.out.println("ID Repositório: " + repositorio.getIdRepositorio());
		System.out.println("Nome Repositório: " + repositorio.getNomeRepositorio());
		System.out.println("Descrição Repositório: " + repositorio.getDescricao());
		System.out.println("Usuário Owner do Repositório: " + repositorio.getUsuarioOwner().getIdUsuario() + " - "
				+ repositorio.getUsuarioOwner().getNomeUsuario() + " "
				+ repositorio.getUsuarioOwner().getSobreNomeUsuario());
		System.out.println("Tipo Repositório: " + repositorio.getTipoRepositorio().name());
		System.out.println("Data de criação do Repositório: " + repositorio.getDataCriacao());
		System.out.println("------------------------------------------------------------------------------");
	}
}
