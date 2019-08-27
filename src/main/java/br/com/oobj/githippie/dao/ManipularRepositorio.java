package br.com.oobj.githippie.dao;

import java.util.List;

import br.com.oobj.githippie.model.Repositorio;
import br.com.oobj.githippie.model.Usuario;

public interface ManipularRepositorio {

	public Repositorio criarRepositorio(Repositorio repositorio);

	public Repositorio editarRepositorio(Repositorio repositorio);

	public Repositorio buscarPorID(Repositorio repositorio);

	public List<Repositorio> buscarPorQualquerCampo(String coluna, String valor);

	public List<Usuario> listarUsuariosPorRepositorio(Repositorio repositorio);

	public List<Repositorio> listarRepositoriosPorUsuario(Usuario usuario);

	public void desativarRepositorio(Repositorio repositorio);

	public void criarVinculoUsuarioRepositorio(Repositorio repositorio, Usuario usuario);

	public void removerVinculoUsuarioRepositorio(Repositorio repositorio, Usuario usuario);

}