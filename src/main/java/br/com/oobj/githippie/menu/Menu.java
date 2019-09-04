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
		// index 2 Menu Organiza��o
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

		// Menu Usu�rio
		case 1:
			System.out.println("\n\n==== Menu Usu�rio ====");
			getOptionsUserMenu();
			UsuarioMenu.menu();
			break;
		// Menu Organiza��o
		case 2:
			System.out.println("\n\n==== Menu Organiza��o ====");
			getOptionsOrganizacaoMenu();
			OrganizacaoMenu.menu();
			break;
			
		//======= Menu Reposit�rio =======
		case 3:
			System.out.println("\n\n==== Menu Reposit�rio ====");
			getOptionsRepositorioMenu();
			RepositorioMenu.menu();
			break;
		// Cadastrar um reposit�rio
		case 31:
			System.out.println("\n\n==== Cadastro de Reposit�rio ====");
			RepositorioMenu.cadastrarRepositorio();
			break;
		// Consultar um reposit�rio
		case 32:
			System.out.println("\n\n==== Consultar Reposit�rio ====");
			RepositorioMenu.consultarRepositorio();
			break;		
		// Consultar um reposit�rio
		case 33:
		case 338:
			System.out.println("\n\n==== Menu Editar Reposit�rio ====");
			getOptionsEditarRepositorioMenu();
			RepositorioMenu.menu();
			break;		
		// Deletar um reposit�rio
		case 34:
			System.out.println("\n\n==== Deletar Reposit�rio ====");
			RepositorioMenu.deletarRepositorio();
			break;			
			
		// ======= MENU TAREFA =======
		// Menu Tarefa
		case 4:
			System.out.println("\n\n==== Menu Tarefa ====");
			getOptionsTarefaMenu();
			TarefaMenu.menu();
			break;

		// ======= MENU USU�RIO =======

		// Cadastrar um usu�rio
		case 11:
			System.out.println("\n\n==== Cadastro de usu�rio ====");
			UsuarioMenu.cadastrarUsuario();
			break;
		// Buscar por um usu�rio espec�fico
		case 12:
			System.out.println("\n\n==== Buscar por um usu�rio espec�fico ====");
			UsuarioMenu.buscarUsuario();
			break;
		// Atualizar os dados de um usu�rio
		case 13:
			System.out.println("\n\n==== Atualizar os dados de um usu�rio ====");
			UsuarioMenu.editarUsuario();
			break;
		// Deletar uma usu�rio
		case 14:
			System.out.println("\n\n==== Deletar uma usu�rio ====");
			UsuarioMenu.deletarUsuario();
			break;
		// Listar todos as usu�rios
		case 15:
			System.out.println("\n\n==== Listar todos as usu�rios ====");
			UsuarioMenu.listarUsuarios();
			break;
		// Importar usu�rios
		case 16:
			System.out.println("\n\n==== Importar usu�rios ====");
			getOptionsTarefaMenu();
			TarefaMenu.menu();
			break;
		}
	}

	private void getOptionsMainMenu() {
		System.out.println("Digite o n�mero referente � op��o desejada: ");
		System.out.println("1 - Gerenciar Usu�rios");
		System.out.println("2 - Gerenciar Organiza��o");
		System.out.println("3 - Gerenciar Reposit�rio");
		System.out.println("4 - Gerenciar Tarefa");
		System.out.println("0 - SAIR");
	}

	private void getOptionsUserMenu() {
		System.out.println("Digite o n�mero referente � op��o desejada: ");
		System.out.println("1 - Cadastrar um usu�rio");
		System.out.println("2 - Buscar por um usu�rio espec�fico");
		System.out.println("3 - Atualizar os dados de um usu�rio");
		System.out.println("4 - Deletar uma usu�rio");
		System.out.println("5 - Listar todos as usu�rios");
		System.out.println("6 - Importar usu�rios");
		System.out.println("9 - Voltar ao menu principal");
		System.out.println("0 - SAIR");
	}

	private void getOptionsOrganizacaoMenu() {
		System.out.println("Digite o n�mero referente � op��o desejada: ");
		System.out.println("1 - Cadastrar uma organiza��o");
		System.out.println("2 - Buscar por uma organiza��o espec�fica");
		System.out.println("3 - Atualizar os dados de uma organiza��o");
		System.out.println("4 - Deletar uma organiza��o");
		System.out.println("5 - Listar todas as organiza��es");
		System.out.println("6 - Importar organiza��es");
		System.out.println("9 - Menu Principal");
		System.out.println("0 - SAIR");
	}

	private void getOptionsRepositorioMenu() {
		System.out.println("Digite o n�mero referente � op��o desejada: ");
		System.out.println("1 - Cadastrar um reposit�rio");
		System.out.println("2 - Buscar por um reposit�rio espec�fico");
		System.out.println("3 - Atualizar os dados de um reposit�rio");
		System.out.println("4 - Deletar um reposit�rio");
		System.out.println("5 - Listar todos os reposit�rios");
		System.out.println("6 - Importar reposit�rios");
		System.out.println("9 - Menu Principal");
		System.out.println("0 - SAIR");
	}
	
	private void getOptionsEditarRepositorioMenu() {
		System.out.println("Digite o n�mero referente � op��o desejada: ");
		System.out.println("1 - Trocar os dados do reposit�rio");
		System.out.println("2 - Adicionar um colaborador a esse reposit�rio");
		System.out.println("3 - Remover um colaborador desse reposit�rio");
		System.out.println("4 - Listar todos os colaboradores desse reposit�rio");
		System.out.println("5 - Criar uma tarefa para esse reposit�rio");
		System.out.println("6 - Listar todas as tarefas desse reposit�rio");
		System.out.println("7 - Importar tarefas");
		System.out.println("8 - Voltar ao menu anterior");
		System.out.println("9 - Menu Principal");
		System.out.println("0 - SAIR");
	}

	private void getOptionsTarefaMenu() {
		System.out.println("Digite o n�mero referente � op��o desejada: ");
		System.out.println("1 - Cadastrar um tipo de tarefa");
		System.out.println("2 - Buscar por um tipo de tarefa espec�fico");
		System.out.println("3 - Atualizar os dados de um tipo de tarefa");
		System.out.println("4 - Deletar um tipo de tarefa");
		System.out.println("5 - Listar todas os tipos de tarefas");
		System.out.println("6 - Importar tipos de tarefas");
		System.out.println("9 - Menu Principal");
		System.out.println("0 - SAIR");
	}

}
