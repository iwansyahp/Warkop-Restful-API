package id.warkop.api.v1.warung;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiMethod.HttpMethod;

import id.warkop.model.warung.Warung;
import id.warkop.model.warung.WarungCtrl;

@Api(name = "warung",
title = "Layanan Warung",
version="v1",
description="API untuk resource warung")
public class WarungApi {
	
	@ApiMethod(name="baru", 
			path="barang", 
			httpMethod=HttpMethod.POST)
	public JSONWarung baru(
			JSONWarung jWarung) throws Exception {
		JSONWarung response = null;
		
		Warung warung = new WarungCtrl().baru(jWarung.getNama(),jWarung.getAlamat(), jWarung.getNoTelp(), jWarung.getNamaPemilik());
		
		response = new JSONWarung(warung);
		
		return response;
	}
	}
