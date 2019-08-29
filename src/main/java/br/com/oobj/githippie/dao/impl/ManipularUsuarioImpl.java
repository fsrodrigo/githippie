package br.com.oobj.githippie.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.oobj.githippie.config.ConexaoJDBC;
import br.com.oobj.githippie.dao.ManipularUsuario;
import br.com.oobj.githippie.model.Plano;
import br.com.oobj.githippie.model.Usuario;

public class ManipularUsuarioImpl implements ManipularUsuario {

	private ConexaoJDBC conexao;

	public Usuario cadastrarUsuario(Usuario usuario) {

		if (!consultarPorQualquerColuna("email_usuario", usuario.getEmailUsuario()).isEmpty()) {
			System.out.println("Falha ao cadastrar usuário: Usuário com o e-mail " + usuario.getEmailUsuario()
					+ " já possui cadastro!!!");
			return null;
		}

		String sql = "INSERT INTO usuarios "
				+ "(nome_usuario, sobre_nome_usuario, email_usuario, url_linkedin, sexo, data_cadastro, plano) "
				+ "VALUES" + " (?,?,?,?,?,cast (? AS TIMESTAMP),?);";

		try {
			conexao = new ConexaoJDBC();
			PreparedStatement ps = conexao.getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, usuario.getNomeUsuario());
			ps.setString(2, usuario.getSobreNomeUsuario());
			ps.setString(3, usuario.getEmailUsuario());
			ps.setString(4, usuario.getUrlLinkedin());
			ps.setString(5, String.valueOf(usuario.getSexo()));
			ps.setString(6, usuario.getDataCadastro());
			ps.setString(7, String.valueOf(usuario.getPlano()));
			// Printar query pronta...
			// System.out.println(ps.toString());

			int linhasInseridas = ps.executeUpdate();

			if (linhasInseridas == 1) {
				System.out.println("Usuário cadastrado com sucesso");
				ps.getGeneratedKeys().next();
				usuario.setIdUsuario(ps.getGeneratedKeys().getInt(1));
			}

		} catch (Exception e) {
			System.out.println("Falha ao cadastrar usuário: " + e);
		}
		return usuario;
	}

	public void editarUsuario(Usuario usuario) {

		String sql = "UPDATE usuarios SET nome_usuario = ?, sobre_nome_usuario = ?, email_usuario = ?, url_linkedin = ?, "
				+ "sexo = ?, plano = ? WHERE id = ?;";

		try {
			conexao = new ConexaoJDBC();
			PreparedStatement ps = conexao.getConnection().prepareStatement(sql);
			ps.setString(1, usuario.getNomeUsuario());
			ps.setString(2, usuario.getSobreNomeUsuario());
			ps.setString(3, usuario.getEmailUsuario());
			ps.setString(4, usuario.getUrlLinkedin());
			ps.setString(5, String.valueOf(usuario.getSexo()));
			ps.setString(6, String.valueOf(usuario.getPlano()));
			ps.setInt(7, usuario.getIdUsuario());

			// Usuario usuarioValidarRepetido = consultarPorQualquerColuna("email_usuario",
			// usuario.getEmailUsuario());
			List<Usuario> validarUsuarioRepetido = consultarPorQualquerColuna("email_usuario",
					usuario.getEmailUsuario());
			if (validarUsuarioRepetido.size() == 1
					&& validarUsuarioRepetido.get(0).getIdUsuario() != usuario.getIdUsuario()) {
				System.out.println("Falha ao Editar, usuário com o e-mail " + usuario.getEmailUsuario()
						+ " já possui cadastro!!!");
			} else {
				ps.executeUpdate();
				System.out.println("Usuário editado com sucesso");
			}

		} catch (Exception e) {
			System.out.println("Falha ao editar o usuário: " + e);
		}

	}

	public void desativarusuario(Usuario usuario) {
		String sql = "UPDATE usuarios SET ativo = FALSE WHERE id = ?;";

		try {
			conexao = new ConexaoJDBC();
			PreparedStatement ps = conexao.getConnection().prepareStatement(sql);
			ps.setInt(1, usuario.getIdUsuario());
			ps.executeUpdate();
			System.out.println("Usuário deletado com sucesso!");
		} catch (Exception e) {
			System.out.println("Falha desativar o usuário: " + e);
		}
	}

	public Usuario consultarPorId(int id) {
		String sql = "SELECT * FROM usuarios where id= " + id + " AND ativo = TRUE;";

		try {
			conexao = new ConexaoJDBC();
			PreparedStatement ps = conexao.getConnection().prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			Usuario usuario = new Usuario();

			while (rs.next()) {

				usuario.setIdUsuario(rs.getInt("id"));
				usuario.setNomeUsuario(rs.getString("nome_usuario"));
				usuario.setSobreNomeUsuario(rs.getString("sobre_nome_usuario"));
				usuario.setEmailUsuario(rs.getString("email_usuario"));
				usuario.setUrlLinkedin(rs.getString("url_linkedin"));
				usuario.setSexo(rs.getString("sexo").charAt(0));
				usuario.setDataCadastro(rs.getString("data_cadastro"));
				usuario.setPlano(Plano.valueOf(rs.getString("plano")));

				return usuario;
			}
		} catch (Exception e) {
			System.out.println("Falha ao Consultar o ID: " + id);
			System.out.println(e);
		}
		return null;
	}

	public List<Usuario> listarTodos() {
		String sql = "SELECT * FROM usuarios WHERE ativo = TRUE;";
		List<Usuario> usuarios = new ArrayList<Usuario>();

		try {
			conexao = new ConexaoJDBC();
			PreparedStatement ps = conexao.getConnection().prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

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
			System.out.println("Falha ao executar query que lista todos os usuarios... " + e);
		}

		return usuarios;
	}

	public List<Usuario> consultarPorQualquerColuna(String coluna, String valor) {
		String sql = "SELECT * FROM usuarios WHERE " + coluna + " = \'" + valor + "\' AND ativo = TRUE;";
		List<Usuario> usuarios = new ArrayList<Usuario>();

		try {
			ConexaoJDBC conexao = new ConexaoJDBC();
			conexao = new ConexaoJDBC();
			Connection conn = conexao.getConnection();
			// System.out.println(conn.prepareStatement(sql).toString());
			ResultSet rs = conn.prepareStatement(sql).executeQuery();
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

}
