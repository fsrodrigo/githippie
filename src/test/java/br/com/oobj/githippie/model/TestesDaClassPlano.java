package br.com.oobj.githippie.model;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TestesDaClassPlano {
	
	Plano plano;

	@Test
	public void testarValorRetornadoAoEscolherPlanoFree() {
		plano = Plano.FREE;
		double valorRetornado = plano.definirPrecoPlano(plano);
		double valorEsperado = 0.00;
		assertEquals(valorEsperado, valorRetornado, 0.00);
	}
	@Test
	public void testarValorRetornadoAoEscolherPlanoPro() {
		plano = Plano.PRO;
		double valorRetornado = plano.definirPrecoPlano(plano);
		double valorEsperado = 25.00;
		assertEquals(valorEsperado, valorRetornado, 0.00);
	}
	@Test
	public void testarValorRetornadoAoEscolherPlanoEnterprise() {
		plano = Plano.ENTERPRISE;
		double valorRetornado = plano.definirPrecoPlano(plano);
		double valorEsperado = 50.00;
		assertEquals(valorEsperado, valorRetornado, 0.00);
	}

}
