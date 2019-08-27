package br.com.oobj.githippie.menu;

import static org.hamcrest.CoreMatchers.containsString;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Arrays;

public class PrincipalMenu implements IMenu {

	@SuppressWarnings("unlikely-arg-type")
	public static void menu() {
		Integer opcaoDigitada = 0;
		Scanner ent = new Scanner(System.in);
		

		if (ent.hasNextInt()) {
			opcaoDigitada = ent.nextInt();			
			
			if (Arrays.toString(Menu.getOptions().get(0)).contains(opcaoDigitada.toString())){
				Menu.setMenu(opcaoDigitada);
			} else
				System.out.println("OPÇÃO INVALIDA.");
				Menu.MAIN.getMenu();

		} else {
			System.out.println("OPÇÃO INVALIDA.");
			Menu.MAIN.getMenu();
		}
	}
}
