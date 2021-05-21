package one.dio.peopleapi.utils;

import java.time.LocalDate;
import java.util.Collections;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import one.dio.peopleapi.dto.request.PersonDTO;
import one.dio.peopleapi.entity.Person;

public class PersonUtils {
	private static final String FIRST_NAME = "Rogério";
	private static final String LAST_NAME = "de Souza";
	private static final String CPF_NUMBER = "009.616.586-30";
	private static final Long PERSON_ID = 1L;
	private static final LocalDate BIRTH_DATE = LocalDate.of(1977, 01, 29);

	public static PersonDTO createFakeDTO() {
		return PersonDTO.builder()
				.firstName(FIRST_NAME)
				.lastName(LAST_NAME)
				.cpf(CPF_NUMBER)
				.birthDate("29-01-1977")
				.phones(Collections.singletonList(PhoneUtils.createFakeDTO()))
				.build();
	}

	public static Person createFakeEntity() {
		return Person.builder()
				.id(PERSON_ID)
				.firstName(FIRST_NAME)
				.lastName(LAST_NAME)
				.cpf(CPF_NUMBER)
				.birthDate(BIRTH_DATE)
				.phones(Collections.singletonList(PhoneUtils.createFakeEntity()))
				.build();
	}

	public static String asJsonString(PersonDTO personDTO) {
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
			objectMapper.registerModules(new JavaTimeModule());

			return objectMapper.writeValueAsString(personDTO);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}	
