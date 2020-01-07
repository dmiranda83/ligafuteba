package br.com.futeba.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

@Configuration
@PropertySource("classpath:application-integrationtest.properties")
public class CEPConfig {

	@Autowired
	private Environment env;

	private String endpoint;

	public String getEndpoint() {
		endpoint = env.getProperty("service.cep.endpoint");
		return endpoint;
	}

	public void setEndpoint(String endpoint) {
		this.endpoint = endpoint;
	}
}