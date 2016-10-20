package br.ufes.dcel.beer.model;

public enum TipoCerveja {
	LAGER("Lager"), ALE ("Ale"), STOUT("Stout"), WEISS("Weiss");
	
	private String descricao;
	
	private TipoCerveja(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao(){
		return descricao;
	}
}
