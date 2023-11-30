package com.mx.marvelservice.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mx.marvelservice.service.CharactersServiceImpl;
import com.mx.marvelservice.to.CharacterResponse;

@RestController
@RequestMapping("/characters")
@CrossOrigin(origins = "*")
public class CharactersController {

	Logger logger = LoggerFactory.getLogger(CharactersController.class);

	@Autowired
	CharactersServiceImpl charactersService;
	
	@GetMapping(value="/getAllCharacters")
	public ResponseEntity<CharacterResponse> getAllCharacters(){
		logger.info("::::::::: getAllCharacters {} :::::::::");
		return new ResponseEntity<> (charactersService.getAllCharacters(),  HttpStatus.OK);
	}
	
	@GetMapping(value="/getCharactersById/{characterId}")
	public ResponseEntity<CharacterResponse> getCharactersById(@PathVariable("characterId") long characterId){
		logger.info("::::::::: getCharactersById {} ",characterId);
		return new ResponseEntity<> (charactersService.getCharactersById(characterId),  HttpStatus.OK);
	}	
	
}
