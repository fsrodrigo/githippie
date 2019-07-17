package br.com.oobj.githippie.dao;

import static org.junit.Assert.*;

import java.sql.Connection;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import br.com.oobj.githippie.config.ConexaoJDBC;
import br.com.oobj.githippie.dao.impl.ManipularOrganizacaoImpl;
import br.com.oobj.githippie.dao.impl.ManipularUsuarioImpl;
import br.com.oobj.githippie.dao.impl.ManipularVinculoUsuarioOrganizacaoImpl;
import br.com.oobj.githippie.model.Organizacao;
import br.com.oobj.githippie.model.Usuario;
import br.com.oobj.githippie.util.Utils;

public class TestesDaoVinculoUserOrg {
	
	private Utils util = new Utils();
	private Organizacao organizacao = util.getOrganizacaoTest();
	
	private ManipularUsuarioImpl usuarioDAO = new ManipularUsuarioImpl();
	private ManipularOrganizacaoImpl organizacaoDAO = new ManipularOrganizacaoImpl();
	private ManipularVinculoUsuarioOrganizacao vinculoDAO = new ManipularVinculoUsuarioOrganizacaoImpl();

	@Before
	public void cadastrarUsuarioEOrganizacaoERegistroNaTabelaVinculo() {
		Usuario usuarioCadastrado = usuarioDAO.cadastrarUsuario(organizacao.getUsuarioOwner());
		organizacao.getUsuarioOwner().setIdUsuario(usuarioCadastrado.getIdUsuario());
		organizacaoDAO.cadastrarOrganizacao(organizacao);
	}	
	
	@Test
	public void teste_ConexaoJDBC() {
		try {
			ConexaoJDBC conexao = new ConexaoJDBC();
			conexao = new ConexaoJDBC();
			Connection conn = conexao.getConnection();
			System.out.println("Conexão com BD estabelecida... ");
			conn.close();
		} catch (Exception e) {
			System.out.println("Falha ao conectar no BD TESTE..." + e);
		}
	}

	@Test
	public void testeValidarSeExiste1RegistroNaTabelaVinculoReferenteAOrganizacaoTeste() {
		int qtdVinculos = vinculoDAO.listarTodosVinculos().size();
		int qtdEsperado = 1;
		assertEquals(qtdEsperado, qtdVinculos);
	}
	
	
	@After
	public void teste_CriadoParaTruncarATabelaAsTabelasAposExecucaoDeCadaTeste() {
		try {
			ConexaoJDBC conexao = new ConexaoJDBC();
			conexao = new ConexaoJDBC();
			Connection conn = conexao.getConnection();
			conn.prepareStatement("TRUNCATE TABLE usuarios CASCADE;").execute();
			conn.close();
		} catch (Exception e) {
			System.out.println("Falha ao truncar a tabela usuarios;" + e);
		}
		usuarioDAO = new ManipularUsuarioImpl();
		int qtdCadastros = usuarioDAO.listarTodos().size();
		int qtdCadastrosEsperado = 0;
		assertEquals(qtdCadastrosEsperado, qtdCadastros);
	}



}
