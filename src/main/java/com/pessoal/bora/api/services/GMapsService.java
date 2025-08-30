package com.pessoal.bora.api.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.jayway.jsonpath.JsonPath;

import net.minidev.json.JSONArray;

@Service
public class GMapsService {
	
	private static final String GMAPS_TEMPLATE = "https://maps.googleapis.com/maps/api/directions/json?origin={origin}&destination={destination}&key={key}";
	
	@Value("${googlemaps.apikey}")
	private String appKey;
	
	
	public Integer getDistanceBetweenAddresses(String addressOne, String addressTwo) {
		
		RestTemplate template = new RestTemplate();
		String jsonResult = template.getForObject(GMAPS_TEMPLATE, String.class, addressOne, addressTwo, appKey);
		
		JsonPath.parse(jsonResult);
		
		JSONArray rawResults = JsonPath.parse(jsonResult).read("$..legs[*].duration.value");
		
		List<Integer> results = rawResults.stream().map(it -> ((Integer)it)).collect(Collectors.toList());
		
		return results.stream().min(Integer::compareTo).orElse(Integer.MAX_VALUE);
		
	}
	
	

}
