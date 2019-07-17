package br.com.oobj.githippie.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.oobj.githippie.config.ConexaoJDBC;
import br.com.oobj.githippie.dao.ManipularOrganizacao;
import br.com.oobj.githippie.model.Organizacao;
import br.com.oobj.githippie.model.VinculoUsuarioOrganizacao;

public class ManipularOrganizacaoImpl implements ManipularOrganizacao {

	private ConexaoJDBC conexao;
	private ManipularUsuarioImpl usuarioDAO = new ManipularUsuarioImpl();

	public Organizacao cadastrarOrganizacao(Organizacao organizacao) {
		ManipularVinculoUsuarioOrganizacaoImpl vinculoDAO = new ManipularVinculoUsuarioOrganizacaoImpl();
		String sql = "INSERT INTO organizacoes "
				+ "(nome_organizacao, descricao_organizacao, usuario_owner, data_criacao) " + "VALUES"
				+ " (?, ?, ?, cast (? AS TIMESTAMP));";

		try {
			conexao = new ConexaoJDBC();
			PreparedStatement ps = conexao.getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, organizacao.getNomeOrganizacao());
			ps.setString(2, organizacao.getDescricaoOrganizacao());
			ps.setInt(3, organizacao.getUsuarioOwner().getIdUsuario());
			ps.setString(4, organizacao.getDataCriacao());
			// System.out.println(ps.toString()); // Printar query pronta...
			int linhasInseridas = ps.executeUpdate();
			if (linhasInseridas == 1) {
				VinculoUsuarioOrganizacao vinculo = new VinculoUsuarioOrganizacao();
				ps.getGeneratedKeys().next();
				organizacao.setIdOrganizacao(ps.getGeneratedKeys().getInt(1));
				vinculo.setIsAdminBool(true);
				vinculo.setOrganizacao(organizacao);
				vinculo.setUsuario(organizacao.getUsuarioOwner());
				vinculoDAO.criarVinculoUsuarioOrganizacao(vinculo);
			}

		} catch (Exception e) {
			System.out.println("Falha ao cadastrar uma nova Organização .... " + e);
		}
		return organizacao;
	}

	public void editarOrganizacao(Organizacao organizacao) {
		String sql = "UPDATE organizacoes SET nome_organizacao = ?, descricao_organizacao = ?, usuario_owner = ? WHERE id = ?;";

		try {
			conexao = new ConexaoJDBC();
			PreparedStatement ps = conexao.getConnection().prepareStatement(sql);
			ps.setString(1, organizacao.getNomeOrganizacao());
			ps.setString(2, organizacao.getDescricaoOrganizacao());
			ps.setInt(3, organizacao.getUsuarioOwner().getIdUsuario());
			ps.setInt(4, organizacao.getIdOrganizacao());

			// System.out.println(ps.toString());
			ps.executeUpdate();

		} catch (Exception e) {
			System.out.println("Falha ao editar o usuário ..." + e);
		}
	}

	public void desativarOrganizacao(Organizacao organizacao) {
		String sql = "UPDATE organizacoes SET ativo = FALSE WHERE id = ?;";

		try {
			conexao = new ConexaoJDBC();
			PreparedStatement ps = conexao.getConnection().prepareStatement(sql);
			ps.setInt(1, organizacao.getIdOrganizacao());
			ps.executeUpdate();

		} catch (Exception e) {
			System.out.println("Falha desativar a Organização: " + organizacao.getNomeOrganizacao());
			System.out.println(e);
		}
	}

	public Organizacao consultarPorId(int id) {
		String sql = "SELECT * FROM organizacoes WHERE id =" + id + ";";
		Organizacao organizacao = new Organizacao();

		try {
			conexao = new ConexaoJDBC();
			Connection conn = conexao.getConnection();
			// System.out.println(conn.prepareStatement(sql).toString());
			ResultSet rs = conn.prepareStatement(sql).executeQuery();
			conn.close();

			while (rs.next()) {

				organizacao.setIdOrganizacao(rs.getInt("id"));
				organizacao.setNomeOrganizacao(rs.getString("nome_organizacao"));
				organizacao.setDescricaoOrganizacao(rs.getString("descricao_organizacao"));
				organizacao.setDataCriacao(rs.getString("data_criacao"));
				organizacao.setUsuarioOwner(usuarioDAO.consultarPorId(rs.getInt("usuario_owner")));

				return organizacao;
			}
		} catch (Exception e) {
			System.out.println("Falha ao consultar a organização cujo ID hé..:" + id + " ERRO: " + e);
		}
		return organizacao;

	}

	public List<Organizacao> consultarOrganizacaoPorQualquerColuna(String coluna, String valor) {
		String sql = "SELECT * FROM organizacoes WHERE " + coluna + " = \'" + valor + "\';";
		List<Organizacao> organizacoes = new ArrayList<Organizacao>();

		try {
			conexao = new ConexaoJDBC();
			Connection conn = conexao.getConnection();
			// System.out.println(conn.prepareStatement(sql).toString());
			ResultSet rs = conn.prepareStatement(sql).executeQuery();
			conn.close();

			while (rs.next()) {
				Organizacao organizacao = new Organizacao();

				organizacao.setIdOrganizacao(rs.getInt("id"));
				organizacao.setNomeOrganizacao(rs.getString("nome_organizacao"));
				organizacao.setDescricaoOrganizacao(rs.getString("descricao_organizacao"));
				organizacao.setDataCriacao(rs.getString("data_criacao"));
				organizacao.setUsuarioOwner(usuarioDAO.consultarPorId(rs.getInt("usuario_owner")));

				organizacoes.add(organizacao);
			}
		} catch (Exception e) {
			System.out.println("Falha ao executar query que busca uma Organizacao por qualquer campo... " + e);
		}
		return organizacoes;
	}

	public List<Organizacao> listarTodasOrganizacoes() {
		String sql = "SELECT * FROM organizacoes;";
		List<Organizacao> organizacoes = new ArrayList<Organizacao>();

		try {
			conexao = new ConexaoJDBC();
			PreparedStatement ps = conexao.getConnection().prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Organizacao organizacao = new Organizacao();

				organizacao.setIdOrganizacao(rs.getInt("id"));
				organizacao.setNomeOrganizacao(rs.getString("nome_organizacao"));
				organizacao.setDescricaoOrganizacao(rs.getString("descricao_organizacao"));
				organizacao.setDataCriacao(rs.getString("data_criacao"));
				organizacao.setUsuarioOwner(usuarioDAO.consultarPorId(rs.getInt("usuario_owner")));

				organizacoes.add(organizacao);
			}
		} catch (Exception e) {
			System.out.println("Falha ao executar query que lista todas as organizações... " + e);
		}
		return organizacoes;
	}

}
