package com.blissfullycoding;

import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.blissfullycoding.response.MarsRoverApiResponse;

public class MarsRoverApiTest {

	@Test
	public void smallTest () {
	RestTemplate rt = new RestTemplate();
	
	ResponseEntity<MarsRoverApiResponse> response = rt.getForEntity("https://api.nasa.gov/mars-photos/api/v1/rovers/curiosity/photos?sol=2&api_key=E62x6TTItGoEuhSgIBdMQDZvKvCbPr2H0MkZ6LNL", MarsRoverApiResponse.class);
	System.out.println(response.getBody());
	}
	
	
}
