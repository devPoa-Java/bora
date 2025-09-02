package com.pessoal.bora.api.infrastructure;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import org.springframework.core.io.ClassPathResource;



public class FileUtils {
	
	public static String loadFileContents(String fileName) throws IOException {
		
		InputStream is = new ClassPathResource(fileName).getInputStream();
		byte[] data = new byte[is.available()];
		is.read(data);
		
		
		return new String(data);
	}
	
	public static String loadFileContents(String fileName, Map<String, String> replacements) throws IOException {
		String fileContents = loadFileContents(fileName);
		
		for(Map.Entry<String, String> entry : replacements.entrySet()) {
			fileContents = fileContents.replace("{{" + entry.getKey() + "}}", entry.getValue());
			
		}
		return fileContents;
	}
	
	

}
