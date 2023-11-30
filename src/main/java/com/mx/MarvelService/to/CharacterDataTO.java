package com.mx.marvelservice.to;

import java.util.List;
import lombok.Data;

@Data
public class CharacterDataTO {
	private int available;
	private String collectionURI;
	private List<ItemTO> lstItemTO;
	private int returned;
}
