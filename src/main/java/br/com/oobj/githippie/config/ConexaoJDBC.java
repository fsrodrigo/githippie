package br.com.oobj.githippie.config;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class ConexaoJDBC {
	
	private static String url;
	private static String port;
	private static String user;
	private static String pass;
	private static String db;

	public ConexaoJDBC() {
		Properties prop = new Properties();
		InputStream fis = ConexaoJDBC.class.getClassLoader().getResourceAsStream("database.properties");
		
		
		if(fis == null) {
			System.out.println("Não encontrei o arquivo de configuração do BD...");
			System.exit(0);
		}
		try {
			prop.load(fis);
			
			ConexaoJDBC.url = prop.getProperty("URL");
			ConexaoJDBC.port = prop.getProperty("PORT");
			ConexaoJDBC.user = prop.getProperty("USER");
			ConexaoJDBC.pass = prop.getProperty("PASS");
			ConexaoJDBC.db = prop.getProperty("DB");

		} catch (IOException e) {
			System.out.println("Erro ao ler o arquivo de configuração");
			e.printStackTrace();
		}
	}
	
	public Connection getConnection() {
		try {
			Connection conexao = DriverManager.getConnection("jdbc:postgresql://" + url + ":" + port + "/" + db, user,
					pass);
			return conexao;
		} catch (Exception e) {
			System.out.println("Erro ao obter a conexão... " + e);
			return null;
		}
	}

}
