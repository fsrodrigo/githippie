package br.com.oobj.githippie.model;

import java.util.List;

public class Usuario {
	
	private int idUsuario;
	private String nomeUsuario;
	private String sobreNomeUsuario;
	private String emailUsuario;
	private String urlLinkedin;
	private char sexo;
	private String dataCadastro;
	private Plano plano;
	private List<Repositorio> repositoriosUsuario;
	private List<Organizacao> organizacoesUsuario;
	private List<VinculoUsuarioOrganizacao> vinculosUsuarioOrganizacao;
	
	
	
	public List<VinculoUsuarioOrganizacao> getVinculosUsuario() {
		return vinculosUsuarioOrganizacao;
	}
	public void setVinculosUsuario(List<VinculoUsuarioOrganizacao> vinculosUsuarioOrganizacao) {
		this.vinculosUsuarioOrganizacao = vinculosUsuarioOrganizacao;
	}
	public List<Organizacao> getOrganizacoesUsuario() {
		return organizacoesUsuario;
	}
	public void setOrganizacoesUsuario(List<Organizacao> organizacoesUsuario) {
		this.organizacoesUsuario = organizacoesUsuario;
	}
	public List<Repositorio> getRepositoriosUsuario() {
		return repositoriosUsuario;
	}
	public void setRepositoriosUsuario(List<Repositorio> repositoriosUsuario) {
		this.repositoriosUsuario = repositoriosUsuario;
	}
	public int getIdUsuario() {
		return idUsuario;
	}
	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}
	public String getNomeUsuario() {
		return nomeUsuario;
	}
	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}
	public String getSobreNomeUsuario() {
		return sobreNomeUsuario;
	}
	public void setSobreNomeUsuario(String sobreNomeUsuario) {
		this.sobreNomeUsuario = sobreNomeUsuario;
	}
	public String getEmailUsuario() {
		return emailUsuario;
	}
	public void setEmailUsuario(String emailUsuario) {
		this.emailUsuario = emailUsuario;
	}
	public String getUrlLinkedin() {
		return urlLinkedin;
	}
	public void setUrlLinkedin(String urlLinkedin) {
		this.urlLinkedin = urlLinkedin;
	}
	public char getSexo() {
		return sexo;
	}
	public void setSexo(char sexo) {
		this.sexo = sexo;
	}
	public String getDataCadastro() {
		return dataCadastro;
	}
	public void setDataCadastro(String dataCadastro) {
		this.dataCadastro = dataCadastro;
	}
	public Plano getPlano() {
		return plano;
	}
	public void setPlano(Plano plano) {
		this.plano = plano;
	}
	
	
}
