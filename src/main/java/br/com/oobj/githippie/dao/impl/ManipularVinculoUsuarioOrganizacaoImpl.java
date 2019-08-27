package br.com.oobj.githippie.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.oobj.githippie.config.ConexaoJDBC;
import br.com.oobj.githippie.dao.ManipularVinculoUsuarioOrganizacao;
import br.com.oobj.githippie.model.Organizacao;
import br.com.oobj.githippie.model.Usuario;
import br.com.oobj.githippie.model.VinculoUsuarioOrganizacao;

public class ManipularVinculoUsuarioOrganizacaoImpl implements ManipularVinculoUsuarioOrganizacao {

	private ConexaoJDBC conexao;
	private ManipularUsuarioImpl usuarioDAO;// = new ManipularUsuarioImpl();
	private ManipularOrganizacaoImpl organizacaoDAO;// = new ManipularOrganizacaoImpl();

	public VinculoUsuarioOrganizacao criarVinculoUsuarioOrganizacao(VinculoUsuarioOrganizacao vinculo) {
		String sql = "INSERT INTO usuarios_organizacao " + "(id_usuario, id_organizacao, adm) " + "VALUES"
				+ " (?, ?, ?);";

		try {
			conexao = new ConexaoJDBC();
			Connection conn = conexao.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1, vinculo.getUsuario().getIdUsuario());
			ps.setInt(2, vinculo.getOrganizacao().getIdOrganizacao());
			ps.setBoolean(3, vinculo.getIsAdmin());

			int linhasInseridas = ps.executeUpdate();
			if (linhasInseridas == 1) {
				ps.getGeneratedKeys().next();
				vinculo.setId(ps.getGeneratedKeys().getInt(1));
			}
			conn.close();
		} catch (Exception e) {
			System.out.println("Falha ao Vincular um usuário a uma organização .... " + e);
		}
		return vinculo;
	}

	public Organizacao listarUsuariosVinculadosPorOrganizacao(Organizacao organizacao) {
		String sql = "SELECT * FROM usuarios_organizacao WHERE id_organizacao = ?";
		List<Usuario> usuariosAdm = new ArrayList<Usuario>();
		List<Usuario> usuariosParticipantes = new ArrayList<Usuario>();

		try {
			conexao = new ConexaoJDBC();
			Connection conn = conexao.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setInt(1, organizacao.getIdOrganizacao());
			ResultSet rs = ps.executeQuery();
			conn.close();

			while (rs.next()) {
				usuarioDAO = new ManipularUsuarioImpl();
				if (rs.getBoolean("adm")) {
					usuariosAdm.add(usuarioDAO.consultarPorId(rs.getInt("id_usuario")));
				} else {
					usuariosParticipantes.add(usuarioDAO.consultarPorId(rs.getInt("id_usuario")));
				}
			}

			organizacao.setUsuariosAdm(usuariosAdm);
			organizacao.setUsuariosParticipantes(usuariosParticipantes);

		} catch (Exception e) {
			System.out.println("Falha ao obter a lista de usuarios vinculados à Organização... " + e);
		}

		return organizacao;
	}

	public List<VinculoUsuarioOrganizacao> buscarVinculoPorQualquerCampo(String coluna, String valor) {
		String sql = "SELECT * FROM usuarios_organizacao WHERE " + coluna + " = \'" + valor + "\';";
		List<VinculoUsuarioOrganizacao> vinculos = new ArrayList<VinculoUsuarioOrganizacao>();
		try {
			conexao = new ConexaoJDBC();
			Connection conn = conexao.getConnection();
			ResultSet rs = conn.prepareStatement(sql).executeQuery();
			conn.close();

			while (rs.next()) {
				usuarioDAO = new ManipularUsuarioImpl();
				organizacaoDAO = new ManipularOrganizacaoImpl();
				VinculoUsuarioOrganizacao vinculo = new VinculoUsuarioOrganizacao();
				vinculo.setId(rs.getInt("id"));
				vinculo.setUsuario(usuarioDAO.consultarPorId(rs.getInt("id_usuario")));
				vinculo.setOrganizacao(organizacaoDAO.consultarPorId(rs.getInt("id_organizacao")));
				vinculo.setIsAdminBool(rs.getBoolean("adm"));
				vinculos.add(vinculo);
			}

		} catch (Exception e) {
			System.out.println("Falha ao obter a lista com todos vinculos... " + e);
		}
		return vinculos;

	}

	public List<VinculoUsuarioOrganizacao> listarTodosVinculos() {
		String sql = "SELECT * FROM usuarios_organizacao";
		List<VinculoUsuarioOrganizacao> vinculos = new ArrayList<VinculoUsuarioOrganizacao>();
		try {
			conexao = new ConexaoJDBC();
			Connection conn = conexao.getConnection();
			ResultSet rs = conn.prepareStatement(sql).executeQuery();
			conn.close();

			while (rs.next()) {
				usuarioDAO = new ManipularUsuarioImpl();
				organizacaoDAO = new ManipularOrganizacaoImpl();
				VinculoUsuarioOrganizacao vinculo = new VinculoUsuarioOrganizacao();
				vinculo.setId(rs.getInt("id"));
				vinculo.setUsuario(usuarioDAO.consultarPorId(rs.getInt("id_usuario")));
				vinculo.setOrganizacao(organizacaoDAO.consultarPorId(rs.getInt("id_organizacao")));
				vinculo.setIsAdminBool(rs.getBoolean("adm"));
				vinculos.add(vinculo);
			}

		} catch (Exception e) {
			System.out.println("Falha ao obter a lista com todos vinculos... " + e);
		}

		return vinculos;
	}

	public Usuario listarOrganizacoesDoUsuario(Usuario usuario) {
		organizacaoDAO = new ManipularOrganizacaoImpl();
		String sql = "SELECT * FROM usuarios_organizacao WHERE id_usuario = ?";
		List<Organizacao> organizacoesUsuario = new ArrayList<Organizacao>();
		List<VinculoUsuarioOrganizacao> vinculosUsuarioOrganizacao = new ArrayList<VinculoUsuarioOrganizacao>();

		try {
			conexao = new ConexaoJDBC();
			Connection conn = conexao.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setInt(1, usuario.getIdUsuario());

			ResultSet rs = ps.executeQuery();
			conn.close();

			while (rs.next()) {
				VinculoUsuarioOrganizacao vinculoUserOrg = new VinculoUsuarioOrganizacao();
				Organizacao orgConsultada = new Organizacao();
				orgConsultada = organizacaoDAO.consultarPorId(rs.getInt("id_organizacao"));
				vinculoUserOrg.setId(rs.getInt("id"));
				vinculoUserOrg.setUsuario(usuario);
				vinculoUserOrg.setOrganizacao(orgConsultada);
				vinculoUserOrg.setIsAdminBool(rs.getBoolean("adm"));
				vinculosUsuarioOrganizacao.add(vinculoUserOrg);
				organizacoesUsuario.add(orgConsultada);
			}

		} catch (Exception e) {
			System.out.println("Falha ao obter a lista de Organizações vinculadas ao Usuário... " + e);
		}

		usuario.setOrganizacoesUsuario(organizacoesUsuario);
		usuario.setVinculosUsuario(vinculosUsuarioOrganizacao);
		return usuario;
	}

	public void editarPermissaoVinculoUsuarioOrganizacao(VinculoUsuarioOrganizacao vinculo) {
		String sql = "UPDATE usuarios_organizacao SET id_usuario = ?, id_organizacao = ?, adm = ? WHERE id = ?;";

		try {
			conexao = new ConexaoJDBC();
			PreparedStatement ps = conexao.getConnection().prepareStatement(sql);
			ps.setInt(1, vinculo.getUsuario().getIdUsuario());
			ps.setInt(2, vinculo.getOrganizacao().getIdOrganizacao());
			ps.setBoolean(3, vinculo.getIsAdmin());
			ps.setInt(4, vinculo.getId());
			ps.executeUpdate();

		} catch (Exception e) {
			System.out.println("Falha Editar o vinculo do usuário: " + vinculo.getUsuario().getNomeUsuario()
					+ " na empresa: " + vinculo.getOrganizacao().getNomeOrganizacao());
			System.out.println(e);
		}

	}

	public void removerVinculoUsuarioOrganizacao(VinculoUsuarioOrganizacao vinculo) {
		String sql = "DELETE FROM usuarios_organizacao WHERE id_usuario = ? AND id_organizacao = ? AND id = ?;";

		try {
			conexao = new ConexaoJDBC();
			PreparedStatement ps = conexao.getConnection().prepareStatement(sql);
			ps.setInt(1, vinculo.getUsuario().getIdUsuario());
			ps.setInt(2, vinculo.getOrganizacao().getIdOrganizacao());
			ps.setInt(3, vinculo.getId());
			ps.executeUpdate();

		} catch (Exception e) {
			System.out.println("Falha Editar o Remover o vinculo do usuário: " + vinculo.getUsuario().getNomeUsuario()
					+ " na empresa: " + vinculo.getOrganizacao().getNomeOrganizacao());
			System.out.println(e);
		}
	}

}
