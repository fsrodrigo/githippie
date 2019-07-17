package br.com.oobj.githippie.model;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestesDaClassRepositorio {
	
	Repositorio repositorio;

	@Test
	public void testarMetodoSetTipoRepositorioDaClassRepositrioPassar0EretornarPublico() {
		repositorio = new Repositorio();
		
		int valorInformado = 0;
		
		repositorio.setTipoRepositorio(valorInformado);
		
		String tipoRepositorioEsperado = "Público";
		String tipoRepositorioRetornado = repositorio.getTipoRepositorio();
		
		assertEquals(tipoRepositorioEsperado, tipoRepositorioRetornado);
	}
	
	@Test
	public void testarMetodoSetTipoRepositorioDaClassRepositrioPassar1EretornarPrivado() {
		repositorio = new Repositorio();
		
		int valorInformado = 1;
		
		repositorio.setTipoRepositorio(valorInformado);
		
		String tipoRepositorioEsperado = "Privado";
		String tipoRepositorioRetornado = repositorio.getTipoRepositorio();
		
		assertEquals(tipoRepositorioEsperado, tipoRepositorioRetornado);
	}

}
