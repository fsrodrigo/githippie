package br.com.oobj.githippie.menu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum Menu {

	USUARIO(1),
	USUARIO_CADASTRAR(11),
	USUARIO_CONSULTAR(12),
	USUARIO_EDITAR(13),
	USUARIO_DELETAR(14),
	USUARIO_LISTAR(15),
	USUARIO_IMPORTAR(16),
	ORGANIZACAO(2),
	REPOSITORIO(3),
	REPOSITORIO_CADASTRAR(31),
	REPOSITORIO_CONSULTAR(32),
	REPOSITORIO_EDITAR(33),
	REPOSITORIO_EDITAR_MENU(338),
	REPOSITORIO_DELETAR(34),
	TAREFA(4),
	MAIN(9),
	MAIN_USUARIO(19),
	MAIN_ORGANIZACAO(29),
	MAIN_REPOSITORIO(39),
	MAIN_REPOSITORIO_EDITAR(339),
	MAIN_TAREFA(49),
	SAIR(0),
	SAIR_USUARIO(10),
	SAIR_ORGANIZACAO(20),
	SAIR_REPOSITORIO(30),
	SAIR_REPOSITORIO_EDITAR(330),
	SAIR_TAREFA(40);

	private int menu;
	private static Map<Integer, Menu> map = new HashMap<Integer, Menu>();
	
	static {
		for(Menu menuEnum : Menu.values()) {
			map.put(menuEnum.menu, menuEnum);
		}
	}
	
	Menu(final int menu) {
		this.menu = menu;
	}
	
	public static void setMenu(int index) {
		map.get(index).getMenu();
	}
	
	public static List<Integer[]> getOptions() {
		List<Integer[]> menuOptions = new ArrayList<Integer[]>();
		// index 0 Menu principal
		Integer[] optionMain = { 1, 2, 3, 4, 9, 0 };
		// index 1 Menu User
		Integer[] optionUser = { 1, 2, 3, 4, 5, 6, 9, 0 };
		// index 2 Menu Organização
		Integer[] optionOrg = { 1, 2, 3, 4, 5, 6, 9, 0 };
		// index 3 Menu Repositorio
		Integer[] optionRepo = { 1, 2, 3, 4, 5, 6, 9, 0 };
		// index 4 Menu Repositorio
		Integer[] optionEditarRepo = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 0 };
		// index 5 Menu Tarefa
		Integer[] optionTarefa = { 1, 2, 3, 4, 5, 6, 9, 0 };

		menuOptions.add(optionMain);
		menuOptions.add(optionUser);
		menuOptions.add(optionOrg);
		menuOptions.add(optionRepo);
		menuOptions.add(optionEditarRepo);
		menuOptions.add(optionTarefa);

		return menuOptions;
	}


	public void getMenu() {
		switch (menu) {
		// Sair do Sistema
		case 0:
		case 10:
		case 20:
		case 30:
		case 330:
		case 40:
			System.out.println("System Down!!!");
			System.exit(0);
			break;
		// Voltar ao menu Principal
		case 9:
		case 19:
		case 29:
		case 39:
		case 339:
		case 49:
			System.out.println("\n\n==== Menu Principal ====");
			getOptionsMainMenu();
			PrincipalMenu.menu();
			break;

		// ======= MENU PRINCIPAL =======

		// Menu Usuário
		case 1:
			System.out.println("\n\n==== Menu Usuário ====");
			getOptionsUserMenu();
			UsuarioMenu.menu();
			break;
		// Menu Organização
		case 2:
			System.out.println("\n\n==== Menu Organização ====");
			getOptionsOrganizacaoMenu();
			OrganizacaoMenu.menu();
			break;
			
		//======= Menu Repositório =======
		case 3:
			System.out.println("\n\n==== Menu Repositório ====");
			getOptionsRepositorioMenu();
			RepositorioMenu.menu();
			break;
		// Cadastrar um repositório
		case 31:
			System.out.println("\n\n==== Cadastro de Repositório ====");
			RepositorioMenu.cadastrarRepositorio();
			break;
		// Consultar um repositório
		case 32:
			System.out.println("\n\n==== Consultar Repositório ====");
			RepositorioMenu.consultarRepositorio();
			break;		
		// Consultar um repositório
		case 33:
		case 338:
			System.out.println("\n\n==== Menu Editar Repositório ====");
			getOptionsEditarRepositorioMenu();
			RepositorioMenu.menu();
			break;		
		// Deletar um repositório
		case 34:
			System.out.println("\n\n==== Deletar Repositório ====");
			RepositorioMenu.deletarRepositorio();
			break;			
			
		// ======= MENU TAREFA =======
		// Menu Tarefa
		case 4:
			System.out.println("\n\n==== Menu Tarefa ====");
			getOptionsTarefaMenu();
			TarefaMenu.menu();
			break;

		// ======= MENU USUÁRIO =======

		// Cadastrar um usuário
		case 11:
			System.out.println("\n\n==== Cadastro de usuário ====");
			UsuarioMenu.cadastrarUsuario();
			break;
		// Buscar por um usuário específico
		case 12:
			System.out.println("\n\n==== Buscar por um usuário específico ====");
			UsuarioMenu.buscarUsuario();
			break;
		// Atualizar os dados de um usuário
		case 13:
			System.out.println("\n\n==== Atualizar os dados de um usuário ====");
			UsuarioMenu.editarUsuario();
			break;
		// Deletar uma usuário
		case 14:
			System.out.println("\n\n==== Deletar uma usuário ====");
			UsuarioMenu.deletarUsuario();
			break;
		// Listar todos as usuários
		case 15:
			System.out.println("\n\n==== Listar todos as usuários ====");
			UsuarioMenu.listarUsuarios();
			break;
		// Importar usuários
		case 16:
			System.out.println("\n\n==== Importar usuários ====");
			getOptionsTarefaMenu();
			TarefaMenu.menu();
			break;
		}
	}

	private void getOptionsMainMenu() {
		System.out.println("Digite o número referente à opção desejada: ");
		System.out.println("1 - Gerenciar Usuários");
		System.out.println("2 - Gerenciar Organização");
		System.out.println("3 - Gerenciar Repositório");
		System.out.println("4 - Gerenciar Tarefa");
		System.out.println("0 - SAIR");
	}

	private void getOptionsUserMenu() {
		System.out.println("Digite o número referente à opção desejada: ");
		System.out.println("1 - Cadastrar um usuário");
		System.out.println("2 - Buscar por um usuário específico");
		System.out.println("3 - Atualizar os dados de um usuário");
		System.out.println("4 - Deletar uma usuário");
		System.out.println("5 - Listar todos as usuários");
		System.out.println("6 - Importar usuários");
		System.out.println("9 - Voltar ao menu principal");
		System.out.println("0 - SAIR");
	}

	private void getOptionsOrganizacaoMenu() {
		System.out.println("Digite o número referente à opção desejada: ");
		System.out.println("1 - Cadastrar uma organização");
		System.out.println("2 - Buscar por uma organização específica");
		System.out.println("3 - Atualizar os dados de uma organização");
		System.out.println("4 - Deletar uma organização");
		System.out.println("5 - Listar todas as organizações");
		System.out.println("6 - Importar organizações");
		System.out.println("9 - Menu Principal");
		System.out.println("0 - SAIR");
	}

	private void getOptionsRepositorioMenu() {
		System.out.println("Digite o número referente à opção desejada: ");
		System.out.println("1 - Cadastrar um repositório");
		System.out.println("2 - Buscar por um repositório específico");
		System.out.println("3 - Atualizar os dados de um repositório");
		System.out.println("4 - Deletar um repositório");
		System.out.println("5 - Listar todos os repositórios");
		System.out.println("6 - Importar repositórios");
		System.out.println("9 - Menu Principal");
		System.out.println("0 - SAIR");
	}
	
	private void getOptionsEditarRepositorioMenu() {
		System.out.println("Digite o número referente à opção desejada: ");
		System.out.println("1 - Trocar os dados do repositório");
		System.out.println("2 - Adicionar um colaborador a esse repositório");
		System.out.println("3 - Remover um colaborador desse repositório");
		System.out.println("4 - Listar todos os colaboradores desse repositório");
		System.out.println("5 - Criar uma tarefa para esse repositório");
		System.out.println("6 - Listar todas as tarefas desse repositório");
		System.out.println("7 - Importar tarefas");
		System.out.println("8 - Voltar ao menu anterior");
		System.out.println("9 - Menu Principal");
		System.out.println("0 - SAIR");
	}

	private void getOptionsTarefaMenu() {
		System.out.println("Digite o número referente à opção desejada: ");
		System.out.println("1 - Cadastrar um tipo de tarefa");
		System.out.println("2 - Buscar por um tipo de tarefa específico");
		System.out.println("3 - Atualizar os dados de um tipo de tarefa");
		System.out.println("4 - Deletar um tipo de tarefa");
		System.out.println("5 - Listar todas os tipos de tarefas");
		System.out.println("6 - Importar tipos de tarefas");
		System.out.println("9 - Menu Principal");
		System.out.println("0 - SAIR");
	}

}
