package com.mx.marvelservice.to;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class BitacoraTO {
	 private long idusuario;
	 private String fecha;
	 private String api;
	 private String request;
	 private String response; 
}
