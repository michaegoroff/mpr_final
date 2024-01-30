package com.pjatk.MPRSpring1;

import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.when;
import static io.restassured.RestAssured.with;
import static org.hamcrest.Matchers.hasItems;

public class RestAssuredTests {

    private static String baseUrl = "http://localhost:8080";
    @Test
    public void testIfRecordsArePresent(){
        when().get("/cats").then()
                .statusCode(200)
                .body("name",hasItems("Mietek","Slawek"));
    }

    @Test
    public void testAddingRecord(){
        with().body(new Cat("Dodatek",33,"Red"))
                .contentType("application/json")
                .when()
                .request("POST","/cats/add")
                .then()
                .statusCode(200);
    }

    @Test
    public void testUpdatingRecord(){
        with().body(new Cat("Updatek",2,"White"))
                .contentType("application/json")
                .when()
                .request("PUT","/cats/put/2")
                .then()
                .statusCode(200);
    }

    @Test
    public void testDeletingRecord(){
        when().delete("/cats/delete/1").then()
                .statusCode(200);
    }

    @Test
    public void testFiltering(){
        when().get("/cats/filter/awek").then()
                .statusCode(200)
                .body("name",hasItems("Slawek"));
    }


}
