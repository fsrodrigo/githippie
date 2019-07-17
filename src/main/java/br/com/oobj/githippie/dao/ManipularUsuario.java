package br.com.oobj.githippie.dao;

import java.util.List;

import br.com.oobj.githippie.model.Usuario;

public interface ManipularUsuario {

	public Usuario cadastrarUsuario(Usuario usuario);
	
	public void editarUsuario(Usuario usuario);
	
	public void desativarusuario(Usuario usuario);
	
	public Usuario consultarPorId(int id);
	
	public List<Usuario> consultarPorQualquerColuna(String coluna, String valor);
	
	public List<Usuario> listarTodos();
		
}
