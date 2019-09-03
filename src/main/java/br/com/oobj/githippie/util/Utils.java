package br.com.oobj.githippie.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import br.com.oobj.githippie.dao.impl.ManipularUsuarioImpl;
import br.com.oobj.githippie.model.Organizacao;
import br.com.oobj.githippie.model.Plano;
import br.com.oobj.githippie.model.Usuario;

public class Utils {
	DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	private Usuario usuario;
	private Organizacao organizacao;
	
	public Usuario getUserTest() {
		usuario = new Usuario();
		Date date = new Date();

		usuario.setNomeUsuario("Rodrigo");
		usuario.setSobreNomeUsuario("Ferreira da Silva");
		usuario.setEmailUsuario("rodrigo.ferreira@oobj.com.br");
		usuario.setUrlLinkedin("www.linkedin.com.br/fdsrodrigo");
		usuario.setSexo("M".charAt(0));
		usuario.setDataCadastro(dateFormat.format(date).toString());
		usuario.setPlano(Plano.FREE);
		
		return usuario;
	}

	public Organizacao getOrganizacaoTest() {
		organizacao = new Organizacao();
		usuario = this.getUserTest();

		Date date = new Date();
		
		organizacao.setNomeOrganizacao("Oobj TI");
		organizacao.setDescricaoOrganizacao("Repositório da Oobj TI");
		organizacao.setUsuarioOwner(usuario);
		organizacao.setDataCriacao(dateFormat.format(date).toString());
		
		return organizacao;
	}
	
	public Usuario getUserTestRename(String nome) {
		Usuario usuario = new Usuario();
		Date date = new Date();

		usuario.setNomeUsuario(nome);
		usuario.setSobreNomeUsuario("Ferreira da Silva");
		usuario.setEmailUsuario("rodrigo.ferreira@oobj.com.br");
		usuario.setUrlLinkedin("www.linkedin.com.br/fdsrodrigo");
		usuario.setSexo("M".charAt(0));
		usuario.setDataCadastro(dateFormat.format(date).toString());
		usuario.setPlano(Plano.FREE);
		
		return usuario;
	}
	
	public Organizacao getOrganizacaoTestRename(String nome) {
		organizacao = new Organizacao();
		usuario = this.getUserTest();

		Date date = new Date();
		
		organizacao.setNomeOrganizacao(nome);
		organizacao.setDescricaoOrganizacao("Repositório da Oobj TI");
		organizacao.setUsuarioOwner(usuario);
		organizacao.setDataCriacao(dateFormat.format(date).toString());
		
		return organizacao;
	}
	
	public static String validarCampoObrigatorio() {
		System.out.println("O campo não pode ser vazio.. Digite novamente: ");
		Scanner ent = new Scanner(System.in);
		Boolean flag = false;
		String novoValor = "";
		while (!flag) {
			novoValor = ent.nextLine().toUpperCase();
			if (novoValor.isEmpty() || novoValor.trim().equals("")) {
				System.out.println("O campo não pode ser vazio.. Digite novamente: ");
			} else {
				flag = true;
			}
		}
		return novoValor;
	}
	
	public static int[] buscarPorEmail(String email) {
		List<Usuario> usuarioEncontrado;
		ManipularUsuarioImpl usuarioDAO = new ManipularUsuarioImpl();
		usuarioEncontrado = usuarioDAO.consultarPorQualquerColuna("email_usuario", email);
		int[] ids = new int[usuarioEncontrado.size()];

		if (usuarioEncontrado.size() == 1) {
			System.out.println("Usuário(s) Encontrado(s): " + usuarioEncontrado.size());
			System.out.println("------------------------------------------------------------------------------");
			System.out.println("ID usuário: " + usuarioEncontrado.get(0).getIdUsuario());
			System.out.println("Nome usuário: " + usuarioEncontrado.get(0).getNomeUsuario() + " "
					+ usuarioEncontrado.get(0).getSobreNomeUsuario());
			System.out.println("Email usuário: " + usuarioEncontrado.get(0).getEmailUsuario());
			System.out.println("Sexo usuário: " + usuarioEncontrado.get(0).getSexo());
			System.out.println("URL Linkedin usuário: " + usuarioEncontrado.get(0).getUrlLinkedin());
			System.out.println("Plano usuário: " + usuarioEncontrado.get(0).getPlano().name());
			System.out.println("Data de cadastro usuário: " + usuarioEncontrado.get(0).getDataCadastro());
			System.out.println("------------------------------------------------------------------------------");
			ids[0] = usuarioEncontrado.get(0).getIdUsuario();
		}
		return ids;
	}
	
	public static int[] buscarPorNome(String nome) {
		List<Usuario> usuarioEncontrado;
		ManipularUsuarioImpl usuarioDAO = new ManipularUsuarioImpl();
		usuarioEncontrado = usuarioDAO.consultarPorQualquerColuna("nome_usuario", nome);
		int[] ids = new int[usuarioEncontrado.size()];
		if (usuarioEncontrado.size() == 0) {
			return ids;
		}
		System.out.println("Usuário(s) Encontrado(s): " + usuarioEncontrado.size());
		int x = 0;
		for (Usuario usuario : usuarioEncontrado) {
			System.out.println("------------------------------------------------------------------------------");
			System.out.println("ID usuário: " + usuario.getIdUsuario());
			System.out.println("Nome usuário: " + usuario.getNomeUsuario() + " " + usuario.getSobreNomeUsuario());
			System.out.println("Email usuário: " + usuario.getEmailUsuario());
			System.out.println("Sexo usuário: " + usuario.getSexo());
			System.out.println("URL Linkedin usuário: " + usuario.getUrlLinkedin());
			System.out.println("Plano usuário: " + usuario.getPlano().name());
			System.out.println("Data de cadastro usuário: " + usuario.getDataCadastro());
			System.out.println("------------------------------------------------------------------------------");
			ids[x] = usuario.getIdUsuario();
			x++;
		}
		return ids;
	}
}
