package br.com.oobj.githippie.model;

public class Tarefa {
	
	private int idTarefa;
	private Repositorio repositorio;
	
	private String tituloTarefa;
	private String descricaoTarefa;
	private String autorTarefa;
	private String dataCriacao;
	private boolean tarefaAberta;
	
	public int getIdTarefa() {
		return idTarefa;
	}
	public void setIdTarefa(int idTarefa) {
		this.idTarefa = idTarefa;
	}
	public Repositorio getRepositorio() {
		return repositorio;
	}
	public void setRepositorio(Repositorio repositorio) {
		this.repositorio = repositorio;
	}
	public String getTituloTarefa() {
		return tituloTarefa;
	}
	public void setTituloTarefa(String tituloTarefa) {
		this.tituloTarefa = tituloTarefa;
	}
	public String getDescricaoTarefa() {
		return descricaoTarefa;
	}
	public void setDescricaoTarefa(String descricaoTarefa) {
		this.descricaoTarefa = descricaoTarefa;
	}
	public String getAutorTarefa() {
		return autorTarefa;
	}
	public void setAutorTarefa(String autorTarefa) {
		this.autorTarefa = autorTarefa;
	}
	public String getDataCriacao() {
		return dataCriacao;
	}
	public void setDataCriacao(String dataCriacao) {
		this.dataCriacao = dataCriacao;
	}
	public boolean isTarefaAberta() {
		return tarefaAberta;
	}
	public void setTarefaAberta(boolean tarefaAberta) {
		this.tarefaAberta = tarefaAberta;
	}
	
	

}
