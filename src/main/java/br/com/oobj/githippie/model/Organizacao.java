package br.com.oobj.githippie.model;

import java.util.ArrayList;
import java.util.List;

public class Organizacao {
	private final Plano PLANO_ORGANIZACAO = Plano.ENTERPRISE;

	private int idOrganizacao;
	private String nomeOrganizacao;
	private String descricaoOrganizacao;
	private String dataCriacao;
	
	private Usuario usuarioOwner;
	private List<Usuario> usuariosAdm;
	private List<Usuario> usuariosParticipantes;
	
	public int getIdOrganizacao() {
		return idOrganizacao;
	}
	public void setIdOrganizacao(int idOrganizacao) {
		this.idOrganizacao = idOrganizacao;
	}
	public String getNomeOrganizacao() {
		return nomeOrganizacao;
	}
	public void setNomeOrganizacao(String nomeOrganizacao) {
		this.nomeOrganizacao = nomeOrganizacao;
	}
	public String getDescricaoOrganizacao() {
		return descricaoOrganizacao;
	}
	public void setDescricaoOrganizacao(String descricaoOrganizacao) {
		this.descricaoOrganizacao = descricaoOrganizacao;
	}
	public String getDataCriacao() {
		return dataCriacao;
	}
	public void setDataCriacao(String dataCriacao) {
		this.dataCriacao = dataCriacao;
	}
	public Usuario getUsuarioOwner() {
		return usuarioOwner;
	}
	public void setUsuarioOwner(Usuario usuarioOwner) {
		this.usuarioOwner = usuarioOwner;
	}
	public List<Usuario> getUsuariosAdm() {
		return usuariosAdm;
	}
	public void setUsuariosAdm(List<Usuario> usuariosAdm) {
		this.usuariosAdm = usuariosAdm;
	}
	public List<Usuario> getUsuariosParticipantes() {
		return usuariosParticipantes;
	}
	public void setUsuariosParticipantes(List<Usuario> usuariosParticipantes) {
		this.usuariosParticipantes = usuariosParticipantes;
	}
	public Plano getPLANO_ORGANIZACAO() {
		return PLANO_ORGANIZACAO;
	}

	
}
