package com.eshop.watches.catalog;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import com.eshop.watches.catalog.entity.Brand;
import com.eshop.watches.catalog.entity.Watch;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.web.client.RestTemplate;

import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.AFTER_TEST_METHOD;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Sql(scripts = "/sql/integration-test-data.sql")
@Sql(scripts = "/sql/integration-test-cleanup-data.sql", executionPhase = AFTER_TEST_METHOD)
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

    List<Watch> watches = restTemplate.getForObject(baseUrl + "/watches", List.class);
    assertAll(
        () -> assertNotNull(watches),
        () -> assertEquals(4, watches.size()));
  }

  @Test
  public void getBrandsTest() {

    List<Brand> brands = restTemplate.getForObject(baseUrl + "/brands", List.class);
    assertAll(
        () -> assertNotNull(brands),
        () -> assertEquals(2, brands.size()));
  }

  // https://www.vincenzoracca.com/en/blog/framework/spring/integration-test/
}
