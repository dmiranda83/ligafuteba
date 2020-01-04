package br.com.futeba.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;

public final class HeaderUtil {

	private static final Logger log = LoggerFactory.getLogger(HeaderUtil.class);

	private HeaderUtil() {
	}

	public static HttpHeaders createAlert(String message, String param) {
		HttpHeaders headers = new HttpHeaders();
		headers.add("X-sentryApp-alert", message);
		headers.add("X-sentryApp-params", param);
		return headers;
	}

	public static HttpHeaders createEntityCreationAlert(String entityName,
			String param) {
		return createAlert(
				"A new " + entityName + " is created with identifier " + param,
				param);
	}

	public static HttpHeaders createEntityUpdateAlert(String entityName,
			String param) {
		return createAlert(
				"A " + entityName + " is updated with identifier " + param,
				param);
	}

	public static HttpHeaders createEntityDeletionAlert(String entityName,
			String param) {
		return createAlert(
				"A " + entityName + " is deleted with identifier " + param,
				param);
	}

	public static HttpHeaders createFailureAlert(String entityName,
			String errorKey, String defaultMessage) {
		log.error("Entity creation failed, {}", defaultMessage);
		HttpHeaders headers = new HttpHeaders();
		headers.add("X-sentryApp-error", defaultMessage);
		headers.add("X-sentryApp-params", entityName);
		return headers;
	}

	public static HttpHeaders createErrorAlert(String message) {
		HttpHeaders headers = new HttpHeaders();
		headers.add("X-sentryApp-error", message);
		return headers;
	}

	public static HttpHeaders createErrorAlert(String message, String param) {
		HttpHeaders headers = new HttpHeaders();
		headers.add("X-sentryApp-error", message);
		headers.add("X-sentryApp-params", param);
		return headers;
	}
}
