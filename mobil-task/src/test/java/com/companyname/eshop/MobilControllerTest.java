package com.companyname.eshop;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.companyname.eshop.controller.MobilController;
import com.companyname.eshop.model.Mobil;
import com.companyname.eshop.service.MobilService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;



@RunWith(SpringRunner.class)
@ExtendWith(SpringExtension.class)
@WebMvcTest(value=MobilController.class)
class MobilControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private MobilService mobilService;
	
	  

	@Test
	public void getAllMobilesTest() throws Exception {
		Mobil mobilSample1 = new Mobil();
		mobilSample1.setDescription("A mobil with a water fountain picture");
		mobilSample1.setFountain("R0lGODlhAQABAIAAAAUEBAAAACwAAAAAAQABAAACAkQBADs=");
		mobilSample1.setId(5);
		mobilSample1.setPrice(5000);
		mobilSample1.setTitle("Nokia");
		
		Mobil mobilSample2 = new Mobil();
		mobilSample2.setDescription("A mobil with a water fountain picture");
		mobilSample2.setFountain("R0lGODlhAQABAIAAAAUEBAAAACwAAAAAAQABAAACAkQBADs=");
		mobilSample2.setId(5);
		mobilSample2.setPrice(6000);
		mobilSample2.setTitle("Iphone");
		
		List<Mobil> mobilList = new ArrayList<>();
		mobilList.add(mobilSample1);
		mobilList.add(mobilSample2);
		
		Mockito.when(mobilService.getAllMobiles()).thenReturn(mobilList);
	
		String URI = "/eshop//mobiles";
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				                       .get(URI)
				                       .accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		String outputInJson = result.getResponse().getContentAsString();
		String expectedJson = mapToJson(mobilList);
		assertThat(outputInJson).isEqualTo(expectedJson);
	}
	
	
	@Test
	public void getMobilByIdTest() throws Exception {
		Mobil mockMobil = new Mobil();
		mockMobil.setDescription("A mobil with a water fountain picture");
		mockMobil.setFountain("R0lGODlhAQABAIAAAAUEBAAAACwAAAAAAQABAAACAkQBADs=");
		mockMobil.setId(2);
		mockMobil.setPrice(5000);
		mockMobil.setTitle("Smsung");
		
		Mockito.when(mobilService.getMobilByID(2)).thenReturn(mockMobil);
		
		String URI = "/eshop//mobiles/2";
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				                       .get(URI)
				                       .accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		String expectedJson = this.mapToJson(mockMobil);
		String outputInJson = result.getResponse().getContentAsString();
		assertThat(outputInJson).isEqualTo(expectedJson);
	}
	

	
	@Test
	public void createWatchTest() throws Exception {
		Mobil mockMobil = new Mobil();
		mockMobil.setDescription("A mobil with a water fountain picture");
		mockMobil.setFountain("R0lGODlhAQABAIAAAAUEBAAAACwAAAAAAQABAAACAkQBADs=");
		mockMobil.setId(1);
		mockMobil.setPrice(2000);
		mockMobil.setTitle("Nokia");
		
		String inputInJson = this.mapToJson(mockMobil);
		String URI = "/eshop/mobiles";
		
		Mockito.when(mobilService.createMobil(Mockito.any(Mobil.class))).thenReturn(mockMobil);
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders
				                       .post(URI)
				                       .accept(MediaType.APPLICATION_JSON).content(inputInJson)
				                       .contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();
		String outputInJson = response.getContentAsString();
		assertThat(outputInJson).isEqualTo(inputInJson);
		assertEquals(HttpStatus.CREATED.value(), response.getStatus());
	}
	
	
	@Test
	public void updateMoTest() throws Exception {
	
		Mobil mockMobil = new Mobil();
		mockMobil.setDescription("A mobil with a water fountain picture");
		mockMobil.setFountain("R0lGODlhAQABAIAAAAUEBAAAACwAAAAAAQABAAACAkQBADs=");
		mockMobil.setId(1);
		mockMobil.setPrice(5000);
		mockMobil.setTitle("Nokia");
		Mockito.when(mobilService.updateMobil(mockMobil)).thenReturn(HttpStatus.OK);
	}
	
	
	 // in this method: Map an Object into a JSON String, Uses a Jackson ObjectMapper.
	
	private String mapToJson(Object object) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.writeValueAsString(object);
	}
	

}
