package com.pessoal.bora.api.controllers;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static io.restassured.RestAssured.basic;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PassengerControllerTestIT {
	@LocalServerPort
	private int port;
	
	@BeforeEach	
	public void setup() {
		RestAssured.baseURI = "https://localhost:" + port;
		RestAssured.useRelaxedHTTPSValidation();
		RestAssured.authentication = basic("admin", "password");
		RestAssured.defaultParser = Parser.JSON;
	}
	
	@Test
	public void testCreatePassenger() {
		String createPassengerJSON = "{\"name\":\"Sandro dos Santos\"}";
		given()
			.contentType(ContentType.JSON)
			.body(createPassengerJSON)
			.post("/passengers")
			.then()
			.statusCode(200)
			.body("id", notNullValue())
			.body("name", equalTo("Sandro dos Santos"));
	}

}
