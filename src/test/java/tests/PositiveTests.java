package tests;

import io.qameta.allure.Description;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;
import requests.APIRequests;

import java.io.File;

public class PositiveTests {

    @Test
    @Description("Test to verify that the user list is fetched correctly")
    public void testGetUsers() {
        Response response = APIRequests.getUsers();
        response.then()
                .log().all()  // log the response to the console
                .assertThat().statusCode(200)  // assert that the status code is 200 (OK)
                .assertThat().body("page", Matchers.equalTo(1))  // assert that we access the correct page
                .assertThat().body("data.id", Matchers.hasSize(6))  // assert that the data has 6 entries
                .extract().response();
    }

    @Test
    @Description("Test to verify that user details are fetched correctly")
    public void testGetUserById() {
        int userId = 1;
        Response response = APIRequests.getUserById(userId);
        response.then()
                .log().all()  // log the response to the console
                .assertThat().statusCode(200)  // assert that the status / assert that we access the correct page
                .assertThat().body("data.id", Matchers.equalTo(userId))  // assert that the data has 6 entries
                .extract().response();
    }

    @Test
    @Description("Test to verify that user details use JsonSchema are fetched correctly")
    public void testValidateJsonSchemaGetSingleUser() {
        int userId = 5;
        File file = new File("src/test/resources/jsonSchema/GetSingleUserSchema.json");

        Response response = APIRequests.getUserById(userId);
        response.then()
                .log().all()  // log the response to the console
                .assertThat().statusCode(200)  // assert that the status / assert that we access the correct page
                .assertThat().body(JsonSchemaValidator.matchesJsonSchema(file))  // assert that the data has 6 entries
                .extract().response();
    }

    @Test
    @Description("Test to verify that a new user is created successfully")
    public void testCreateUser() {
        String name = "John Doe";
        String job = "Software QA Engineer";
        Response response = APIRequests.createUser(name, job);
        response.then()
                .log().all()  // log the response to the console (optional)
                .assertThat().statusCode(201)  // assert that the status code is 201 (Created)
                .assertThat().body("name", Matchers.equalTo(name))  // assert the name in the response
                .assertThat().body("job", Matchers.equalTo(job))  // assert the job in the response
                .extract().response();
    }

    @Test
    @Description("Test to verify that a update user is successfully")
    public void testUpdateUser() {
        int userId = 2;
        String name = "Jane Doe";
        String job = "Product Manager";
        Response response = APIRequests.updateUser(userId, name, job);
        response.then()
                .log().all()  // log the response to the console (optional)
                .assertThat().statusCode(200)  // assert that the status code is 200 (OK)
                .assertThat().body("name", Matchers.equalTo(name))  // assert the updated name
                .assertThat().body("job", Matchers.equalTo(job))  // assert the updated job
                .extract().response();
    }

    @Test
    @Description("Test to verify that a update user is successfully")
    public void testDeleteUser() {
        int userId = 479;
        Response response = APIRequests.deleteUser(userId);
        response.then()
                .log().all()  // log the response to the console (optional)
                .assertThat().statusCode(204)  // assert that the status code is 204 (No Content)
                .extract().response();
    }
}
