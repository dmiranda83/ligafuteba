package br.com.futeba.services.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.com.futeba.bean.ZipCodeResponse;
import br.com.futeba.config.CEPConfig;

@Service
public class AddressServiceImpl {

	private static final Logger logger = LoggerFactory
			.getLogger(AddressServiceImpl.class);

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private CEPConfig cepConfig;

	public AddressServiceImpl() {
	}

	public AddressServiceImpl(RestTemplate restTemplate, CEPConfig cepConfig) {
		this.restTemplate = restTemplate;
		this.cepConfig = cepConfig;
	}

	public ZipCodeResponse getAddressByZipCode(String zipCode) {
		logger.info(String.format("Buscando CEP [%s]...", zipCode));

		try {
			ResponseEntity<ZipCodeResponse> forEntity = restTemplate
					.getForEntity(String.format("%s/%s/json",
							cepConfig.getEndpoint(), zipCode),
							ZipCodeResponse.class);

			return forEntity.getBody();
		} catch (Exception ex) {
			logger.debug("Erro ao buscar o CEP", ex);
			return new ZipCodeResponse();
		}
	}
}
