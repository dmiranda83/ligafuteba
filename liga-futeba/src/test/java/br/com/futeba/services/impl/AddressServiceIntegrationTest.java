package br.com.futeba.services.impl;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.getRequestedFor;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlPathEqualTo;
import static com.github.tomakehurst.wiremock.client.WireMock.verify;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import com.github.tomakehurst.wiremock.client.ResponseDefinitionBuilder;
import com.github.tomakehurst.wiremock.junit.WireMockRule;

import br.com.futeba.bean.ZipCodeResponse;
import br.com.futeba.config.CEPConfig;
import br.com.futeba.services.impl.AddressServiceImpl;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AddressServiceIntegrationTest {
	private AddressServiceImpl addressService;

	@Autowired
	private CEPConfig cepConfig;

	@Rule
	public WireMockRule wireMockRule = new WireMockRule(8082);

	@Before
	public void setUp() throws Exception {
		RestTemplate restTemplate = new RestTemplateBuilder().build();
		addressService = new AddressServiceImpl(restTemplate, cepConfig);
	}

	@Test
	public void listAddressByZipCodeWithSuccess() {

		ZipCodeResponse mockResponse = new ZipCodeResponse();
		mockResponse.setAddress("Rua Amélia Hummel Beruezzo");
		mockResponse.setCity("Caieiras");
		mockResponse.setComplement("(Cond R Park)");
		mockResponse.setNeighborhood("Serpa");
		mockResponse.setZipCode("07713-630");
		mockResponse.setState("SP");

		stubFor(get(urlPathEqualTo("/ws/07713630/json"))
				.willReturn(ResponseDefinitionBuilder.okForJson(mockResponse)));

		ZipCodeResponse zipCodeResponse = addressService
				.getAddressByZipCode("07713630");

		Assert.assertNotNull(zipCodeResponse);
		Assert.assertFalse(zipCodeResponse.isObjectEmpty());
	}

	@Test
	public void listAddressByZipCodeWithFail() {

		stubFor(get(urlPathEqualTo("/ws/07010100/json")).willReturn(
				aResponse().withStatus(HttpStatus.BAD_GATEWAY.value())));

		ZipCodeResponse zipCodeResponse = addressService
				.getAddressByZipCode("07010100");

		verify(1, getRequestedFor(urlPathEqualTo("/ws/07010100/json")));

		Assert.assertNotNull(zipCodeResponse);
	}
}
