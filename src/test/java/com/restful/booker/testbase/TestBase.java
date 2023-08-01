package com.restful.booker.testbase;

import com.restful.booker.util.TestUtils;
import io.restassured.RestAssured;
import org.junit.BeforeClass;

public class TestBase extends TestUtils {
    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI = "https://restful-booker.herokuapp.com";
       // RestAssured.port = 3030;
        RestAssured.basePath = "/booking";
    }


}
