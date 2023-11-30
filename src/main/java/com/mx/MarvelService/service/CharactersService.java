package com.mx.marvelservice.service;

import com.mx.marvelservice.to.CharacterResponse;

public interface CharactersService {

	public CharacterResponse getAllCharacters();
	public CharacterResponse getCharactersById(long character);
}
