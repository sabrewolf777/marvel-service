package com.mx.marvelservice.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.mx.marvelservice.service.BitacoraService;
import com.mx.marvelservice.to.BitacoraResponse;

@RestController
@RequestMapping("/bitacoras")
@CrossOrigin(origins = "*")
public class BitacoraController {

	Logger logger = LoggerFactory.getLogger(BitacoraController.class);
	
	@Autowired
	BitacoraService bitacoraService;
	
	@GetMapping(value="/getAllBitacoras")
	public ResponseEntity<BitacoraResponse> getAllCharacters(){
		logger.info("::::::::: getAllBitacoras  :::::::::");
		return new ResponseEntity<> (bitacoraService.getAllBitacora(),  HttpStatus.OK);
	}
	
}
