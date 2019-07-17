package br.com.oobj.githippie.dao;

import static org.junit.Assert.assertEquals;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import br.com.oobj.githippie.config.ConexaoJDBC;
import br.com.oobj.githippie.dao.impl.ManipularOrganizacaoImpl;
import br.com.oobj.githippie.dao.impl.ManipularUsuarioImpl;
import br.com.oobj.githippie.model.Organizacao;
import br.com.oobj.githippie.model.Usuario;
import br.com.oobj.githippie.util.Utils;

public class TestesDaoOrganizacao {

	private Utils util = new Utils();
	private Organizacao organizacao = util.getOrganizacaoTest();
	private ManipularUsuarioImpl usuarioDAO = new ManipularUsuarioImpl();
	private ManipularOrganizacaoImpl organizacaoDAO = new ManipularOrganizacaoImpl();

	@Before
	public void cadastrarUsuarioOwnerDaOrganizacao() {
		Usuario usuarioCadastrado = usuarioDAO.cadastrarUsuario(organizacao.getUsuarioOwner());	
		organizacao.setUsuarioOwner(usuarioCadastrado);
		// usuario.setIdUsuario(UsuarioDAO.consultarPorQualquerColuna("data_cadastro",
		// usuario.getDataCadastro()).get(0).getIdUsuario());
	}

	@Test
	public void getOrganizacao() {
		assertEquals(organizacao.getNomeOrganizacao(), "Oobj TI");
	}

	@Test
	public void testeA_ConexaoJDBC() {
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
	public void testeB_MetodoResponsavelPorSalvarUmaNovaOrganizacao() {
		List<Organizacao> organizacaoCadastrada = new ArrayList<Organizacao>();
		System.out.println("Organização a ser cadastrada: " + organizacao.getNomeOrganizacao());
		organizacaoDAO.cadastrarOrganizacao(organizacao);

		organizacaoCadastrada = organizacaoDAO.consultarOrganizacaoPorQualquerColuna("data_criacao",
				organizacao.getDataCriacao());

		int qtdCadastros = organizacaoCadastrada.size();
		int qtdCadastrosEsperado = 1;
		assertEquals(qtdCadastrosEsperado, qtdCadastros);
	}

	@Test
	public void testeC_MetodoResponsavelPorEditarUmaOrganizacao() {
		List<Organizacao> organizacaoCadastrada = new ArrayList<Organizacao>();
		organizacaoDAO.cadastrarOrganizacao(organizacao);
		organizacaoCadastrada = organizacaoDAO.listarTodasOrganizacoes();
		organizacao = organizacaoCadastrada.get(0);

		System.out.println("Organização a ser Editada: " + organizacao.getNomeOrganizacao() + " ID: "
				+ organizacao.getIdOrganizacao());
		System.out.println("Sua atual descrição hé: " + organizacao.getDescricaoOrganizacao());

		organizacao.setDescricaoOrganizacao("EDITADO PELO TESTE");
		System.out.println("A nova descrição será: " + organizacao.getDescricaoOrganizacao());

		organizacaoDAO.editarOrganizacao(organizacao);

		organizacaoCadastrada = organizacaoDAO.consultarOrganizacaoPorQualquerColuna("descricao_organizacao",
				"EDITADO PELO TESTE");

		int qtdCadastros = organizacaoCadastrada.size();
		int qtdCadastrosEsperado = 1;
		assertEquals(qtdCadastrosEsperado, qtdCadastros);
	}

	@Test
	public void testeD_MetodoResponsavelPorConsultarUmaOrganizacaoPorID() {
		List<Organizacao> organizacaoCadastrada = new ArrayList<Organizacao>();
		organizacaoDAO.cadastrarOrganizacao(organizacao);
		organizacaoCadastrada = organizacaoDAO.listarTodasOrganizacoes();
		organizacao = organizacaoCadastrada.get(0);

		System.out.println("Organização a ser Consultada: " + organizacao.getNomeOrganizacao() + " ID: "
				+ organizacao.getIdOrganizacao());

		Organizacao orgRetornada = new Organizacao();
		orgRetornada = organizacaoDAO.consultarPorId(organizacao.getIdOrganizacao());

		organizacaoCadastrada.add(orgRetornada);

		int qtdCadastros = 0;
		if (orgRetornada.getIdOrganizacao() == organizacao.getIdOrganizacao())
			qtdCadastros = 1;

		int qtdCadastrosEsperado = 1;
		assertEquals(qtdCadastrosEsperado, qtdCadastros);
	}

	@Test
	public void testeE_MetodoResponsavelPorDesativarUmaOrganizacao() {
		List<Organizacao> organizacaoCadastrada = new ArrayList<Organizacao>();
		organizacaoDAO.cadastrarOrganizacao(organizacao);
		organizacaoCadastrada = organizacaoDAO.listarTodasOrganizacoes();
		organizacao = organizacaoCadastrada.get(0);

		System.out.println("Organização a ser Desativada: " + organizacao.getNomeOrganizacao() + " ID: "
				+ organizacao.getIdOrganizacao());

		organizacaoDAO.desativarOrganizacao(organizacao);

		organizacaoCadastrada = organizacaoDAO.consultarOrganizacaoPorQualquerColuna("ativo", "FALSE");

		int qtdCadastros = organizacaoCadastrada.size();
		int qtdCadastrosEsperado = 1;
		assertEquals(qtdCadastrosEsperado, qtdCadastros);
	}

	@After
	public void teste_CriadoParaTruncarATabelaUsuariosAposExcucaoDeTodosOsTestes() {
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
