package me.herondias.Sienge.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import me.herondias.Sienge.dto.TransporteDTO;
import me.herondias.Sienge.model.TipoVeiculo;
import me.herondias.Sienge.services.TransporteService;

@Controller
@RequestMapping("/")
public class TransporteController {
	
	@Autowired
    private TransporteService transpService;
	
	/**
	 * Carrega página inicial
	 * @return
	 */
	@GetMapping("/")
	public String home() {
		return "index";
	}
	
	/**
	 * Lista Tipos de Veiculos
	 * @return
	 */
	@GetMapping("/listarTiposVeiculos")
	public ResponseEntity<?> getTiposVeiculos() {
		return new ResponseEntity<>(transpService.getTiposVeiculos(), HttpStatus.OK);
	}
	
	/**
	 * Adiciona novo tipo de veiculo
	 * @param transporte
	 * @return
	 * @throws Exception 
	 */
	@PostMapping("/addTipoVeiculo")
	public ResponseEntity<?> addTipoVeiculo(@RequestBody(required = false) TipoVeiculo tipoVeiculo) throws Exception {
		transpService.addTiposVeiculos(tipoVeiculo);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	/**
	 * Efetua calculo para obter o custo do transporte
	 * @param transporte
	 * @return
	 */
	@PostMapping("/simularCustoTransporte")
	public ResponseEntity<?> simularCustoTransporte(@RequestBody TransporteDTO transporte) {
		validarParametros(transporte);

		transporte.setCustoFrete(transpService.simularCustoTransporte(transporte));
		
		return new ResponseEntity<>(transporte, HttpStatus.OK);
	}
	
	/**
	 * Verifica se
	 * @param transporte
	 */
	private void validarParametros(TransporteDTO transporte) {
		if(transporte.getPesoCarga() <= 0) {
    		throw new IllegalArgumentException("O Peso da Carga Transportada não pode ser menor ou igual a 0.");
		} else if(transporte.getDistPavimentada() <= 0 && transporte.getDistNaoPavimentada() <= 0) {
			throw new IllegalArgumentException("A valor da Distancia percorrida não pode ser menor ou igual a 0.");
		}
	}
}