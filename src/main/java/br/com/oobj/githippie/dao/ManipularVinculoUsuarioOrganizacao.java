package br.com.oobj.githippie.dao;

import java.util.List;

import br.com.oobj.githippie.model.Organizacao;
import br.com.oobj.githippie.model.Usuario;
import br.com.oobj.githippie.model.VinculoUsuarioOrganizacao;

public interface ManipularVinculoUsuarioOrganizacao {
	
	public VinculoUsuarioOrganizacao criarVinculoUsuarioOrganizacao(VinculoUsuarioOrganizacao vinculo);
	
	public Organizacao listarUsuariosVinculadosPorOrganizacao(Organizacao organizacao);
	
	public List<VinculoUsuarioOrganizacao> listarTodosVinculos();
	
	public Usuario listarOrganizacoesDoUsuario(Usuario usuario);
	
	public void editarPermissaoVinculoUsuarioOrganizacao(int idVinculo, Usuario usuario, Organizacao organizacao, Boolean isAdmin);
	
	public void removerVinculoUsuarioOrganizacao(int idVinculo, Usuario usuario, Organizacao organizacao);
}
