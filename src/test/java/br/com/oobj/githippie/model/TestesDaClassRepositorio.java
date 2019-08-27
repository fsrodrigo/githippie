package br.com.oobj.githippie.model;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestesDaClassRepositorio {
	
	Repositorio repositorio;

	@Test
	public void testarMetodoSetTipoRepositorioDaClassRepositrioPassar0EretornarPublico() {
		repositorio = new Repositorio();
		
		TipoRepositorio valorInformado = TipoRepositorio.PUBLICO;
		
		repositorio.setTipoRepositorio(valorInformado);
		
		String tipoRepositorioEsperado = "PUBLICO";
		TipoRepositorio tipoRepositorioRetornado = repositorio.getTipoRepositorio();
		
		assertEquals(tipoRepositorioEsperado, String.valueOf(tipoRepositorioRetornado));
	}
	
	@Test
	public void testarMetodoSetTipoRepositorioDaClassRepositrioPassar1EretornarPrivado() {
		repositorio = new Repositorio();
		
		TipoRepositorio valorInformado = TipoRepositorio.PRIVADO;
		
		repositorio.setTipoRepositorio(valorInformado);
		
		String tipoRepositorioEsperado = "PRIVADO";
		TipoRepositorio tipoRepositorioRetornado = repositorio.getTipoRepositorio();
		
		assertEquals(tipoRepositorioEsperado, String.valueOf(tipoRepositorioRetornado));
	}

}
