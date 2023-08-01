package com.restful.booker.crudtest;

import com.restful.booker.model.BookingDates;
import com.restful.booker.model.RestPojo;
import com.restful.booker.testbase.TestBase;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class CrudTest extends TestBase {
    static String token = "a13881580e3518a";

    @Test
    public void verifyCreateBooking() {
        RestPojo restPojo = new RestPojo();
        restPojo.setFirstname("manya");
        restPojo.setLastname("patel");
        restPojo.setTotalPrice("2000");
        restPojo.setDepositPaid(true);
        BookingDates bookingD = new BookingDates();
        bookingD.setCheckIn("12-12-2023");
        bookingD.setCheckOut("12-15-2023");
        restPojo.setBookingDates(bookingD);
        restPojo.setAdditionalNeeds("B & B");
        Response response = given()
                .headers("Content-Type", "application/json", "Authorization", "Bearer " + token)
                .when()
                .body(restPojo)
                .post();
        //To fetch response from server
        String responseBody = response.getBody().asString();
        response.then().log().all().statusCode(200);
        JsonPath jsonPath = new JsonPath(responseBody);
        String bookingId = jsonPath.getString("bookingid");
        System.out.println("bookingid " + bookingId);
    }

    @Test
    public void getAllBookingId() {
        Response response = given()
                .basePath("/booking")
                .header("Content-Type", "application/json")
                .when()
                .get();
        response.then().statusCode(200);
        response.prettyPrint();
    }

    @Test
    public void getBooking() {
        Response response = given()
                .basePath("/booking/613")
                .header("Content-Type", "application/json")
                .when()
                .get();
        response.then().statusCode(200);
        response.prettyPrint();
    }

    @Test
    public void getBookingIdsWithFirstName() {
        Response response = given()
                .basePath("/booking?firstname=John")
                .header("Content-Type", "application/json")
                .when()
                .get();
        response.then().statusCode(200);
        response.prettyPrint();
    }

    @Test
    public void updateBooking() {
        RestPojo restPojo = new RestPojo();
        restPojo.setFirstname("manya");
        restPojo.setLastname("patel");
        restPojo.setTotalPrice("4000");
        restPojo.setDepositPaid(true);
        BookingDates bd = new BookingDates();
        bd.setCheckIn("12-23-2023");
        bd.setCheckOut("12-25-2023");
        restPojo.setBookingDates(bd);
        Response response = given()
                .headers("Content-Type", "application/json", "Authorization", "Bearer " + token)
                .basePath("/booking/2625")
                .when()
                .body(restPojo)
                .put();

        response.then().log().all().statusCode(200);
        response.prettyPrint();
    }

    @Test
    public void partialUpdateBooking() {
        RestPojo restPojo = new RestPojo();
        restPojo.setFirstname("janu");
        restPojo.setLastname("joshi");
        Response response = given()
                .basePath(RestAssured.basePath + "/94")
                .headers("Content-Type", "application/json", "Authorization", "Bearer " + "YWRtaW46cGFzc3dvcmQxMjM=")
                .when()
                .body(restPojo)
                .patch();

        response.then().log().all().statusCode(200);
        String responseBody = response.getBody().asString();
        JsonPath jsonPath = new JsonPath(responseBody);

    }

    @Test
    public void VerifyUserDeleteBookingSuccessfully() {
        //   PostsPojo postsPojo = new PostsPojo();
        Response response = given()
                .headers("Content-Type", "application/json", "Authorization", "Basic " + "YWRtaW46cGFzc3dvcmQxMjM=")
                .basePath(RestAssured.basePath + "/94")
                .when()
                .body("")
                .delete();

        response.prettyPrint();
        response.then().statusCode(201);
    }
}










