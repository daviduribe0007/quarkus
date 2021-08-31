//package com.quarkusinsights.video1;
//
//import com.google.inject.Inject;
//import com.quarkusinsights.video1.country.CountryService;
//import io.quarkus.test.junit.QuarkusTest;
//import org.eclipse.microprofile.rest.client.inject.RestClient;
//import org.junit.jupiter.api.Test;
//
//import static io.restassured.RestAssured.given;
//import static org.hamcrest.CoreMatchers.is;
//
//@QuarkusTest
//class FirstTestTest {
//
//    @Inject
//    @RestClient
//    CountryService countryService;
//
//    @Test
//    public void testHi() {
//        given().port(8080)
//                .when().get("/yo")
//                .then()
//                .statusCode(200)
//                .body(is("Hellow my name is david"));
//    }
//}