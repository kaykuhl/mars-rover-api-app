package com.blissfullycoding.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.blissfullycoding.response.MarsRoverApiResponse;

@Service
public class MarsRoverApiService {
	
	private static final String API_KEY = "E62x6TTItGoEuhSgIBdMQDZvKvCbPr2H0MkZ6LNL";

	public MarsRoverApiResponse getRoverData(String roverType, Integer marsSol) {
		RestTemplate rt = new RestTemplate();
	
		ResponseEntity<MarsRoverApiResponse> response = rt.getForEntity("https://api.nasa.gov/mars-photos/api/v1/rovers/" + roverType + "/photos?sol=" + marsSol + "&api_key=" + API_KEY, MarsRoverApiResponse.class);
		
		return response.getBody();
	}
	
	
}
