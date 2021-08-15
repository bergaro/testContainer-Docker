package ru.netology.springBootApp;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.testcontainers.containers.GenericContainer;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class SpringBootAppApplicationTests {
	@Autowired
	private TestRestTemplate testRestTemplate;
	private static final int APP_PORT = 24001;
	private static final GenericContainer<?> devApp = new GenericContainer<>("devapp")
			.withExposedPorts(APP_PORT);
	private static final GenericContainer<?> prodApp = new GenericContainer<>("prodapp")
			.withExposedPorts(APP_PORT);

	@BeforeAll
	public static void startContainers() {
		devApp.start();
		prodApp.start();
	}

	@Test
	public void devEnvironmentTest() {
		String actualResult;
		String expectedResult = "Current profile is dev";
		ResponseEntity<String> devEntity = testRestTemplate.getForEntity("http://localhost:" +
				devApp.getMappedPort(APP_PORT) + "/profile", String.class);
		actualResult = devEntity.getBody();
		Assertions.assertEquals(expectedResult, actualResult);
	}

	@Test
	public void prodEnvironmentTest() {
		String actualResult;
		String expectedResult = "Current profile is production";
		ResponseEntity<String> prodEntity = testRestTemplate.getForEntity("http://localhost:" +
                prodApp.getMappedPort(APP_PORT) + "/profile", String.class);
		actualResult = prodEntity.getBody();
		Assertions.assertEquals(expectedResult, actualResult);
	}


}
