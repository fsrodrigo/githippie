package br.com.oobj.githippie.menu;

import java.util.ArrayList;
import java.util.List;

public enum Menu {

	USUARIO(1),
	ORGANIZACAO(2),
	REPOSITORIO(3),
	TAREFA(4),
	MAIN(9),
	SAIR(0);

	private int menu;

	Menu(final int menu) {
		this.menu = menu;
	}
	public static List<Integer[]> getOptions() {
		List<Integer[]> menuOptions = new ArrayList<Integer[]>();
		// index 0 Menu principal
		Integer[] optionMain = {1,2,3,4,9,0};
		// index 1 Menu User
		Integer[] optionUser = {1,2,3,4,5,9,0};
		// index 2 Menu Organização
		Integer[] optionOrg = {1,2,3,4,5,9,0};
		// index 3 Menu Repositorio
		Integer[] optionRepo = {1,2,3,4,5,9,0};
		// index 4 Menu Tarefa
		Integer[] optionTarefa = {1,2,3,4,9,0};
		
		
		menuOptions.add(optionMain);
		menuOptions.add(optionUser);
		menuOptions.add(optionOrg);
		menuOptions.add(optionRepo);
		menuOptions.add(optionTarefa);
		
		
		return menuOptions;
	}
	
	public static void setMenu(int index) {
		switch (index) {
		case 1:
			Menu.USUARIO.getMenu();
			break;
		case 2:
			Menu.ORGANIZACAO.getMenu();
			break;
		case 3:
			Menu.REPOSITORIO.getMenu();
			break;
		case 4:
			Menu.TAREFA.getMenu();
			break;
		case 9:
			Menu.MAIN.getMenu();
			break;
		case 0:
			Menu.SAIR.getMenu();
			break;

		}
	}

	public void getMenu() {
		switch (menu) {
		case 9:
			System.out.println("==== Menu Principal ====");
			getOptionsMainMenu();
			PrincipalMenu.menu();
			break;
		case 1:
			System.out.println("==== Menu Usuário ====");
			getOptionsUserMenu();
			UsuarioMenu.menu();
			break;
		case 2:
			System.out.println("==== Menu Organização ====");
			getOptionsOrganizacaoMenu();
			OrganizacaoMenu.menu();
			break;
		case 3:
			System.out.println("==== Menu Repositório ====");
			getOptionsRepositorioMenu();
			RepositorioMenu.menu();
			break;
		case 4:
			System.out.println("==== Menu Tarefa ====");
			getOptionsTarefaMenu();
			TarefaMenu.menu();
			break;
		case 0:
			System.out.println("System Down!!!");
			System.exit(0);
			break;
		default:
			System.out.println("==== Menu Principal ====");
			getOptionsMainMenu();
			PrincipalMenu.menu();
		}
	}

	private void getOptionsMainMenu() {
		System.out.println("Digite o número referente à opção desejada: ");
		System.out.println("1 - Menu Usuário");
		System.out.println("2 - Menu Organização");
		System.out.println("3 - Menu Repositório");
		System.out.println("4 - Menu Tarefa");
		System.out.println("0 - SAIR");
	}
	
	private void getOptionsUserMenu() {
		System.out.println("Digite o número referente à opção desejada: ");
		System.out.println("1 - Novo Usuário");
		System.out.println("2 - Consultar Usuário");
		System.out.println("3 - Editar um Usuário");
		System.out.println("4 - Listar todos Usuários");
		System.out.println("5 - Desativar um Usuário");
		System.out.println("9 - Menu Principal");
		System.out.println("0 - SAIR");
	}
	
	private void getOptionsOrganizacaoMenu() {
		System.out.println("Digite o número referente à opção desejada: ");
		System.out.println("1 - Nova Organização");
		System.out.println("2 - Consultar Organização");
		System.out.println("3 - Editar uma Organização");
		System.out.println("4 - Listar todas Organizações");
		System.out.println("5 - Desativar uma Organização");
		System.out.println("9 - Menu Principal");
		System.out.println("0 - SAIR");
	}
	
	private void getOptionsRepositorioMenu() {
		System.out.println("Digite o número referente à opção desejada: ");
		System.out.println("1 - Novo Repositório");
		System.out.println("2 - Consultar um Repositório");
		System.out.println("3 - Editar um Repositório");
		System.out.println("4 - Listar todos Repositório");
		System.out.println("5 - Desativar um Repositório");
		System.out.println("9 - Menu Principal");
		System.out.println("0 - SAIR");
	}
	
	private void getOptionsTarefaMenu() {
		System.out.println("Digite o número referente à opção desejada: ");
		System.out.println("1 - Nova Tarefa");
		System.out.println("2 - Consultar Tarefa");
		System.out.println("3 - Listar Tarefas");
		System.out.println("4 - Editar Tarefa");
		System.out.println("9 - Menu Principal");
		System.out.println("0 - SAIR");
	}

}
