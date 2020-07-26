package com.blissfullycoding.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.blissfullycoding.dto.HomeDto;
import com.blissfullycoding.response.MarsRoverApiResponse;



@Service
public class MarsRoverApiService {
	
	private static final String API_KEY = "E62x6TTItGoEuhSgIBdMQDZvKvCbPr2H0MkZ6LNL";

	public MarsRoverApiResponse getRoverData(HomeDto homeDto) {
		RestTemplate rt = new RestTemplate();
		
		List<String> apiUrlEndpoints = getApiUrlEndpoints(homeDto);
		List<MarsPhoto> photos = new ArrayList<>();
		MarsRoverApiResponse response =  new MarsRoverApiResponse();
		
		apiUrlEndpoints.stream()
					.forEach(url -> {
						MarsRoverApiResponse apiResponse = rt.getForObject(url, MarsRoverApiResponse.class);
						photos.addAll(apiResponse.getPhotos()); 
					});
					
		response.setPhotos(photos);
		
		return response;
	}
	
	public List<String> getApiUrlEndpoints (HomeDto homeDto) {
		List<String> urls = new ArrayList<>();		
		if(Boolean.TRUE.equals(homeDto.getCameraFhaz())) {
			urls.add("https://api.nasa.gov/mars-photos/api/v1/rovers/" + homeDto.getMarsApiRoverData() + "/photos?sol=" + homeDto.getMarsSol() + "&api_key=" + API_KEY + "&camera=FHAZ");
		} 
		if (Boolean.TRUE.equals(homeDto.getCameraChemcam()) && "curiosity".equalsIgnoreCase(homeDto.getMarsApiRoverData())) {
			urls.add("https://api.nasa.gov/mars-photos/api/v1/rovers/" + homeDto.getMarsApiRoverData() + "/photos?sol=" + homeDto.getMarsSol() + "&api_key=" + API_KEY + "&camera=CHEMCAM");			
		}
		if (Boolean.TRUE.equals(homeDto.getCameraMahli()) && "curiosity".equalsIgnoreCase(homeDto.getMarsApiRoverData())) {
			urls.add("https://api.nasa.gov/mars-photos/api/v1/rovers/" + homeDto.getMarsApiRoverData() + "/photos?sol=" + homeDto.getMarsSol() + "&api_key=" + API_KEY + "&camera=MAHLI");			
		}
		if (Boolean.TRUE.equals(homeDto.getCameraMardi()) && "curiosity".equalsIgnoreCase(homeDto.getMarsApiRoverData())) {
			urls.add("https://api.nasa.gov/mars-photos/api/v1/rovers/" + homeDto.getMarsApiRoverData() + "/photos?sol=" + homeDto.getMarsSol() + "&api_key=" + API_KEY + "&camera=MARDI");			
		}
		if (Boolean.TRUE.equals(homeDto.getCameraMast()) && "curiosity".equalsIgnoreCase(homeDto.getMarsApiRoverData())) {
			urls.add("https://api.nasa.gov/mars-photos/api/v1/rovers/" + homeDto.getMarsApiRoverData() + "/photos?sol=" + homeDto.getMarsSol() + "&api_key=" + API_KEY + "&camera=MAST");			
		}
		if (Boolean.TRUE.equals(homeDto.getCameraMinites() && !"curiosity".equalsIgnoreCase(homeDto.getMarsApiRoverData()))) {
			urls.add("https://api.nasa.gov/mars-photos/api/v1/rovers/" + homeDto.getMarsApiRoverData() + "/photos?sol=" + homeDto.getMarsSol() + "&api_key=" + API_KEY + "&camera=MINITES");			
		}
		if (Boolean.TRUE.equals(homeDto.getCameraNavcam())) {
			urls.add("https://api.nasa.gov/mars-photos/api/v1/rovers/" + homeDto.getMarsApiRoverData() + "/photos?sol=" + homeDto.getMarsSol() + "&api_key=" + API_KEY + "&camera=NAVCAM");			
		}
		if (Boolean.TRUE.equals(homeDto.getCameraPancam() && !"curiosity".equalsIgnoreCase(homeDto.getMarsApiRoverData()))) {
			urls.add("https://api.nasa.gov/mars-photos/api/v1/rovers/" + homeDto.getMarsApiRoverData() + "/photos?sol=" + homeDto.getMarsSol() + "&api_key=" + API_KEY + "&camera=PANCAM");			
		}
		if (Boolean.TRUE.equals(homeDto.getCameraRhaz())) {
			urls.add("https://api.nasa.gov/mars-photos/api/v1/rovers/" + homeDto.getMarsApiRoverData() + "/photos?sol=" + homeDto.getMarsSol() + "&api_key=" + API_KEY + "&camera=RHAZ");			
		}
		return urls;
	}
}
