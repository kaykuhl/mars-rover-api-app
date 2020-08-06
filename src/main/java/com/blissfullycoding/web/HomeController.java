package com.blissfullycoding.web;

import java.lang.reflect.InvocationTargetException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.blissfullycoding.dto.HomeDto;
import com.blissfullycoding.response.MarsRoverApiResponse;
import com.blissfullycoding.service.MarsRoverApiService;

@Controller

public class HomeController {

	@Autowired
	private MarsRoverApiService roverService;
	
	@GetMapping("/")
	public String getHomeView (ModelMap model, Long userId) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		
		HomeDto homeDto = new HomeDto();
		
		homeDto.setMarsApiRoverData("Opportunity");
		homeDto.setMarsSol(1);

		if(userId == null) {
			homeDto = roverService.save(homeDto);
		} else {
		homeDto = roverService.findByUserId(userId);
		}
		MarsRoverApiResponse roverData = roverService.getRoverData(homeDto);
		model.put("roverData", roverData);
		model.put("homeDto", homeDto);
		model.put("validCameras", roverService.getValidCameras().get(homeDto.getMarsApiRoverData()));
	    if (!Boolean.TRUE.equals(homeDto.getRememberPreferences()) && userId != null) {
	        HomeDto defaultHomeDto = createDefaultHomeDto(userId);
	        roverService.save(defaultHomeDto);
	      }
		return "index";
	}
	
	@GetMapping("/savedPreferences")
	  @ResponseBody
	  public HomeDto getSavedPreferences (Long userId) {
	    if (userId != null)
	      return roverService.findByUserId(userId);
	    else
	      return createDefaultHomeDto(userId);
	  }
	  
	  private HomeDto createDefaultHomeDto(Long userId) {
	    HomeDto homeDto = new HomeDto();
	    homeDto.setMarsApiRoverData("Opportunity");
	    homeDto.setMarsSol(1);
	    homeDto.setUserId(userId);
	    return homeDto;
	  }
	  
	  @PostMapping("/")
	  public String postHomeView (HomeDto homeDto) {
	    homeDto = roverService.save(homeDto);
	    return "redirect:/?userId="+homeDto.getUserId();
	  }
	  
	}
	
