package one.dio.peopleapi.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import one.dio.peopleapi.dto.request.PersonDTO;
import one.dio.peopleapi.dto.response.MessageResponseDTO;
import one.dio.peopleapi.entity.Person;
import one.dio.peopleapi.repository.PersonRepository;
import one.dio.peopleapi.utils.PersonUtils;

import static one.dio.peopleapi.utils.PersonUtils.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PersonServiceTest {

	@Mock
	private PersonRepository personRepository;

	@InjectMocks
	private PersonService personService;

	@Test
	void testGivenPersonDTOThenReturnSavedMessage() {
		PersonDTO personDTO = createFakeDTO();
		Person expectedSavedPerson = createFakeEntity();

		when(personRepository.save(any(Person.class))).thenReturn(expectedSavedPerson);

		MessageResponseDTO expectedSuccesMessage = createExpectedMessageResponse(expectedSavedPerson.getId());
		MessageResponseDTO succesMessage = personService.createPerson(personDTO);

		assertEquals(expectedSuccesMessage, succesMessage);
	}

	private MessageResponseDTO createExpectedMessageResponse(Long id) {
		return MessageResponseDTO
				.builder()
				.message("Created person with Id " + id)
				.build();
	}

}
