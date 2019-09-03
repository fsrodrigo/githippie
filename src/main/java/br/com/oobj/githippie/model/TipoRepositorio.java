package br.com.oobj.githippie.model;

import java.util.HashMap;
import java.util.Map;

public enum TipoRepositorio {

	PUBLICO("1"), PRIVADO("2");

	private String tipoRepositorio;
	private static Map<String, TipoRepositorio> map = new HashMap<String, TipoRepositorio>();

	static {
		for (TipoRepositorio repoEnum : TipoRepositorio.values()) {
			map.put(repoEnum.tipoRepositorio, repoEnum);
		}
	}

	TipoRepositorio(String tipoRepositorio) {
		this.tipoRepositorio = tipoRepositorio;
	}

	public String getTipoRepositorio() {
		return tipoRepositorio;
	}

	public static TipoRepositorio obterRepositorio(String repositorio) {
		return map.get(repositorio);
	}
}
