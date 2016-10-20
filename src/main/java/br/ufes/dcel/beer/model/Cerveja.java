package br.ufes.dcel.beer.model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name="cerveja")
public class Cerveja {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@NotNull(message="{nome.notBlank}")
	@Size(min=5,max=50)
	private String nome;
	
	
	@NotBlank(message="Pais Origem é obrigatório")
	private String paisOrigem;
	
	@NotNull(message="Volume é obrigatório")
	private Integer volume;
	
	@NotNull(message="Valor é obrigatório")
	private BigDecimal valor;
	private String foto;
	private String url;
	
	@NotNull(message="Tipo é obrigatório")
	@Enumerated(EnumType.STRING)
	private TipoCerveja tipo;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getPaisOrigem() {
		return paisOrigem;
	}
	public void setPaisOrigem(String paisOrigem) {
		this.paisOrigem = paisOrigem;
	}
	public Integer getVolume() {
		return volume;
	}
	public void setVolume(Integer volume) {
		this.volume = volume;
	}
	public BigDecimal getValor() {
		return valor;
	}
	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}
	public String getFoto() {
		return foto;
	}
	public void setFoto(String foto) {
		this.foto = foto;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
	
	public TipoCerveja getTipo() {
		return tipo;
	}
	public void setTipo(TipoCerveja tipo) {
		this.tipo = tipo;
	}
	@Override
	public boolean equals(Object cerveja){
		Cerveja c = (Cerveja) cerveja;
		Long compId = (Long) c.getId();
		if(compId==null) return false;
		if(this.getId().equals(id)) return true;
		else return false;
	}
	
}
