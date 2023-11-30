package com.mx.marvelservice.to;

import java.util.List;
import lombok.Data;

@Data
public class BitacoraResponse {
	private List<BitacoraTO> lstBitacora;
	private MensajeResponse mensaje;
}
