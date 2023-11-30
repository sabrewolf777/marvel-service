package com.mx.marvelservice.service;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mx.marvelservice.connector.ConnectMarvelService;
import com.mx.marvelservice.to.CharacterResponse;
import com.mx.marvelservice.to.CharacterTO;
import com.mx.marvelservice.to.MensajeResponse;
import com.mx.marvelservice.to.ThumbnailTO;

@Service
public class CharactersServiceImpl implements CharactersService{

	Logger logger = LoggerFactory.getLogger(CharactersServiceImpl.class);
	
	@Autowired
	ConnectMarvelService conect;
	
	public CharacterResponse getAllCharacters() {
		final MensajeResponse mensaje= new MensajeResponse();
		final CharacterResponse response= new CharacterResponse();
		try {
			response.setLstCharacterTO(transJsonToChar(conect.connectToServiceGET()));
	        mensaje.setCodigo(200);
	        mensaje.setMensaje("se obtuvo la lista de personajes correctamente");
	        response.setMensaje(mensaje);
		}catch(Exception e) {
			mensaje.setCodigo(-1);
		    mensaje.setMensaje("ERROR: no se pudo obtener la lista de personajes");
		    logger.info(mensaje.getMensaje()+"::",e);
		}
		return response;
	}
	
	public CharacterResponse getCharactersById(final long character) {
		final MensajeResponse mensaje= new MensajeResponse();
		final CharacterResponse response= new CharacterResponse();
		try {
			response.setLstCharacterTO(transJsonToChar(conect.connectToServiceGET(character)));
	        mensaje.setCodigo(200);
	        mensaje.setMensaje("se obtuvo la lista de caracteres correctamente");
	        response.setMensaje(mensaje);
		}catch(Exception e) {
			mensaje.setCodigo(-1);
		    mensaje.setMensaje("ERROR: no se pudo obtener la lista de caracteres");
		    logger.info(mensaje.getMensaje()+"::",e);
		}
		return response;
	}

		
	public List<CharacterTO> transJsonToChar(final String json) {
		List<CharacterTO> lstCharacter= new ArrayList<>();
		try {
			logger.info("::::: transJsonToChar :::::");
			JSONObject object= new JSONObject(json);
			JSONObject data= (JSONObject) object.get("data");	
			JSONArray results= (JSONArray) data.get("results");
			
			
			for(int i = 0; i < results.length(); i++){
				
				JSONObject obj = results.getJSONObject(i);
				JSONObject tharray= (JSONObject) obj.get("thumbnail");
				ThumbnailTO th= new ThumbnailTO();
				th.setPath(tharray.getString("path"));
				th.setExtension(tharray.getString("extension"));
				
				final CharacterTO tmp=CharacterTO.builder()
											     .id(obj.getInt("id"))
											     .name( obj.getString("name"))
												 .description(obj.getString("description"))
												 .modified(obj.getString("modified"))
												 .resourceURI(obj.getString("resourceURI"))
												 .thumbnail(th)
												 .imagen(tharray.getString("path")+"."+tharray.getString("extension"))
												 .build();	
				lstCharacter.add(tmp);
			}
				
		} catch (JSONException e) {
			logger.info("Error en transJsonToChar:",e);
		}
		return lstCharacter;
	}
	
	
}
