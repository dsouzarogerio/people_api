package one.dio.peopleapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import one.dio.peopleapi.dto.MessageResponseDTO;
import one.dio.peopleapi.dto.request.PersonDTO;
import one.dio.peopleapi.entity.Person;
import one.dio.peopleapi.mapper.PersonMapper;
import one.dio.peopleapi.repository.PersonRepository;

@Service
public class PersonService {

	private PersonRepository personRepository;
	
	private final PersonMapper personMapper = PersonMapper.INSTANCE;

	@Autowired
	public PersonService(PersonRepository personRepository) {
		this.personRepository = personRepository;
	}

	public MessageResponseDTO createPerson(PersonDTO personDTO) {
		Person personToSave = personMapper.toModel(personDTO);
		
		Person savedPerson = personRepository.save(personToSave);
		return MessageResponseDTO
				.builder()
				.message("Created Person with ID " + savedPerson.getId())
				.build();
	}
}