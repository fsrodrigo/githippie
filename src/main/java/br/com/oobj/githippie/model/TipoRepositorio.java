package br.com.oobj.githippie.model;

public enum TipoRepositorio {

	PUBLICO("Publico"),
	PRIVADO("Privado");

	private String tipoRepositorio;

	TipoRepositorio(String tipoRepositorio) {
		this.tipoRepositorio = tipoRepositorio;
	}

	public String getTipoRepositorio() {
		return tipoRepositorio;
	}
}
