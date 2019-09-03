package br.com.oobj.githippie.dao;

import static org.junit.Assert.assertEquals;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import br.com.oobj.githippie.config.ConexaoJDBC;
import br.com.oobj.githippie.dao.impl.ManipularUsuarioImpl;
import br.com.oobj.githippie.model.Usuario;
import br.com.oobj.githippie.util.Utils;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestesDaoUsuario {

	private Utils util = new Utils();
	private ManipularUsuarioImpl usuarioDAO;
	private Usuario usuario = util.getUserTest();

	@Test
	public void getUsuario() {

		assertEquals(usuario.getNomeUsuario(), "Rodrigo");
	}

	@Test
	public void testeA_ConexaoJDBC() {
		try {
			ConexaoJDBC conexao = new ConexaoJDBC();
			conexao = new ConexaoJDBC();
			Connection conn = conexao.getConnection();
			System.out.println("Conexão com BD estabelecida... " + conexao);
			conn.close();
		} catch (Exception e) {
			System.out.println("Falha ao conectar no BD TESTE..." + e);
		}
	}

	@Test
	public void testeB_MetodoResponsavelPorSalvarUmNovoUsuario() {
		usuarioDAO = new ManipularUsuarioImpl();
		System.out.println("Objeto a ser cadastrado hé do usuário: " + usuario.getNomeUsuario());
		Usuario usuarioCadastrado = usuarioDAO.cadastrarUsuario(usuario);
		int qtdCadastros = 0;
		if (usuarioCadastrado.getIdUsuario() != 0)
			qtdCadastros = 1;

		int qtdCadastrosEsperado = 1;
		assertEquals(qtdCadastrosEsperado, qtdCadastros);
	}

	@Test
	public void testeC_MetodoResponsavelPorEditarUmUsuario() {
		usuarioDAO = new ManipularUsuarioImpl();
		usuarioDAO.cadastrarUsuario(usuario);
		List<Usuario> usuarios = new ArrayList<Usuario>();
		usuarios = usuarioDAO.listarTodos();
		Usuario usuario = usuarios.get(0);

		System.out.println(
				"Objeto a ser editado hé do usuário: " + usuario.getNomeUsuario() + " ID: " + usuario.getIdUsuario());
		System.out.println("Seu sobrenome hé: " + usuario.getSobreNomeUsuario());

		usuario.setSobreNomeUsuario("EDITADO PELO TESTE");
		System.out.println("O novo sobrenome do usuário será: " + usuario.getSobreNomeUsuario());

		usuarioDAO.editarUsuario(usuario);
		int qtdCadastros = usuarioDAO.consultarPorQualquerColuna("sobre_nome_usuario", usuario.getSobreNomeUsuario())
				.size();
		int qtdCadastrosEsperado = 1;
		assertEquals(qtdCadastrosEsperado, qtdCadastros);
	}

	@Test
	public void testeD_DesativarUmUsuarioAnteriormenteAtivo() {
		usuarioDAO = new ManipularUsuarioImpl();
		usuarioDAO.cadastrarUsuario(usuario);
		List<Usuario> usuarios = new ArrayList<Usuario>();
		usuarios = usuarioDAO.listarTodos();
		Usuario usuario = usuarios.get(0);

		System.out.println("Vou Desativar o usuário: " + usuario.getNomeUsuario() + " ID: " + usuario.getIdUsuario());

		usuarioDAO.desativarUsuario(usuario);
		int qtdCadastros = usuarioDAO.consultarPorQualquerColuna("ativo", "false").size();
		int qtdCadastrosEsperado = 1;
		assertEquals(qtdCadastrosEsperado, qtdCadastros);
	}

	@Test
	public void testeE_MetodoResponsavelPorListarTodosUsuarios() {
		usuarioDAO = new ManipularUsuarioImpl();
		usuarioDAO.cadastrarUsuario(usuario);
		int qtdCadastros = usuarioDAO.listarTodos().size();
		int qtdCadastrosEsperado = 1;
		assertEquals(qtdCadastrosEsperado, qtdCadastros);
	}

	@After
	public void testeF_CriadoParaTruncarATabelaUsuariosAposExcucaoDeTodosOsTestes() {
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
