package tests;

import io.qameta.allure.Description;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;
import requests.APIRequests;

public class BoundaryTests {

    @Test
    @Description("Test to verify that getting the first user works correctly")
    public void testGetFirstUser() {
        int firstUserId = 1;
        Response response = APIRequests.getUserById(firstUserId);
        response.then()
                .log().all()  // log the response to the console
                .assertThat().statusCode(200)  // assert that the status code is 200 (OK)
                .assertThat().body("data.id", Matchers.equalTo(firstUserId))  // assert that we get the correct user
                .extract().response();
    }

    @Test
    @Description("Test to verify that getting the last user works correctly")
    public void testGetLastUser() {
        int lastUserId = 12;  // Assuming the last user in the dataset has ID 12
        Response response = APIRequests.getUserById(lastUserId);
        response.then()
                .log().all()  // log the response to the console
                .assertThat().statusCode(200)  // assert that the status code is 200 (OK)
                .assertThat().body("data.id", Matchers.equalTo(lastUserId))  // assert that we get the correct user
                .extract().response();
    }

    @Test
    @Description("Test to verify that updating the user with minimum input works")
    public void testUpdateUserWithMinimumInput() {
        int userId = 2;
        String name = "A";  // Minimum input for name
        String job = "B";   // Minimum input for job

        Response response = APIRequests.updateUser(userId, name, job);
        response.then()
                .log().all()  // log the response to the console (optional)
                .assertThat().statusCode(200)  // assert that the status code is 200 (OK)
                .assertThat().body("name", Matchers.equalTo(name))  // assert the updated name
                .assertThat().body("job", Matchers.equalTo(job))  // assert the updated job
                .extract().response();
    }

    @Test
    @Description("Test to verify that updating the user with maximum input works")
    public void testUpdateUserWithMaximumInput() {
        int userId = 2;
        String name = "A".repeat(100);  // Maximum input for name, assuming a limit of 100 characters
        String job = "B".repeat(100);   // Maximum input for job

        Response response = APIRequests.updateUser(userId, name, job);
        response.then()
                .log().all()  // log the response to the console (optional)
                .assertThat().statusCode(200)  // assert that the status code is 200 (OK)
                .assertThat().body("name", Matchers.equalTo(name))  // assert the updated name
                .assertThat().body("job", Matchers.equalTo(job))  // assert the updated job
                .extract().response();
    }
}
