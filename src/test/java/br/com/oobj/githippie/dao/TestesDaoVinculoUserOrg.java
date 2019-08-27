package br.com.oobj.githippie.dao;

import static org.junit.Assert.assertEquals;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import br.com.oobj.githippie.config.ConexaoJDBC;
import br.com.oobj.githippie.dao.impl.ManipularOrganizacaoImpl;
import br.com.oobj.githippie.dao.impl.ManipularUsuarioImpl;
import br.com.oobj.githippie.dao.impl.ManipularVinculoUsuarioOrganizacaoImpl;
import br.com.oobj.githippie.model.Organizacao;
import br.com.oobj.githippie.model.Usuario;
import br.com.oobj.githippie.model.VinculoUsuarioOrganizacao;
import br.com.oobj.githippie.util.Utils;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestesDaoVinculoUserOrg {

	private Utils util = new Utils();
	private Organizacao organizacao = util.getOrganizacaoTest();

	private ManipularUsuarioImpl usuarioDAO = new ManipularUsuarioImpl();
	private ManipularOrganizacaoImpl organizacaoDAO = new ManipularOrganizacaoImpl();
	private ManipularVinculoUsuarioOrganizacao vinculoDAO = new ManipularVinculoUsuarioOrganizacaoImpl();

	@Before
	public void cadastrarUsuarioEOrganizacaoERegistroNaTabelaVinculo() {
		System.out.println("***Add 1 user + 1 Org e 1 vinculo para esses... necess�rio para iniciar cada teste***");
		Usuario usuarioCadastrado = usuarioDAO.cadastrarUsuario(organizacao.getUsuarioOwner());
		organizacao.setUsuarioOwner(usuarioCadastrado);
		organizacao.getUsuarioOwner().setIdUsuario(usuarioCadastrado.getIdUsuario());
		organizacaoDAO.cadastrarOrganizacao(organizacao);
	}

	@Test
	public void testeA_ConexaoJDBC() {
		System.out.println("====Teste conex�o JDBC====");
		try {
			ConexaoJDBC conexao = new ConexaoJDBC();
			conexao = new ConexaoJDBC();
			Connection conn = conexao.getConnection();
			System.out.println("Conex�o com BD estabelecida... ");
			conn.close();
		} catch (Exception e) {
			System.out.println("Falha ao conectar no BD TESTE..." + e);
		}
	}

	@Test
	public void testeB_ValidarSeExiste1RegistroNaTabelaVinculoReferenteAOrganizacaoTeste() {
		System.out.println("====Teste Validar se existe 1 registro na tabela Vinculo====");
		int qtdVinculos = vinculoDAO.listarTodosVinculos().size();
		int qtdEsperado = 1;
		System.out.println("Registros encontrados: " + qtdVinculos);
		assertEquals(qtdEsperado, qtdVinculos);
	}

	@Test
	public void testeC_AdicionarOutroUsuarioN�oAdmNoSistemaEVinculaloAOrganizacao() {
		System.out.println("====Teste criar outro vinculo e validar se existir�o 2 registros====");

		Usuario usuario2 = util.getUserTestRename("Joao");
		usuarioDAO.cadastrarUsuario(usuario2);
		VinculoUsuarioOrganizacao vinculo = new VinculoUsuarioOrganizacao();
		vinculo.setUsuario(usuario2);
		vinculo.setOrganizacao(organizacao);
		vinculo.setIsAdminBool(false);
		vinculoDAO.criarVinculoUsuarioOrganizacao(vinculo);

		int qtdVinculos = vinculoDAO.listarTodosVinculos().size();
		int qtdEsperado = 2;
		System.out.println("Registros encontrados: " + qtdVinculos);
		assertEquals(qtdEsperado, qtdVinculos);

	}

	@Test
	public void testeD_AddOutroUsuarioNoSistemaECriarVinculoComOrgEValidarIDs() {
		System.out.println("====Teste criar outro usu�rio, vincula-lo e consulta-lo pelo ID do usu�rio====");
		List<VinculoUsuarioOrganizacao> vinculosRetornados = new ArrayList<VinculoUsuarioOrganizacao>();
		Usuario usuario2 = util.getUserTestRename("Joao");
		usuario2 = usuarioDAO.cadastrarUsuario(usuario2);
		VinculoUsuarioOrganizacao vinculo = new VinculoUsuarioOrganizacao();
		vinculo.setUsuario(usuario2);
		vinculo.setOrganizacao(organizacao);
		vinculo.setIsAdminBool(false);
		vinculo = vinculoDAO.criarVinculoUsuarioOrganizacao(vinculo);
		System.out.println("Usu�rio com o ID: " + usuario2.getIdUsuario() + " Nome: " + usuario2.getNomeUsuario());
		vinculosRetornados = vinculoDAO.buscarVinculoPorQualquerCampo("id_usuario",
				String.valueOf(usuario2.getIdUsuario()));
		assertEquals(usuario2.getIdUsuario(), vinculosRetornados.get(0).getUsuario().getIdUsuario());
	}

	@Test
	public void testeE_CriarVinculoCom4UsuariosERetornalosEmUmaLista() {
		System.out.println(
				"====Teste Add + 3 usu�rios e buscar todos os vinculos encontrados no sistema, deve retornar 4====");
		List<VinculoUsuarioOrganizacao> vinculosRetornados = new ArrayList<VinculoUsuarioOrganizacao>();
		VinculoUsuarioOrganizacao vinculo = new VinculoUsuarioOrganizacao();
		vinculo.setOrganizacao(organizacao);
		vinculo.setIsAdminBool(false);

		Usuario usuario2 = usuarioDAO.cadastrarUsuario(util.getUserTestRename("Jo�o"));
		vinculo.setUsuario(usuario2);
		vinculo = vinculoDAO.criarVinculoUsuarioOrganizacao(vinculo);
		Usuario usuario3 = usuarioDAO.cadastrarUsuario(util.getUserTestRename("Boris"));
		vinculo.setUsuario(usuario3);
		vinculo = vinculoDAO.criarVinculoUsuarioOrganizacao(vinculo);
		Usuario usuario4 = usuarioDAO.cadastrarUsuario(util.getUserTestRename("Cida"));
		vinculo.setUsuario(usuario4);
		vinculo = vinculoDAO.criarVinculoUsuarioOrganizacao(vinculo);

		vinculosRetornados = vinculoDAO.listarTodosVinculos();
		System.out.println(
				"O Sistema possui no total: " + vinculosRetornados.size() + " Vinculos, com os seguintes usu�rios: ");
		for (VinculoUsuarioOrganizacao vinculoRet : vinculosRetornados) {
			System.out.println(vinculoRet.getUsuario().getNomeUsuario());
		}

		assertEquals(4, vinculosRetornados.size());
	}

	@Test
	public void testeF_AddMais3OrganizacoesEVincular1UsuarioNessas3Empresas() {
		System.out.println(
				"====Teste Add + 3 Organiza�oes, vincular o user nelas e trazer todas orgs do user. deve retornar 4====");
		Usuario usuarioAVincular = organizacao.getUsuarioOwner();
		Organizacao organizacaoNova, organizacaoCadastrada;
		VinculoUsuarioOrganizacao vinculo = new VinculoUsuarioOrganizacao();
		vinculo.setOrganizacao(organizacao);
		vinculo.setIsAdminBool(false);
		vinculo.setUsuario(organizacao.getUsuarioOwner());

		Usuario usuario2 = usuarioDAO.cadastrarUsuario(util.getUserTestRename("Jo�o"));
		organizacaoNova = util.getOrganizacaoTestRename("Empresa do Jo�o");
		organizacaoNova.setUsuarioOwner(usuario2);
		organizacaoCadastrada = organizacaoDAO.cadastrarOrganizacao(organizacaoNova);
		vinculo.setOrganizacao(organizacaoCadastrada);
		vinculo = vinculoDAO.criarVinculoUsuarioOrganizacao(vinculo);

		Usuario usuario3 = usuarioDAO.cadastrarUsuario(util.getUserTestRename("Boris"));
		organizacaoNova = util.getOrganizacaoTestRename("Empresa do Boris");
		organizacaoNova.setUsuarioOwner(usuario3);
		organizacaoCadastrada = organizacaoDAO.cadastrarOrganizacao(organizacaoNova);
		vinculo.setOrganizacao(organizacaoCadastrada);
		vinculo = vinculoDAO.criarVinculoUsuarioOrganizacao(vinculo);

		Usuario usuario4 = usuarioDAO.cadastrarUsuario(util.getUserTestRename("Cida"));
		organizacaoNova = util.getOrganizacaoTestRename("Empresa da Cida");
		organizacaoNova.setUsuarioOwner(usuario4);
		organizacaoCadastrada = organizacaoDAO.cadastrarOrganizacao(organizacaoNova);
		vinculo.setOrganizacao(organizacaoCadastrada);
		vinculo = vinculoDAO.criarVinculoUsuarioOrganizacao(vinculo);

		usuarioAVincular = vinculoDAO.listarOrganizacoesDoUsuario(usuarioAVincular);

		System.out.println("O usuario possui: " + usuarioAVincular.getOrganizacoesUsuario().size()
				+ " Vinculos, com as seguintes Organiza��es: ");
		for (Organizacao organizacao : usuarioAVincular.getOrganizacoesUsuario()) {
			System.out.println(organizacao.getNomeOrganizacao());
		}
		assertEquals(4, usuarioAVincular.getOrganizacoesUsuario().size());
	}

	@Test
	public void testeF_AddUsersOrgsEVinculosEListarTodosUsersDeCadaOrganizacao() {
		System.out.println("====Teste Add + Users, Orgs e Vinculos e Obter todos usuarios de cada Organiza��o====");
		Organizacao organizacaoNova, organizacaoCadastrada;
		VinculoUsuarioOrganizacao vinculo = new VinculoUsuarioOrganizacao();
		List<Organizacao> allOrganizacoes = new ArrayList<Organizacao>();
		vinculo.setOrganizacao(organizacao);
		vinculo.setIsAdminBool(false);
		vinculo.setUsuario(organizacao.getUsuarioOwner());
		allOrganizacoes.add(vinculoDAO.listarUsuariosVinculadosPorOrganizacao(organizacao));

		Usuario usuario2 = usuarioDAO.cadastrarUsuario(util.getUserTestRename("Jo�o"));
		organizacaoNova = util.getOrganizacaoTestRename("Empresa do Jo�o");
		organizacaoNova.setUsuarioOwner(usuario2);
		organizacaoCadastrada = organizacaoDAO.cadastrarOrganizacao(organizacaoNova);
		vinculo.setOrganizacao(organizacaoCadastrada);
		vinculo = vinculoDAO.criarVinculoUsuarioOrganizacao(vinculo);
		allOrganizacoes.add(vinculoDAO.listarUsuariosVinculadosPorOrganizacao(organizacaoCadastrada));

		Usuario usuario3 = usuarioDAO.cadastrarUsuario(util.getUserTestRename("Boris"));
		organizacaoNova = util.getOrganizacaoTestRename("Empresa do Boris");
		organizacaoNova.setUsuarioOwner(usuario3);
		organizacaoCadastrada = organizacaoDAO.cadastrarOrganizacao(organizacaoNova);
		vinculo.setOrganizacao(organizacaoCadastrada);
		vinculo = vinculoDAO.criarVinculoUsuarioOrganizacao(vinculo);
		vinculo.setUsuario(usuario2);
		vinculo.setIsAdminBool(true);
		vinculo = vinculoDAO.criarVinculoUsuarioOrganizacao(vinculo);

		allOrganizacoes.add(vinculoDAO.listarUsuariosVinculadosPorOrganizacao(organizacaoCadastrada));

		Usuario usuario4 = usuarioDAO.cadastrarUsuario(util.getUserTestRename("Cida"));
		organizacaoNova = util.getOrganizacaoTestRename("Empresa da Cida");
		organizacaoNova.setUsuarioOwner(usuario4);
		organizacaoCadastrada = organizacaoDAO.cadastrarOrganizacao(organizacaoNova);
		vinculo.setOrganizacao(organizacaoCadastrada);
		vinculo = vinculoDAO.criarVinculoUsuarioOrganizacao(vinculo);
		vinculo.setUsuario(usuario3);
		vinculo.setIsAdminBool(true);
		vinculo = vinculoDAO.criarVinculoUsuarioOrganizacao(vinculo);
		vinculo.setUsuario(organizacao.getUsuarioOwner());
		vinculo = vinculoDAO.criarVinculoUsuarioOrganizacao(vinculo);

		allOrganizacoes.add(vinculoDAO.listarUsuariosVinculadosPorOrganizacao(organizacaoCadastrada));

		for (Organizacao organizacao : allOrganizacoes) {
			System.out.println("Organiza��o: " + organizacao.getNomeOrganizacao() + " Usu�rio Owner: "
					+ organizacao.getUsuarioOwner().getNomeUsuario());
			if (organizacao.getUsuariosAdm() != null)
				for (Usuario usuarioAdm : organizacao.getUsuariosAdm())
					System.out.println(" Usu�rio Adm: " + usuarioAdm.getNomeUsuario());
			if (organizacao.getUsuariosParticipantes() != null)
				for (Usuario usuarioPart : organizacao.getUsuariosParticipantes())
					System.out.println(" Usu�rio Participante: " + usuarioPart.getNomeUsuario());
		}
		int qtdVinculos = vinculoDAO.listarTodosVinculos().size();
		assertEquals(10, qtdVinculos);
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
