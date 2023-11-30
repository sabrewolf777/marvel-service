package com.mx.marvelservice.to;

import java.util.List;

import lombok.Data;

@Data
public class CharacterResponse {
	private List<CharacterTO> lstCharacterTO;
	private List<Object> lstCharact;
	private String jsonResponse;
	private MensajeResponse mensaje;
}
