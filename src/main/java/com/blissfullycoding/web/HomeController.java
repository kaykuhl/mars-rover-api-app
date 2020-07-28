package com.blissfullycoding.web;

import java.lang.reflect.InvocationTargetException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.blissfullycoding.dto.HomeDto;
import com.blissfullycoding.response.MarsRoverApiResponse;
import com.blissfullycoding.service.MarsRoverApiService;

@Controller

public class HomeController {

	@Autowired
	private MarsRoverApiService roverService;
	
	@GetMapping("/")
	public String getHomeView (ModelMap model, HomeDto homeDto) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		if (StringUtils.isEmpty(homeDto.getMarsApiRoverData())) {
			homeDto.setMarsApiRoverData("Opportunity");
		}
		if(homeDto.getMarsSol() == null) {
			homeDto.setMarsSol(1);
		}
		MarsRoverApiResponse roverData = roverService.getRoverData(homeDto);
		model.put("roverData", roverData);
		model.put("homeDto", homeDto);
		model.put("validCameras", roverService.getValidCameras().get(homeDto.getMarsApiRoverData()));
		
		return "index";
	}
	
	@PostMapping("/")
	public String postHomeView (HomeDto homeDto) {
		System.out.println(homeDto);
		return "redirect:/";
	}
	
	}
	
