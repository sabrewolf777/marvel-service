package com.mx.MarvelService.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/characters")
@CrossOrigin(origins = "*")
public class CharactersController {

	Logger logger = LoggerFactory.getLogger(CharactersController.class);

	@GetMapping(value="/getAllCharacters")
	public ResponseEntity<ResponseDocument> getAllCharacters(){
		logger.info("::::::::: getAllCharacters {} :::::::::");
		return new ResponseEntity<> (documentService.getAllFilesNames(path),  HttpStatus.OK);
	}
	
		
	
}
