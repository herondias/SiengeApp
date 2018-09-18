package me.herondias.Sienge.utils;

import java.util.Random;

import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

public class TransporteMockFactory {
	
	static Random rand = new Random();
	
	public static HttpEntity<String> getCustoTransporte() {
		try {
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("pesoCarga", 5);
			jsonObject.put("fatorMultiplicador", new Double(1.05));		
			jsonObject.put("distPavimentada", 50);
			jsonObject.put("distNaoPavimentada", 30);

			return TransporteMockFactory.getHttpObject(jsonObject);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static HttpEntity<String> getCustoTransporteValidoCincoToneladas() {
		try {
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("pesoCarga", 4);
			jsonObject.put("fatorMultiplicador", new Double(1.0));		
			jsonObject.put("distPavimentada", 0);
			jsonObject.put("distNaoPavimentada", 60);
			
			return TransporteMockFactory.getHttpObject(jsonObject);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public static HttpEntity<String> getCustoTransporteValidoAcimaCincoToneladas() {
		try {
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("pesoCarga", 8);
			jsonObject.put("fatorMultiplicador", new Double(1.05));		
			jsonObject.put("distPavimentada", 100);
			jsonObject.put("distNaoPavimentada", 0);
			
			return TransporteMockFactory.getHttpObject(jsonObject);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public static HttpEntity<String> getTransporteDistanciaInvalida() {
		try {
			JSONObject mock = new JSONObject();
			mock.put("pesoCarga", 10);
			mock.put("fatorMultiplicador", new Double(1.12));		
			mock.put("distPavimentada", 0);
			mock.put("distNaoPavimentada", 0);
			
			return TransporteMockFactory.getHttpObject(mock);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
			
	private static HttpEntity<String> getHttpObject(JSONObject jObject) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> entity = new HttpEntity<String>(jObject.toString(), headers);
		
		return entity;
	}
}
