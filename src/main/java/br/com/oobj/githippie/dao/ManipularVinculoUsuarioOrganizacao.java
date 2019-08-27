package br.com.oobj.githippie.dao;

import java.util.List;

import br.com.oobj.githippie.model.Organizacao;
import br.com.oobj.githippie.model.Usuario;
import br.com.oobj.githippie.model.VinculoUsuarioOrganizacao;

public interface ManipularVinculoUsuarioOrganizacao {
	
	public VinculoUsuarioOrganizacao criarVinculoUsuarioOrganizacao(VinculoUsuarioOrganizacao vinculo);
	
	public Organizacao listarUsuariosVinculadosPorOrganizacao(Organizacao organizacao);
	
	public List<VinculoUsuarioOrganizacao> buscarVinculoPorQualquerCampo(String coluna, String valor);
	
	public List<VinculoUsuarioOrganizacao> listarTodosVinculos();
	
	public Usuario listarOrganizacoesDoUsuario(Usuario usuario);
	
	public void editarPermissaoVinculoUsuarioOrganizacao(VinculoUsuarioOrganizacao vinculo);
	
	public void removerVinculoUsuarioOrganizacao(VinculoUsuarioOrganizacao vinculo);
}
