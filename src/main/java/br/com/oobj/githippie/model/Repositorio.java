package br.com.oobj.githippie.model;

import java.util.List;

public class Repositorio {

	private int idRepositorio;
	private String nomeRepositorio;
	private String descricao;
	private String dataCriacao;
	private String tipoRepositorio;

	private Usuario usuarioOwner;
	private List<Usuario> usuariosColaboradores;
	private List<Tarefa> tarefas;

	public List<Tarefa> getTarefas() {
		return tarefas;
	}

	public void setTarefas(List<Tarefa> tarefas) {
		this.tarefas = tarefas;
	}

	public int getIdRepositorio() {
		return idRepositorio;
	}

	public void setIdRepositorio(int idRepositorio) {
		this.idRepositorio = idRepositorio;
	}

	public String getNomeRepositorio() {
		return nomeRepositorio;
	}

	public void setNomeRepositorio(String nomeRepositorio) {
		this.nomeRepositorio = nomeRepositorio;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(String dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	public String getTipoRepositorio() {
		return tipoRepositorio;
	}

	public void setTipoRepositorio(int tipoRepostorio) {
		if (tipoRepostorio == 0) {
			System.out.println("Agora esse repositório hé Público");
			this.tipoRepositorio = "Público";
		}else if (tipoRepostorio == 1) {
			System.out.println("Agora esse repositório hé Privado");
			this.tipoRepositorio = "Privado";
		}else {
			System.out.println("Valor informado hé invalido, vou retornar null..");
			this.tipoRepositorio = null;
		}
	}

	public Usuario getUsuarioOwner() {
		return usuarioOwner;
	}

	public void setUsuarioOwner(Usuario usuarioOwner) {
		this.usuarioOwner = usuarioOwner;
	}

	public List<Usuario> getUsuariosColaboradores() {
		return usuariosColaboradores;
	}

	public void setUsuariosColaboradores(List<Usuario> usuariosColaboradores) {
		this.usuariosColaboradores = usuariosColaboradores;
	}

}
