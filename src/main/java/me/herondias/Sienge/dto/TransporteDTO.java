package me.herondias.Sienge.dto;

public class TransporteDTO {

	private Integer pesoCarga;    
    private double distPavimentada;
	private double distNaoPavimentada;
	private double fatorMultiplicador;
	private double custoFrete;
	
	public Integer getPesoCarga() {
		return pesoCarga;
	}
	
	public void setPesoCarga(Integer pesoCarga) {
		this.pesoCarga = pesoCarga;
	}
	
	public double getDistPavimentada() {
		return distPavimentada;
	}
	
	public void setDistPavimentada(double distPavimentada) {
		this.distPavimentada = distPavimentada;
	}
	
	public double getDistNaoPavimentada() {
		return distNaoPavimentada;
	}
	
	public void setDistNaoPavimentada(double distNaoPavimentada) {
		this.distNaoPavimentada = distNaoPavimentada;
	}
	
	public double getFatorMultiplicador() {
		return fatorMultiplicador;
	}
	
	public void setFatorMultiplicador(double fatorMultiplicador) {
		this.fatorMultiplicador = fatorMultiplicador;
	}
	
	public double getCustoFrete() {
		return custoFrete;
	}

	public void setCustoFrete(double custoFrete) {
		this.custoFrete = custoFrete;
	}
}
