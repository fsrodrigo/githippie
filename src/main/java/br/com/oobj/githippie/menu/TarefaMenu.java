package br.com.oobj.githippie.menu;

import java.util.Arrays;
import java.util.Scanner;

public class TarefaMenu implements IMenu{

	public static void menu() {
		Integer opcaoDigitada = 0;
		Scanner ent = new Scanner(System.in);
		
		if (ent.hasNextInt()) {
			opcaoDigitada = ent.nextInt();
			if (Arrays.toString(Menu.getOptions().get(5)).contains(opcaoDigitada.toString())) {
				opcaoDigitada = Integer.parseInt("4".concat(opcaoDigitada.toString()));
				Menu.setMenu(opcaoDigitada);
			} else {
				System.out.println("OPÇÃO INVALIDA.");
				Menu.TAREFA.getMenu();
			}
		} else if (!ent.hasNextInt()) {
			System.out.println("OPÇÃO INVALIDA.");
			Menu.TAREFA.getMenu();
		}
	}
}
