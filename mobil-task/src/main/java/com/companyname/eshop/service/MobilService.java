package com.companyname.eshop.service;

import java.util.List;

import org.springframework.http.HttpStatus;

import com.companyname.eshop.model.Mobil;


public interface MobilService {
	
	Mobil createMobil(Mobil mobil);
	
	HttpStatus updateMobil(Mobil mobil);
	
	List<Mobil>  getAllMobiles();
	
	Mobil getMobilByID(long id);
	
	HttpStatus deleteMobil(long id);

}
