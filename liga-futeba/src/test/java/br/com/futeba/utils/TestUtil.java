package br.com.futeba.utils;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.nio.charset.Charset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeParseException;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeDiagnosingMatcher;
import org.springframework.http.MediaType;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class TestUtil {

	public static final String PLACE_TYPE = "Ginasio Poliesportivo";
	public static final String PLACE_MARCELINO = "Quadra Marcelino";
	public static final String PLACE_NOVA_ERA = "Quadra Nova Era";
	public static final String PLACE_LARANJEIRAS = "Quadra Laranjeiras";
	public static final String CATEGORY_FUTSAL = "Futsal";
	public static final String CATEGORY_SOCCER = "Soccer";
	public static final String CATEGORY_BASKETBALL = "Basketball";
	public static final String NONEXISTENT_CATEGORY_NAME = "nonexistent";
	public static final String TEAM_SAN_REMO = "San Remo";
	public static final String TEAM_VENEZA = "Veneza";
	public static final String TEAM_RACA = "Raça";
	public static final String TEAM_ORIGINAIS = "Originais";
	public static final String TEAM_RESPONSABLE = "Roberto";
	public static final String TEAM_RESPONSABLE_PHONE_NUMBER = "11997826657";
	public static final String PLACE_ZIP_CODE = "07713630";
	public static final String PLAYER_DIEGO = "Diego Miranda";
	public static final String PLAYER_DAVI = "Davi Miranda";
	public static final String PLAYER_ELIAS = "Elias Miranda";
	public static final String POSITION_ALA_ESQUERDA = "Ala Esquerda";
	public static final String POSITION_ALA_DIREITA = "Ala Direita";
	public static final String POSITION_FIXO = "Fixo";

	public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(
			MediaType.APPLICATION_JSON.getType(),
			MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

	public static byte[] convertObjectToJsonBytes(Object object)
			throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

		JavaTimeModule module = new JavaTimeModule();
		mapper.registerModule(module);

		return mapper.writeValueAsBytes(object);
	}

	public static byte[] createByteArray(int size, String data) {
		byte[] byteArray = new byte[size];
		for (int i = 0; i < size; i++) {
			byteArray[i] = Byte.parseByte(data, 2);
		}
		return byteArray;
	}

	public static class ZonedDateTimeMatcher
			extends
				TypeSafeDiagnosingMatcher<String> {

		private final ZonedDateTime date;

		public ZonedDateTimeMatcher(ZonedDateTime date) {
			this.date = date;
		}

		@Override
		protected boolean matchesSafely(String item,
				Description mismatchDescription) {
			try {
				if (!date.isEqual(ZonedDateTime.parse(item))) {
					mismatchDescription.appendText("was ").appendValue(item);
					return false;
				}
				return true;
			} catch (DateTimeParseException e) {
				mismatchDescription.appendText("was ").appendValue(item)
						.appendText(
								", which could not be parsed as a ZonedDateTime");
				return false;
			}

		}

		@Override
		public void describeTo(Description description) {
			description.appendText("a String representing the same Instant as ")
					.appendValue(date);
		}
	}

	public static ZonedDateTimeMatcher sameInstant(ZonedDateTime date) {
		return new ZonedDateTimeMatcher(date);
	}

	@SuppressWarnings("unchecked")
	public static void equalsVerifier(Class clazz) throws Exception {
		Object domainObject1 = clazz.getConstructor().newInstance();
		assertThat(domainObject1.toString()).isNotNull();
		assertThat(domainObject1).isEqualTo(domainObject1);
		assertThat(domainObject1.hashCode())
				.isEqualTo(domainObject1.hashCode());
		Object testOtherObject = new Object();
		assertThat(domainObject1).isNotEqualTo(testOtherObject);
		assertThat(domainObject1).isNotEqualTo(null);
		Object domainObject2 = clazz.getConstructor().newInstance();
		assertThat(domainObject1).isNotEqualTo(domainObject2);
		assertThat(domainObject1.hashCode())
				.isEqualTo(domainObject2.hashCode());
	}
}
