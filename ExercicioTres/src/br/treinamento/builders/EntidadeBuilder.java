package br.treinamento.builders;

import br.treinamento.Entidade;

public class EntidadeBuilder {
	
	private Entidade entidade;
	
	private EntidadeBuilder() {
		//
	}
	
	public static EntidadeBuilder umaEntidade() {
		EntidadeBuilder builder = new EntidadeBuilder();
		
		builder.entidade = new Entidade();
		builder.entidade.setId(1L);
		builder.entidade.setNome("Entidade Padrão");
		
		return builder;
	}
	
	public Entidade agora() {
		return entidade;
	}
	
	public EntidadeBuilder comNome(String nome) {
		entidade.setNome(nome);
		
		return this;
	}
}
