package org.springframework.cloud.contract.wiremock;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = WiremockTestsApplication.class, properties = "app.baseUrl=http://localhost:${wiremock.server.port}", webEnvironment = WebEnvironment.NONE)
@AutoConfigureWireMock(port = 9999)
// Should manage to register
public class AutoConfigureWireMockSamePortApplicationTests {

	@Autowired
	private Service service;

	@Test
	public void contextLoads() throws Exception {
		stubFor(get(urlEqualTo("/test2")).willReturn(aResponse()
				.withHeader("Content-Type", "text/plain").withBody("Hello World2!")));
		assertThat(this.service.go2()).isEqualTo("Hello World2!");
	}

}
