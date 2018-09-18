package me.herondias.Sienge.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "tipo_veiculo")
public class TipoVeiculo {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "descricao", nullable = false)
	private String descricao;
	
	@Column(name = "fatorMultiplicador", nullable = false)
	private double fatorMultiplicador;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}
	
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public double getFatorMultiplicador() {
		return fatorMultiplicador;
	}
	
	public void setFatorMultiplicador(double fatorMultiplicador) {
		this.fatorMultiplicador = fatorMultiplicador;
	}
}
