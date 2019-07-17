package br.com.oobj.githippie.config;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConexaoJDBC {

	private final String URL = "127.0.0.1";
	private final String PORT = "5432";
	private final String USER = "postgres";
	private final String PASS = "oobj.postgres";
	private final String DB = "githippie";

	
	public Connection getConnection() {
		try {
			Connection conexao = DriverManager.getConnection("jdbc:postgresql://" + URL + ":" + PORT + "/" + DB, USER,
					PASS);
			return conexao;
		} catch (Exception e) {
			System.out.println("Erro ao obter a conexão... " + e);
			return null;
		}
	}

}
