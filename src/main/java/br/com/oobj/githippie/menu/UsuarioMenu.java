package br.com.oobj.githippie.menu;

import java.util.Arrays;
import java.util.Scanner;

public class UsuarioMenu implements IMenu {

	public static void menu() {
		Integer opcaoDigitada = 0;
		Scanner ent = new Scanner(System.in);

		if (ent.hasNextInt()) {
			opcaoDigitada = ent.nextInt();
			if (Arrays.toString(Menu.getOptions().get(1)).contains(opcaoDigitada.toString())) {
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

}
