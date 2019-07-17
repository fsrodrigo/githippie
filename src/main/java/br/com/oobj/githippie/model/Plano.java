package br.com.oobj.githippie.model;

public enum Plano {

	FREE("free"), PRO("pro"), ENTERPRISE("enterprise");

	private String plano;

	Plano(String plano) {
		this.plano = plano;
	}

	public String getPlano() {
		return plano;
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
