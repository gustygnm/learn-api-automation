package tests;

import io.qameta.allure.Description;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import requests.APIRequests;

public class NegativeTests {

    @Test
    @Description("Test to verify that getting a non-existing user returns 404")
    public void testGetNonExistingUser() {
        int nonExistingUserId = -1;
        Response response = APIRequests.getUserById(nonExistingUserId);
        response.then()
                .log().all()  // log the response to the console
                .assertThat().statusCode(404)  // assert that the status code is 404 (Not Found)
                .extract().response();
    }
}
