package me.herondias.Sienge.enums;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum TipoRodovia {
    PAVIMENTADA("Pavimentada", 0.54), 
    NAO_PAVIMENTADA("Não pavimentada", 0.62);

	private final String descricao;
    private final double custoKm;

    TipoRodovia(String descricao, double custoKm) {
    	this.descricao = descricao;
        this.custoKm = custoKm;
    }
    
    public String getDescricao() {
    	return descricao;
    }
    
    public double getCustoKm() {
    	return custoKm;
    }
}
