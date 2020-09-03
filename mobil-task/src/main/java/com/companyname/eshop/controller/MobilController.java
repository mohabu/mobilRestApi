package com.companyname.eshop.controller;

import java.util.List;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.companyname.eshop.model.Mobil;
import com.companyname.eshop.service.MobilService;

import io.swagger.annotations.ApiOperation;



@RestController
@RequestMapping("/eshop")
public class MobilController {

  @Autowired	
  private MobilService mobilService;
  
  
  
  @PostMapping("/mobiles")
  @ApiOperation(value="create a new mobil", 
  notes="happy response will be HttpStatus value CREATED and created mobil object in response body.",
  response=HttpStatus.class)
  public ResponseEntity<Mobil> createMobil(@Valid @RequestBody Mobil mobil) {
	  
	  if(mobil != null) {
		  return new ResponseEntity<Mobil>(mobilService.createMobil(mobil),HttpStatus.CREATED);
	  }
	  
	return new ResponseEntity<Mobil>(HttpStatus.BAD_REQUEST);
  }
  

  @GetMapping("/mobiles")
  @ApiOperation(value="get All mobiles which are exist in Database", 
  notes="happy response will be HttpStatus value FOUND and list of all objects.",
  response=HttpStatus.class)
  public ResponseEntity<List<Mobil>> getAllMobiles(){
	  if(!mobilService.getAllMobiles().isEmpty()) {
		  return new ResponseEntity<List<Mobil>>(mobilService.getAllMobiles(),HttpStatus.FOUND);
	  }
	  return  new ResponseEntity<List<Mobil>>(HttpStatus.NOT_FOUND);
   }
  
  
  @GetMapping("/mobiles/{id}")
  @ApiOperation(value="get mobil from database by Id", 
  notes="happy response will be HttpStatus value OK and mobil object.",
  response=HttpStatus.class)
  public ResponseEntity<Mobil> getMobilById(@PathVariable long id){
	  if(mobilService.getMobilByID(id) == null) {
		  return new ResponseEntity<Mobil>(HttpStatus.NOT_FOUND); 
		 
	  }
	  return  new ResponseEntity<Mobil>(mobilService.getMobilByID(id),HttpStatus.FOUND);
	
   }
  
  
  @DeleteMapping("/mobiles/{id}")
  @ApiOperation(value="delete mobil from databse by its id.", 
  notes="happy response will be HttpStatus value OK .",
  response=HttpStatus.class)
  public HttpStatus deleteMobil(@PathVariable long id) {
	  
	  return mobilService.deleteMobil(id);
  }
  
  
  @PutMapping("/mobiles/{id}")
  @ApiOperation(value="update existing mobil in database by passing its id and new mobil object as a params.", 
  notes="happy response will be HttpStatus value OK .",
  response=HttpStatus.class)
  public HttpStatus updateMobil(@PathVariable long id, @Valid @RequestBody Mobil mobil) {
	  mobil.setId(id);
	  return mobilService.updateMobil(mobil);
  }
  
  
}
