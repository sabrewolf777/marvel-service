package com.mx.marvelservice.to;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class CharacterTO {
	private int id;
	private String name;
	private String description;
	private String modified;
	private String imagen;
	private ThumbnailTO thumbnail;
	private String resourceURI;
	private CharacterDataTO comics;
	private CharacterDataTO series;
	private CharacterDataTO stories;
	private CharacterDataTO events;
	private UrlTO urls;	
}
