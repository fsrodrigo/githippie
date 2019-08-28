package br.com.oobj.githippie.model;

import java.util.HashMap;
import java.util.Map;

public enum Plano {

	FREE("F"), PRO("P"), ENTERPRISE("E");

	private String plano;
	private static Map<String, Plano> map = new HashMap<String, Plano>();
	
	static {
		for(Plano planoEnum : Plano.values()) {
			map.put(planoEnum.plano, planoEnum);
		}
	}

	Plano(String plano) {
		this.plano = plano;
	}

	public String getPlano() {
		return plano;
	}
	
	public static Plano obterPlano(String plano) {
		return map.get(plano);
	}

	public double definirPrecoPlano(Plano plano) {
		System.out.println("O Plano escolhido foi o: " + plano.getPlano());

		switch (plano) {
		case FREE:
			return 0.00;

		case PRO:
			return 25.00;

		case ENTERPRISE:
			return 50.00;
		}

		System.out.println("Não consegui definir seu plano, vou retornar 0.00");
		return 0.00;

	}
}
