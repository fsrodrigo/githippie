package br.com.oobj.githippie.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.oobj.githippie.config.ConexaoJDBC;
import br.com.oobj.githippie.dao.ManipularRepositorio;
import br.com.oobj.githippie.model.Plano;
import br.com.oobj.githippie.model.Repositorio;
import br.com.oobj.githippie.model.TipoRepositorio;
import br.com.oobj.githippie.model.Usuario;

public class ManipularRepositorioImpl implements ManipularRepositorio {
	ConexaoJDBC conexao;

	public Repositorio criarRepositorio(Repositorio repositorio) {
		String sql = "INSERT INTO repositorios "
				+ "(nome_repositorio, descricao_repositorio, tipo_repositorio, data_criacao, usuario_owner) " + "VALUES"
				+ " (?,?,?,cast (? AS TIMESTAMP),?);";

		try {
			conexao = new ConexaoJDBC();
			PreparedStatement ps = conexao.getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, repositorio.getNomeRepositorio());
			ps.setString(2, repositorio.getDescricao());
			ps.setString(3, String.valueOf(repositorio.getTipoRepositorio()));
			ps.setString(4, repositorio.getDataCriacao());
			ps.setInt(5, repositorio.getUsuarioOwner().getIdUsuario());

			int linhasInseridas = ps.executeUpdate();
			if (linhasInseridas == 1) {
				System.out.println("Repositório cadastrado com sucesso.");
				ps.getGeneratedKeys().next();
				repositorio.setIdRepositorio(ps.getGeneratedKeys().getInt(1));
			}

		} catch (Exception e) {
			System.out.println("Falha ao cadastrar repositório: " + e);
		}
		return repositorio;
	}

	public Repositorio editarRepositorio(Repositorio repositorio) {
		String sql = "UPDATE repositorios SET nome_repositorio = ?, descricao_repositorio = ?, tipo_repositorio = ?, usuario_owner = ? WHERE id = ?;";

		try {
			conexao = new ConexaoJDBC();
			PreparedStatement ps = conexao.getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

			ps.setString(1, repositorio.getNomeRepositorio());
			ps.setString(2, repositorio.getDescricao());
			ps.setString(3, String.valueOf(repositorio.getTipoRepositorio()));
			ps.setInt(4, repositorio.getUsuarioOwner().getIdUsuario());
			ps.setInt(5, repositorio.getIdRepositorio());

			int linhasInseridas = ps.executeUpdate();
			if (linhasInseridas == 1) {
				ps.getGeneratedKeys().next();
				return repositorio;
			}

		} catch (Exception e) {
			System.out.println("Falha ao editar o REPOSITÓRIO..." + repositorio.getNomeRepositorio() + " ID: "
					+ repositorio.getIdRepositorio() + " Erro: " + e);
		}
		return repositorio;
	}

	public Repositorio buscarPorID(Repositorio repositorio) {
		ManipularUsuarioImpl usuarioDAO = new ManipularUsuarioImpl();
		Repositorio repositorioRet = new Repositorio();

		String sql = "SELECT * FROM repositorios WHERE id = ?;";

		try {
			conexao = new ConexaoJDBC();
			PreparedStatement ps = conexao.getConnection().prepareStatement(sql);
			ps.setInt(1, repositorio.getIdRepositorio());
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				repositorioRet.setIdRepositorio(rs.getInt("id"));
				repositorioRet.setNomeRepositorio(rs.getString("nome_repositorio"));
				repositorioRet.setDescricao(rs.getString("descricao_repositorio"));
				repositorioRet.setDataCriacao(rs.getString("data_criacao"));
				repositorioRet.setTipoRepositorio(TipoRepositorio.valueOf(rs.getString("tipo_repositorio")));
				repositorioRet.setUsuarioOwner(usuarioDAO.consultarPorId(rs.getInt("usuario_owner")));

				return repositorioRet;
			}
		} catch (Exception e) {
			System.out.println("Falha ao Consultar o REPOSITORIO ID: " + repositorio.getIdRepositorio());
			System.out.println(e);
		}
		return repositorioRet;
	}

	public List<Repositorio> buscarPorQualquerCampo(String coluna, String valor) {
		String sql = "SELECT * FROM repositorios WHERE " + coluna + " = \'" + valor + "\';";
		List<Repositorio> repositorios = new ArrayList<Repositorio>();
		ManipularUsuarioImpl usuarioDAO = new ManipularUsuarioImpl();

		try {
			ConexaoJDBC conexao = new ConexaoJDBC();
			conexao = new ConexaoJDBC();
			Connection conn = conexao.getConnection();
			// System.out.println(conn.prepareStatement(sql).toString());
			ResultSet rs = conn.prepareStatement(sql).executeQuery();
			conn.close();

			while (rs.next()) {
				Repositorio repositorio = new Repositorio();

				repositorio.setIdRepositorio(rs.getInt("id"));
				repositorio.setNomeRepositorio(rs.getString("nome_repositorio"));
				repositorio.setDescricao(rs.getString("descricao_repositorio"));
				repositorio.setDataCriacao(rs.getString("data_criacao"));
				repositorio.setTipoRepositorio(TipoRepositorio.valueOf(rs.getString("tipo_repositorio")));
				repositorio.setUsuarioOwner(usuarioDAO.consultarPorId(rs.getInt("usuario_owner")));

				repositorios.add(repositorio);
			}
		} catch (Exception e) {
			System.out.println("Falha ao executar query que busca por Repositorios por qualquer campo... " + e);
		}
		return repositorios;
	}

	public List<Usuario> listarUsuariosPorRepositorio(Repositorio repositorio) {
		String sql = "SELECT * FROM usuarios_repositorios WHERE id_repositorio = ?;";
		List<Usuario> usuarios = new ArrayList<Usuario>();

		try {
			conexao = new ConexaoJDBC();
			Connection conn = conexao.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, repositorio.getIdRepositorio());
			ResultSet rs = ps.executeQuery();
			conn.close();

			while (rs.next()) {
				Usuario usuario = new Usuario();

				usuario.setIdUsuario(rs.getInt("id"));
				usuario.setNomeUsuario(rs.getString("nome_usuario"));
				usuario.setSobreNomeUsuario(rs.getString("sobre_nome_usuario"));
				usuario.setEmailUsuario(rs.getString("email_usuario"));
				usuario.setUrlLinkedin(rs.getString("url_linkedin"));
				usuario.setSexo(rs.getString("sexo").charAt(0));
				usuario.setDataCadastro(rs.getString("data_cadastro"));
				usuario.setPlano(Plano.valueOf(rs.getString("plano")));

				usuarios.add(usuario);
			}
		} catch (Exception e) {
			System.out.println("Falha ao executar query que busca por qualquer campo... " + e);
		}
		return usuarios;
	}

	public List<Repositorio> listarRepositoriosPorUsuario(Usuario usuario) {
		String sql = "SELECT * FROM usuarios_repositorios WHERE id_usuario = ?;";
		List<Repositorio> repositorios = new ArrayList<Repositorio>();

		try {
			ConexaoJDBC conexao = new ConexaoJDBC();
			conexao = new ConexaoJDBC();
			Connection conn = conexao.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, usuario.getIdUsuario());
			ResultSet rs = ps.executeQuery();
			conn.close();

			while (rs.next()) {
				Repositorio repositorio = new Repositorio();

				repositorio.setIdRepositorio(rs.getInt("id"));
				repositorio.setNomeRepositorio(rs.getString("nome_repositorio"));
				repositorio.setDescricao(rs.getString("descricao_repositorio"));
				repositorio.setDataCriacao(rs.getString("data_criacao"));
				repositorio.setTipoRepositorio(TipoRepositorio.valueOf(rs.getString("tipo_repositorio")));
				repositorio.setUsuarioOwner(usuario);

				repositorios.add(repositorio);
			}
		} catch (Exception e) {
			System.out.println("Falha ao buscar os repositórios referente a um usuário.. " + e);
		}
		return repositorios;
	}

	public void removerVinculoUsuarioRepositorio(Repositorio repositorio, Usuario usuario) {
		String sql = "DELETE FROM usuarios_repositorios WHERE id_usuario = ? AND id_repositorio = ?;";

		try {
			ConexaoJDBC conexao = new ConexaoJDBC();
			conexao = new ConexaoJDBC();
			Connection conn = conexao.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, usuario.getIdUsuario());
			ps.setInt(2, repositorio.getIdRepositorio());
			ps.executeQuery();
			conn.close();

		} catch (Exception e) {
			System.out.println("Falha ao remover o vinculo desse usuário com a empresa selecionada. " + e);
		}
	}

	public void criarVinculoUsuarioRepositorio(Repositorio repositorio, Usuario usuario) {
		String sql = "INSERT INTO usuarios_repositorios (id_usuario, id_repositorio) VALUES (?, ?);";

		try {
			conexao = new ConexaoJDBC();
			PreparedStatement ps = conexao.getConnection().prepareStatement(sql);
			ps.setInt(1, repositorio.getIdRepositorio());
			ps.setInt(2, usuario.getIdUsuario());
			ps.executeUpdate();

		} catch (Exception e) {
			System.out.println("Falha ao cadastrar um REPOSITÓRIO novo..." + e);
		}
	}

	public void desativarRepositorio(Repositorio repositorio) {
		// TODO Auto-generated method stub

	}

}
