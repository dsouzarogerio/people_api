package one.dio.peopleapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import one.dio.peopleapi.entity.Person;

public interface PersonRepository extends JpaRepository<Person, Long>{

}
