package com.eshop.watches.order;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.hasItems;

import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;

import com.eshop.watches.order.utils.ShippingState;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.Order;

import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class OrderResourceTest {

  @Test
  @Order(1)
  public void test_getWatchOrders_OK() {
    given()
        .header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON)
        .when()
        .get("/orders/")
        .then()
        .statusCode(200)
        .body("size()", equalTo(2))
        .body("id", hasItems(1, 2));
  }

  @Test
  @Order(2)
  public void test_createOrder() {
    JsonObject jsonObject = Json.createObjectBuilder()
        .add("detail", "Carlos Lopez - Omega Seamaster (5000 €) - 07/10/2022")
        .build();
    given()
        .header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON)
        .contentType(MediaType.APPLICATION_JSON)
        .body(jsonObject.toString())
        .when()
        .post("/orders/")
        .then()
        .statusCode(201);
  }

  @Test
  @Order(2)
  public void test_updateStateOrder_1() {

    given()
        .header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON)
        .contentType(MediaType.APPLICATION_JSON)
        .when()
        .put("/orders/next/1")
        .then()
        .statusCode(200)
        .body("shippingStatus", equalTo(ShippingState.Created.getStateText()));
  }

  @Test
  @Order(3)
  public void test_updateStateOrder_2() {

    given()
        .header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON)
        .contentType(MediaType.APPLICATION_JSON)
        .when()
        .put("/orders/next/2")
        .then()
        .statusCode(200)
        .body("shippingStatus", equalTo(ShippingState.Delivered.getStateText()));
  }

}
