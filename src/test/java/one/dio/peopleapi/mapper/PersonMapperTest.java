package one.dio.peopleapi.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import one.dio.peopleapi.mapper.PersonMapper;
import one.dio.peopleapi.dto.request.PersonDTO;
import one.dio.peopleapi.dto.request.PhoneDTO;
import one.dio.peopleapi.entity.Person;
import one.dio.peopleapi.entity.Phone;
import one.dio.peopleapi.utils.PersonUtils;

@SpringBootTest
public class PersonMapperTest {

	@Autowired

	private PersonMapper personMapper;
	
	@Test
	void testGivenPersonDTOThenReturnPersonEntity() {
		PersonDTO personDTO = PersonUtils.createFakeDTO();
		Person person = personMapper.toModel(personDTO);
		
		assertEquals(personDTO.getFirstName(), person.getFirstName());
		assertEquals(personDTO.getLastName(), person.getLastName());
		assertEquals(personDTO.getCpf(), person.getCpf());
		
		Phone phone = person.getPhones().get(0);
		PhoneDTO phoneDTO = personDTO.getPhones().get(0);
		
		assertEquals(phoneDTO.getType(), phone.getType());
		assertEquals(phoneDTO.getNumber(), phone.getNumber());
			
	}
	
	@Test
	void testGivenPersonEntityThenReturnPersonDTO() {
		Person person = PersonUtils.createFakeEntity();
		PersonDTO personDTO = personMapper.toDTO(person);
		
		assertEquals(person.getFirstName(), personDTO.getFirstName());
		assertEquals(person.getLastName(), personDTO.getLastName());
		assertEquals(person.getCpf(), personDTO.getCpf());
		
		Phone phone = person.getPhones().get(0);
		PhoneDTO phoneDTO = personDTO.getPhones().get(0);
		
		assertEquals(phone.getType(), phoneDTO.getType());
		assertEquals(phone.getNumber(), phoneDTO.getNumber());
			
	}
}
