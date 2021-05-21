package one.dio.peopleapi.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import one.dio.peopleapi.dto.request.PersonDTO;
import one.dio.peopleapi.dto.response.MessageResponseDTO;
import one.dio.peopleapi.entity.Person;
import one.dio.peopleapi.exception.PersonNotFoundException;
import one.dio.peopleapi.mapper.PersonMapper;
import one.dio.peopleapi.repository.PersonRepository;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class PersonService {

	private PersonRepository personRepository;

	private final PersonMapper personMapper = PersonMapper.INSTANCE;

	//---------// CREATE PERSON //---------//
	
	public MessageResponseDTO createPerson(PersonDTO personDTO) {
		Person personToSave = personMapper.toModel(personDTO);

		Person savedPerson = personRepository.save(personToSave);
		return createMessageResponse(savedPerson.getId(), ("Created person with Id "));
	}

	//---------// GET ALL PERSON //---------//
	
	public List<PersonDTO> listAll() {
		List<Person> allPeople = personRepository.findAll();
		return allPeople.stream().map(personMapper::toDTO).collect(Collectors.toList());
	}

	//---------// GET PERSON BY ID //---------//
	
	public PersonDTO findById(Long id) throws PersonNotFoundException {
		Person person = verifyIfExists(id);

		return personMapper.toDTO(person);
	}

	//---------// DELETE PERSON BY ID //---------//
	
	public void delete(Long id) throws PersonNotFoundException {
		verifyIfExists(id);

		personRepository.deleteById(id);
	}

	//---------// UPDATE PERSON BY ID //---------//
	
	public MessageResponseDTO updateById(Long id, PersonDTO personDTO) throws PersonNotFoundException {
	
		verifyIfExists(id);
		
		Person personToUpdate = personMapper.toModel(personDTO);

		Person updatedPerson = personRepository.save(personToUpdate);
		return createMessageResponse(updatedPerson.getId(), "Updated person with ID ");
	}
	
	/*-----------------// PRIVATE METHODS //-----------------*/
	
	//---------// METHOD VERIFY IF EXISTS PERSON //---------//
	
	private Person verifyIfExists(Long id) throws PersonNotFoundException {
		return personRepository.findById(id)
				.orElseThrow(() -> new PersonNotFoundException(id));
	}

	//---------// METHOD MESSAGE RESPONSE //---------//
	private MessageResponseDTO createMessageResponse(Long id, String message) {
		return MessageResponseDTO
				.builder()
				.message(message + id)
				.build();
	}
	
}

