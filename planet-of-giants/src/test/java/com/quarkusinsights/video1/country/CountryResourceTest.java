//package com.quarkusinsights.video1.country;
//
//import io.quarkus.test.Mock;
//import io.quarkus.test.junit.QuarkusTest;
//import io.quarkus.test.junit.mockito.InjectMock;
//import org.eclipse.microprofile.rest.client.inject.RestClient;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mockito;
//
//
//import java.util.Collections;
//
//import static io.restassured.RestAssured.given;
//import static org.hamcrest.CoreMatchers.is;
//
//@QuarkusTest
//class CountryResourceTest {
//
//    @InjectMock
//    @RestClient
//    CountryService countryService;
//
//    @BeforeEach
//    void setUP(){
//        Mockito.when(countryService.getByName("greece"))
//                .thenReturn(Collections.singletonList(new Country("Greece","Athens")));
//    }
//
//    @Test //this need on service add the @ApplicationScoped
//    public void testMockitoGreece(){
//        given().port(8080)
//                .when().get("/country/name/greece")
//                .then()
//                .statusCode(200)
//                .body("$.size()",is(1),
//                        "[0].name",is("Greece"),
//                        "[0].capital",is("Athens"));
//    }
//
//    @Test //this need on service add the @ApplicationScoped
//    public void testMockitoDailNotExist(){
//        given().port(8080)
//                .when().get("/country/name/Italia")
//                .then()
//                .statusCode(200)
//                .body("$.size()",is(1));
//    }
//
//
//    @Test //this thes need run mvn compile quarkus:dev and delete the other test with mock
//    public void testGreece(){
//        given().port(8080)
//                .when().get("/country/name/greece")
//                .then()
//                .statusCode(200)
//                .body("$.size()",is(1),
//                        "[0].name",is("Greece"),
//                        "[0].capital",is("Athens"));
//    }
//}