package com.companyname.eshop.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.companyname.eshop.model.Mobil;
import com.companyname.eshop.repository.MobilRepository;



@Transactional
@Service
public class MobilServiceImpl implements MobilService {
	
	@Autowired
	private MobilRepository mobilRepository;

	@Override
	public Mobil createMobil(Mobil mobil) {
		
		return  mobilRepository.save(mobil);
	}
	

	@Override
	public HttpStatus updateMobil(Mobil mobil) {
		Optional<Mobil> mobilInDb = mobilRepository.findById(mobil.getId());
		if(!mobilInDb.isPresent()) {
			return HttpStatus.NOT_FOUND;
		}else {
			Mobil mobilUpdated = mobilInDb.get();
			mobilUpdated.setId(mobil.getId());
			mobilUpdated.setTitle(mobil.getTitle());
			mobilUpdated.setCreatedDate(new Date());
			mobilUpdated.setDescription(mobil.getDescription());
			mobilUpdated.setFountain(mobil.getFountain());
			mobilUpdated.setPrice(mobil.getPrice());
			mobilRepository.save(mobilUpdated);
			return HttpStatus.OK;
		}

	}

	@Override
	public List<Mobil> getAllMobiles() {
		
		return mobilRepository.findAll();
	}

	@Override
	public Mobil getMobilByID(long mobilId) {
		Optional<Mobil> mobilInDb = mobilRepository.findById(mobilId);
		if(mobilInDb.isPresent()) {
			return mobilInDb.get();
		}else {
			return null;
		}
		
	}

	@Override
	public 	HttpStatus deleteMobil(long id) {
		Optional<Mobil> mobil = mobilRepository.findById(id);
		if(!mobil.isPresent()) {
			return HttpStatus.NOT_FOUND;
		}else {
			mobilRepository.delete(mobil.get());
			return HttpStatus.OK;
		}
	}

}
