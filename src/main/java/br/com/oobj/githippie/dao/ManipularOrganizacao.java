package br.com.oobj.githippie.dao;

import java.util.List;

import br.com.oobj.githippie.model.Organizacao;

public interface ManipularOrganizacao {

	public Organizacao cadastrarOrganizacao(Organizacao organizacao);
	
	public void editarOrganizacao(Organizacao organizacao);
	
	public void desativarOrganizacao(Organizacao organizacao);
	
	public Organizacao consultarPorId(int id);
	
	public List<Organizacao> consultarOrganizacaoPorQualquerColuna(String coluna, String valor);
	
	public List<Organizacao> listarTodasOrganizacoes();
	
}
