package me.herondias.Sienge.controller;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import me.herondias.Sienge.dto.TransporteDTO;
import me.herondias.Sienge.utils.TransporteMockFactory;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
public class TransporteControllerTest {
	
	@Autowired
	private TestRestTemplate restTemplate;
	private TransporteDTO transporte = null;
	private ObjectMapper mapper = new ObjectMapper();

	/**
	 * Testa retorno do custo para qualquer valor válido submetido
	 * @throws IOException 
	 * @throws JsonMappingException 
	 * @throws JsonParseException 
	 */
	@Test
	public void postSimularCusto_getCustoValido() throws JsonParseException, JsonMappingException, IOException {
		HttpEntity<String> httpEntityMock = TransporteMockFactory.getCustoTransporte();
	
		ResponseEntity<?> response = restTemplate.postForEntity("/simularCustoTransporte", httpEntityMock, String.class);		
		transporte = mapper.readValue(response.getBody().toString(), TransporteDTO.class);
		
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(transporte.getCustoFrete()).isEqualTo(new Double(47.88));
	}
	
	@Test
	public void postTransporteCusto_getResultadoAteCincoToneladas() throws JsonParseException, JsonMappingException, IOException {
		HttpEntity<String> httpEntityMock = TransporteMockFactory.getCustoTransporteValidoCincoToneladas();
		
		ResponseEntity<?> response = restTemplate.postForEntity("/simularCustoTransporte", httpEntityMock, String.class);
		transporte = mapper.readValue(response.getBody().toString(), TransporteDTO.class);
		
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(transporte.getCustoFrete()).isEqualTo(new Double(37.20));
	}
	
	@Test
	public void postTransporteCusto_getResultadoAcimaCincoToneladas() throws JsonParseException, JsonMappingException, IOException {
		HttpEntity<String> httpEntityMock = TransporteMockFactory.getCustoTransporteValidoAcimaCincoToneladas();
		
		ResponseEntity<?> response = restTemplate.postForEntity("/simularCustoTransporte", httpEntityMock, String.class);
		transporte = mapper.readValue(response.getBody().toString(), TransporteDTO.class);
		
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(transporte.getCustoFrete()).isEqualTo(new Double(62.70));
	}
	
	@Test
	public void postTransporteCusto_getResultadoDistanciaInvalida() throws JsonParseException, JsonMappingException, IOException {
		HttpEntity<String> httpEntityMock = TransporteMockFactory.getTransporteDistanciaInvalida();
		
		ResponseEntity<?> response = restTemplate.postForEntity("/simularCustoTransporte", httpEntityMock, String.class);
		
		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
