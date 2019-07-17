package br.com.oobj.githippie.model;

public class VinculoUsuarioOrganizacao {

	private int id;
	private Usuario usuario;
	private Organizacao organizacao;
	private Boolean isAdmin;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Organizacao getOrganizacao() {
		return organizacao;
	}

	public void setOrganizacao(Organizacao organizacao) {
		this.organizacao = organizacao;
	}

	public Boolean getIsAdmin() {
		return isAdmin;
	}

	public void setIsAdmin(int isAdmin) {
		if (isAdmin == 1)
			this.isAdmin = true;
		else
			this.isAdmin = false;

	}
	
	public void setIsAdminBool(Boolean isAdmin) {
			this.isAdmin = isAdmin;
	}

}
