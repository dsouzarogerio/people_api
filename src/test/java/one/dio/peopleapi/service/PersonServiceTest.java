package one.dio.peopleapi.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import one.dio.peopleapi.dto.MessageResponseDTO;
import one.dio.peopleapi.dto.request.PersonDTO;
import one.dio.peopleapi.entity.Person;
import one.dio.peopleapi.repository.PersonRepository;
import one.dio.peopleapi.utils.PersonUtils;

@ExtendWith(MockitoExtension.class)
public class PersonServiceTest {

	@Mock
	private PersonRepository personRepository;

	@InjectMocks
	private PersonService personService;

	@Test
	void testGivenPersonDTOThenReturnSavedMessage() {

		PersonDTO personDTO = PersonUtils.createFakeDTO();
		Person expectedSavedPerson = PersonUtils.createFakeEntity();

		Mockito.when(personRepository.save(Mockito.any(Person.class))).thenReturn(expectedSavedPerson);

		MessageResponseDTO expectedSuccesMessage = createExpectedMessageResponse(expectedSavedPerson.getId());
		MessageResponseDTO succesMessage = personService.createPerson(personDTO);

		Assertions.assertEquals(expectedSuccesMessage, succesMessage);
	}

	private MessageResponseDTO createExpectedMessageResponse(Long id) {
		return MessageResponseDTO
				.builder()
				.message("Created person with Id " + id)
				.build();
	}

}
