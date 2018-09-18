package me.herondias.Sienge.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import me.herondias.Sienge.dto.TransporteDTO;
import me.herondias.Sienge.enums.TipoRodovia;
import me.herondias.Sienge.model.TipoVeiculo;
import me.herondias.Sienge.repositories.TipoVeiculoRepository;

@Service
public class TransporteService {
	
	@Autowired
	private TipoVeiculoRepository tpVeiculoRepository;
	
	/**
	 * Efetua cálculo de simulação de custo de transporte
	 * @param transporte
	 * @return
	 */
    public double simularCustoTransporte(TransporteDTO transporte) {
    	double totalCustoTransporte = 0.0;
		
    	try {
			double custoPavimentada = (TipoRodovia.PAVIMENTADA.getCustoKm() * transporte.getDistPavimentada());
			double custoNPavimentada = (TipoRodovia.NAO_PAVIMENTADA.getCustoKm() * transporte.getDistNaoPavimentada());
			double custoTotalRodovias = (custoNPavimentada + custoPavimentada);
			
			totalCustoTransporte = (custoTotalRodovias * transporte.getFatorMultiplicador());
			
			if(transporte.getPesoCarga() > 5) {
				int custoToneladaExcedente = transporte.getPesoCarga() - 5;
				double kmRodado = transporte.getDistNaoPavimentada() + transporte.getDistPavimentada();
				double totalToneladaExcedente = custoToneladaExcedente * 0.02 * kmRodado;
				
				totalCustoTransporte += totalToneladaExcedente;
			}
		} catch (Exception e) {
			throw new RuntimeException("Falha ao realizar o cálculo do custo do transporte");
		}
		
		return totalCustoTransporte;
    }
    
    /**
     * Lista de Tipos de Veículos em ordem ascendente
     * @return
     */
    public List<TipoVeiculo> getTiposVeiculos() {
    	return tpVeiculoRepository.findAllByOrderByDescricaoAsc();
    }
    
    /**
     * Cadastra novo tipo de veículo
     * @param tipoVeiculo
     * @return
     * @throws Exception
     */
    public TipoVeiculo addTiposVeiculos(TipoVeiculo tipoVeiculo) throws Exception {
    	List<TipoVeiculo> resTipoVeiculo = tpVeiculoRepository.findByDescricao(tipoVeiculo.getDescricao());
    	if(resTipoVeiculo.size() > 0) {
    		throw new Exception("Já existe um tipo cadastrado com essa descrição: " + tipoVeiculo.getDescricao());
    	}
    	return tpVeiculoRepository.save(tipoVeiculo);
    }
}
