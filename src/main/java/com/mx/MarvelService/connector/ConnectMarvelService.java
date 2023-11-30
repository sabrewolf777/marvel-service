package com.mx.marvelservice.connector;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import com.mx.marvelservice.Utils.GetDate;
import com.mx.marvelservice.config.SecurityConfig;
import com.mx.marvelservice.models.BitacoraModel;
import com.mx.marvelservice.respository.BitacoraRepository;

@Service
public class ConnectMarvelService {
	
	Logger logger = LoggerFactory.getLogger(ConnectMarvelService.class);
	
	@Autowired
	SecurityConfig securityConfig;
	
	@Autowired
	BitacoraRepository peticionesRepository;

	public  String connectToServiceGET() {
		
		final GetDate getReqDate= new GetDate();
		
		final HttpHeaders headers = new HttpHeaders();
		
		headers.set("Accept", "application/json");
		
		final String horaReq=getReqDate.getDateRequest();
		
		logger.info("horaReq: {}",horaReq);
		
	    final UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromUriString(securityConfig.getUrlMarvenApi())
		    													    .queryParam("ts",horaReq)
											    	                .queryParam("apikey", securityConfig.getPublicKey())
											    	                .queryParam("hash", getHash(horaReq));
										    	             
	    final RestTemplate restTemplate  = new RestTemplate();
	    
	    final ResponseEntity<String> response = restTemplate.exchange(uriBuilder.toUriString(), HttpMethod.GET, new HttpEntity<>(headers), String.class);
	    
	    peticionesRepository.save(getReqModel(securityConfig.getUrlMarvenApi(),horaReq,Base64.getEncoder().encodeToString( uriBuilder.toUriString().getBytes()),Base64.getEncoder().encodeToString( response.getBody().getBytes() )));
	    
		return response.getBody();
	}
	
	
	public  String connectToServiceGET(final long character) {
		
		
		
		final HttpHeaders headers = new HttpHeaders();
		
		headers.set("Accept", "application/json");

		final GetDate getReqDate= new GetDate();
		
		final String horaReq=getReqDate.getDateRequest();
		
		logger.info("horaReq: {}",horaReq);
		
		final String req= securityConfig.getUrlMarvenApi()+"/"+character;
		
	    final UriComponentsBuilder uriBuilder = UriComponentsBuilder.fromUriString(req)
		    													    .queryParam("ts", horaReq)
											    	                .queryParam("apikey", securityConfig.getPublicKey())
											    	                .queryParam("hash", getHash(horaReq));
										    	             
	    final RestTemplate restTemplate  = new RestTemplate();
	    
	    final ResponseEntity<String> response = restTemplate.exchange(uriBuilder.toUriString(), HttpMethod.GET, new HttpEntity<>(headers), String.class);
	    
	    peticionesRepository.save(getReqModel(req,horaReq, Base64.getEncoder().encodeToString(uriBuilder.toUriString().getBytes()) , Base64.getEncoder().encodeToString(response.getBody().getBytes())));
	    
		return response.getBody();
	}
	
	public BitacoraModel getReqModel(final String api,final String fecha,final String request,final String response) {
		
		return  BitacoraModel.builder().api(api)
										 .fecha(fecha)
										 .request(request)
										 .response(response)
										 .build();
	}
	
	
	private String getHash(final String horaReq) {
		String strHash="";
		try {
			logger.info("::::: getHash() :::::");
			final byte[] msg = (horaReq+securityConfig.getPrivateKey()+securityConfig.getPublicKey()).getBytes();
			final MessageDigest md = MessageDigest.getInstance("MD5");
			final byte[] hash = md.digest(msg);
			final StringBuilder strBuilder = new StringBuilder();
		    for(byte b : hash) {
		    	strBuilder.append(String.format("%02x", b));
		    }
		    strHash = strBuilder.toString();
		} catch (NoSuchAlgorithmException e) {
			logger.error("ERROR en getHash():",e);
		}
		return strHash;
	}
	
	
	
}
