package one.dio.peopleapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import one.dio.peopleapi.dto.MessageResponseDTO;
import one.dio.peopleapi.entity.Person;
import one.dio.peopleapi.repository.PersonRepository;

@Service
public class PersonService {

	private PersonRepository personRepository;

	@Autowired
	public PersonService(PersonRepository personRepository) {
		this.personRepository = personRepository;
	}

	public MessageResponseDTO createPerson(Person person) {
		Person savedPerson = personRepository.save(person);
		return MessageResponseDTO
				.builder()
				.message("Created Person with ID " + savedPerson.getId())
				.build();
	}
}