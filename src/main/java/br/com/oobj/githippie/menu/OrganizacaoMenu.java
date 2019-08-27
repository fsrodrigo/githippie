package br.com.oobj.githippie.menu;

import java.util.Arrays;
import java.util.Scanner;

public class OrganizacaoMenu implements IMenu {

	public static void menu() {
		Integer opcaoDigitada = 0;
		Scanner ent = new Scanner(System.in);

		if (ent.hasNextInt()) {
			opcaoDigitada = ent.nextInt();
			if (Arrays.toString(Menu.getOptions().get(2)).contains(opcaoDigitada.toString())) {
				Menu.setMenu(opcaoDigitada);
			} else {
				System.out.println("OPÇÃO INVALIDA.");
				Menu.ORGANIZACAO.getMenu();
			}
		} else if (!ent.hasNextInt()) {
			System.out.println("OPÇÃO INVALIDA.");
			Menu.ORGANIZACAO.getMenu();
		}
	}

}
