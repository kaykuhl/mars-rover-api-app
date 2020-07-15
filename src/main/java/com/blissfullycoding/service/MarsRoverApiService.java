package com.blissfullycoding.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.blissfullycoding.response.MarsRoverApiResponse;

@Service
public class MarsRoverApiService {

	public MarsRoverApiResponse getRoverData() {
		RestTemplate rt = new RestTemplate();
	
		ResponseEntity<MarsRoverApiResponse> response = rt.getForEntity("https://api.nasa.gov/mars-photos/api/v1/rovers/curiosity/photos?sol=2&api_key=E62x6TTItGoEuhSgIBdMQDZvKvCbPr2H0MkZ6LNL", MarsRoverApiResponse.class);
		
		return response.getBody();
	}
	
	
}
