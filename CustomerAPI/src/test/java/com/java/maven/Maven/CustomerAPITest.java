package com.java.maven.Maven;

import org.json.simple.JSONObject;
import org.junit.Test;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import junit.framework.Assert;

public class CustomerAPITest {

	
	/**
	 * Create Customer Details & verify HTTP Status Code 401.
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void createCustomerWith401HttpStatus() {
		RestAssured.baseURI = "http://localhost:3000";
		RequestSpecification request = RestAssured.given().relaxedHTTPSValidation().auth().basic("admin1", "admin");
		request.contentType(ContentType.JSON);
		JSONObject requestParams = new JSONObject();
		requestParams.put("first_name", "SelvaKumar");
		requestParams.put("last_name", "Srinivasan");
		requestParams.put("phone", "123-456-7890");
		request.body(requestParams.toJSONString());
		Response response = request.post("/customers");
		int statusCode = response.getStatusCode();
		Assert.assertEquals(401, statusCode);
	}
	
	/**
	 * Create Customer Details & verify HTTP Status Code 201.
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void createCustomer() {
		RestAssured.baseURI = "http://localhost:3000";
		RequestSpecification request = RestAssured.given().relaxedHTTPSValidation().auth().basic("admin", "admin");
		request.contentType(ContentType.JSON);
		JSONObject requestParams = new JSONObject();
		requestParams.put("first_name", "SelvaKumar");
		requestParams.put("last_name", "Srinivasan");
		requestParams.put("phone", "123-456-7890");
		request.body(requestParams.toJSONString());
		Response response = request.post("/customers");
		int statusCode = response.getStatusCode();
		Assert.assertEquals(201, statusCode);
	}
	
	/**
	 * Create Customer Details & verify HTTP Status Code 400.
	 */
	@Test
	public void createCustomerWith400HttpStatus() {
		RestAssured.baseURI = "http://localhost:3000";
		RequestSpecification request = RestAssured.given().relaxedHTTPSValidation().auth().basic("admin", "admin");
		request.contentType(ContentType.JSON);
		request.body("{\"firstName\"}");
		Response response = request.post("/customers");
		int statusCode = response.getStatusCode();
		Assert.assertEquals(400, statusCode);
	}

	/**
	 * Get Customer Details and verify HTTP Status 200.
	 */
	@Test
	public void getCustomer() {
		RestAssured.baseURI = "http://localhost:3000";
		RequestSpecification httpRequest = RestAssured.given().relaxedHTTPSValidation().auth().basic("admin", "admin");
		Response response = httpRequest.request(Method.GET, "/customers/1");
		int statusCode = response.getStatusCode();
		Assert.assertEquals(statusCode, 200);
	}

	/**
	 * Get Customer Details and verify HTTP Status 415.
	 */
	@Test
	public void getCustomerWith415Status() {
		RestAssured.baseURI = "http://localhost:3000";
		RequestSpecification httpRequest = RestAssured.given().relaxedHTTPSValidation().auth().basic("admin", "admin");
		Response response = httpRequest.request(Method.POST, "/customers/2");
		int statusCode = response.getStatusCode();
		Assert.assertEquals(statusCode, 415);
	}

	/**
	 * Update Customer Details & verify 200 HTTP Status
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void updateCustomer() {

		RestAssured.baseURI = "http://localhost:3000";
		RequestSpecification request = RestAssured.given().relaxedHTTPSValidation().auth().basic("admin", "admin");
		request.contentType(ContentType.JSON);
		JSONObject requestParams = new JSONObject();
		requestParams.put("first_name", "SelvaKumar");
		requestParams.put("last_name", "Srinivasan");
		requestParams.put("phone", "123-456-7890");
		request.body(requestParams.toJSONString());
		Response response = request.put("/customers/2");
		int statusCode = response.getStatusCode();
		Assert.assertEquals(200, statusCode);
	}

	/**
	 * Update Customer Details & verify 500 HTTP Status
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void updateCustomerwith500HttpStatus() {

		RestAssured.baseURI = "http://localhost:3000";
		RequestSpecification request = RestAssured.given().relaxedHTTPSValidation().auth().basic("admin", "admin");
		request.contentType(ContentType.JSON);
		JSONObject requestParams = new JSONObject();
		requestParams.put("first_name", "SelvaKumar");
		requestParams.put("last_name", "Srinivasan");
		requestParams.put("phone", "123-456-7890");
		requestParams.put("id", "1");
		request.body(requestParams.toJSONString());
		Response response = request.post("/customers");
		int statusCode = response.getStatusCode();
		Assert.assertEquals(500, statusCode);
	}

	/**
	 * Delete Customer Details & verify 200 HTTP Status
	 */
	@SuppressWarnings("unchecked")
	@Test
	public void deleteCustomer() {
		RestAssured.baseURI = "http://localhost:3000";
		RequestSpecification httpRequest = RestAssured.given().relaxedHTTPSValidation().auth().basic("admin", "admin");

		JSONObject requestParams = new JSONObject();
		requestParams.put("first_name", "SelvaKumar");
		requestParams.put("last_name", "Srinivasan");
		requestParams.put("phone", "123-456-7890");
		httpRequest.body(requestParams.toJSONString());
		Response response = httpRequest.post("/customers");

		response = httpRequest.request(Method.DELETE, "/customers/" + response.getBody().jsonPath().get("id"));
		int statusCode = response.getStatusCode();
		Assert.assertEquals(statusCode, 200);
	}

	/**
	 * Delete Customer Details & verify 404 HTTP Status
	 */
	@Test
	public void deleteCustomers() {
		RestAssured.baseURI = "http://localhost:3000";
		RequestSpecification httpRequest = RestAssured.given().relaxedHTTPSValidation().auth().basic("admin", "admin");
		Response response = httpRequest.request(Method.DELETE, "/customers/A");
		int statusCode = response.getStatusCode();
		Assert.assertEquals(statusCode, 404);
	}

}
