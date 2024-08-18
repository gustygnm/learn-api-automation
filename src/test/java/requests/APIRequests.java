package requests;

import io.restassured.response.Response;
import org.json.JSONObject;

import static io.restassured.RestAssured.given;
import static org.example.utils.Config.BASE_URL;

public class APIRequests {

    public static Response getUsers() {
        return given()
                .when()
                .baseUri(BASE_URL)
                .get("api/users?page=1");
    }

    public static Response getUserById(int userId) {
        return given()
                .when()
                .baseUri(BASE_URL)
                .get("api/users/" + userId);
    }

    public static Response createUser(String name, String job) {
        // Create JSON object for the request body
        JSONObject requestBody = new JSONObject();
        if (!(name.isEmpty() && job.isEmpty())) {
            requestBody.put("name", name);
            requestBody.put("job", job);
        }

        return given()
                .header("Content-Type", "application/json")
                .body(requestBody.toString())
                .when()
                .baseUri(BASE_URL)
                .post("api/users");
    }

    public static Response updateUser(int userId, String name, String job) {
        // Create JSON object for the request body
        JSONObject requestBody = new JSONObject();
        requestBody.put("name", name);
        requestBody.put("job", job);

        return given()
                .header("Content-Type", "application/json")
                .body(requestBody.toString())
                .when()
                .baseUri(BASE_URL)
                .put("api/users/" + userId);
    }

    public static Response deleteUser(int userId) {
        return given()
                .when()
                .baseUri(BASE_URL)
                .delete("api/users/" + userId);
    }
}
