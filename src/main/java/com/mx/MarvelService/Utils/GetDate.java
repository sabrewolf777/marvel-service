package com.mx.marvelservice.Utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class GetDate {

	private SimpleDateFormat format = new SimpleDateFormat("ddMMyyyy");
	
	public String getDateRequest() {
		return format.format(new Date());
	}
	
}
