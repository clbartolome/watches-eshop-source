package com.eshop.watches.catalog;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.AFTER_TEST_METHOD;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.BEFORE_TEST_METHOD;

import java.util.List;

import com.eshop.watches.catalog.entity.Watch;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.web.client.RestTemplate;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Sql(scripts = "/sql/integration-test-data.sql")
@Sql(scripts = "/sql/integration-test-cleanup-data.sql", executionPhase = AFTER_TEST_METHOD)
@ActiveProfiles("test")
public class CatalogIntegrationTests {

  @LocalServerPort
  private int port;

  private String baseUrl = "http://localhost";

  private static RestTemplate restTemplate = null;

  @BeforeAll
  public static void init() {
    restTemplate = new RestTemplate();
  }

  @BeforeEach
  public void setUp() {
    baseUrl = baseUrl.concat(":").concat(port + "");
  }

  @Test
  public void getWatchesTest() {

    ResponseEntity<List> response = restTemplate.getForEntity(baseUrl + "/watches", List.class);
    assertAll(
        () -> assertNotNull(response.getBody()),
        () -> assertEquals(4, response.getBody().size()),
        () -> assertEquals(HttpStatus.OK, response.getStatusCode()));
  }

  @Test
  @Sql(scripts = "/sql/integration-test-cleanup-data.sql", executionPhase = BEFORE_TEST_METHOD)
  public void getWatchesTest_noContent() {

    ResponseEntity<List> response = restTemplate.getForEntity(baseUrl + "/watches", List.class);
    assertAll(
        () -> assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode()));
  }

  @Test
  public void getWatchesByBrandTest() {

    ResponseEntity<List> response = restTemplate.getForEntity(baseUrl + "/watches/brands/1", List.class);
    assertAll(
        () -> assertNotNull(response.getBody()),
        () -> assertEquals(3, response.getBody().size()),
        () -> assertEquals(HttpStatus.OK, response.getStatusCode()));
  }

  @Test
  @Sql(scripts = "/sql/integration-test-cleanup-data.sql", executionPhase = BEFORE_TEST_METHOD)
  public void getWatchesByBrandTest_noContent() {

    ResponseEntity<List> response = restTemplate.getForEntity(baseUrl + "/watches/brands/5", List.class);
    assertAll(
        () -> assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode()));
  }

  @Test
  public void getBrandsTest() {

    ResponseEntity<List> response = restTemplate.getForEntity(baseUrl + "/brands", List.class);
    assertAll(
        () -> assertNotNull(response.getBody()),
        () -> assertEquals(2, response.getBody().size()),
        () -> assertEquals(HttpStatus.OK, response.getStatusCode()));
  }

  @Test
  @Sql(scripts = "/sql/integration-test-cleanup-data.sql", executionPhase = BEFORE_TEST_METHOD)
  public void getBrandsTest_noContent() {

    ResponseEntity<List> response = restTemplate.getForEntity(baseUrl + "/brands", List.class);
    assertAll(
        () -> assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode()));
  }

  @Test
  public void getWatchTest() {

    ResponseEntity<Watch> response = restTemplate.getForEntity(baseUrl + "/watches/1", Watch.class);
    assertAll(
        () -> assertNotNull(response.getBody()),
        () -> assertEquals(HttpStatus.OK, response.getStatusCode()));
  }

  @Test
  public void getWatchTest_NotFound() {

    ResponseEntity<Watch> response = restTemplate.getForEntity(baseUrl + "/watches/99", Watch.class);
    assertAll(
        () -> assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode()));
  }
  // https://www.vincenzoracca.com/en/blog/framework/spring/integration-test/
}
