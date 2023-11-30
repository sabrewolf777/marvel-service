package com.mx.marvelservice.service;

import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mx.marvelservice.models.BitacoraModel;
import com.mx.marvelservice.respository.BitacoraRepository;
import com.mx.marvelservice.to.BitacoraResponse;
import com.mx.marvelservice.to.BitacoraTO;
import com.mx.marvelservice.to.MensajeResponse;

@Service
public class BitacoraServiceImpl implements BitacoraService{

	Logger logger = LoggerFactory.getLogger(BitacoraServiceImpl.class);
	
	@Autowired
	BitacoraRepository bitacoraRepository;
	
	public BitacoraResponse getAllBitacora() {
		BitacoraResponse response= new BitacoraResponse();
		MensajeResponse mensaje= new MensajeResponse();
		try {
			response.setLstBitacora(modelTrasfTo( bitacoraRepository.findAll()));
	        mensaje.setCodigo(200);
	        mensaje.setMensaje("se obtuvo la bitacora correctamente");
	        response.setMensaje(mensaje);
	        logger.info(mensaje.getMensaje());
		}catch(Exception e) {
			mensaje.setCodigo(-1);
		    mensaje.setMensaje("ERROR: no se pudo obtener la bitacora");
		    logger.info(mensaje.getMensaje()+"::",e);
		}
		return response;
	}

	
	private List<BitacoraTO> modelTrasfTo(List<BitacoraModel> lstModel){
		List<BitacoraTO> lstBitacora= new ArrayList<>();
		lstModel.forEach(model->{
			BitacoraTO bitTempo= BitacoraTO.builder().api(model.getApi())
													 .fecha(model.getFecha())
													 .idusuario(model.getIdusuario())
													 .request(model.getRequest())
													 .response(model.getResponse())
													 .build();
			lstBitacora.add(bitTempo);
		});
		
		return  lstBitacora;
	}
	
	
}
