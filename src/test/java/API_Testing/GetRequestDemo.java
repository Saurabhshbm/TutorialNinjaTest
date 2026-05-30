package API_Testing;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.containsString;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
 
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import io.restassured.RestAssured;

public class GetRequestDemo {

public static void main(String[] args) {
	
	

	// Base URI
    RestAssured.baseURI = "https://gorest.co.in/";

    System.out.println("============== GET REQUEST ==============");

    // GET Request
    Response getResponse = given()
                .when()
                .get("/public/v2/users/8052292");

    // Print Response
    System.out.println("Status Code : "
            + getResponse.getStatusCode());

    System.out.println("Response Body : ");
    System.out.println(getResponse.getBody().asString());

    System.out.println("Response Time : "
            + getResponse.getTime());

    System.out.println("Content Type : "
            + getResponse.getContentType());



   System.out.println("\n============== VALIDATIONS ==============");

    // Validations
   given()

   .when()
       .get("/public/v2/users/8052292")

   .then()
       .statusCode(200)
       .body("id", equalTo(8052292))
       .body("name", equalTo("Brajendra Khatri"))
       .body("email", equalTo("khatri_brajendra@baumbach.test"))
       .body("gender", equalTo("male"))
       .body("status", equalTo("active"))
       .time(lessThan(5000L))
       .header("Content-Type", containsString("application/json"))
       .log().all();



    System.out.println("\n============== JSON EXTRACTION ==============");

    // JSON Extraction
    JsonPath jsonPath =
            getResponse.jsonPath();

    int id =
            jsonPath.getInt("id");
    
    String name =
            jsonPath.getString("name");

    String email =
            jsonPath.getString("email");

    String gender =
            jsonPath.getString("gender");
    String status =
            jsonPath.getString("status");

    System.out.println("ID : " + id);
    System.out.println("Name : " + name);

    System.out.println("Email : " + email);

    System.out.println("Gender : " + gender);

    System.out.println("Status : " + status);
}
}