package com.restassured.api.tests;

import java.util.ArrayList;

import org.testng.annotations.Test;

import com.restassured.api.extensions.RestAssuredExtensions;
import com.selenium.ui.testbase.TestBase;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class SampleApiIT extends TestBase {

	@Test
	public void sampleApi() throws Exception {
		RestAssuredExtensions.setEndPoint("http://localhost:3000/posts");
		RestAssuredExtensions.getRequestSpecification();
		RestAssuredExtensions.setContentType(ContentType.JSON);
		ArrayList<Integer> resp = RestAssuredExtensions.getResponse().getBody().jsonPath().get("id");
		resp.forEach(s -> System.out.println(s));
	}

	@Test
	public void sampleApi2() throws Exception {
		RestAssuredExtensions.setEndPoint("http://localhost:3000/posts");
		RestAssuredExtensions.getRequestSpecification();
		RestAssuredExtensions.setContentType(ContentType.JSON);
		ArrayList<String> resp = RestAssuredExtensions.getResponse().getBody().jsonPath().get("title");
		resp.forEach(s -> System.out.println(s));
	}

	@Test
	public void sampleApi3() throws Exception {
		RestAssuredExtensions.setEndPoint("http://localhost:3000/posts");
		RestAssuredExtensions.getRequestSpecification();
		RestAssuredExtensions.setContentType(ContentType.JSON);
		ArrayList<String> resp = RestAssuredExtensions.getResponse().getBody().jsonPath().get("author");
		resp.forEach(s -> System.out.println(s));
	}

}
