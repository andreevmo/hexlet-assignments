package exercise.controller;

import exercise.model.Person;
import exercise.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;

import java.util.Map;

@RestController
@RequestMapping("/people")
public class PeopleController {

    // Автоматически заполняем значение поля
    @Autowired
    private PersonRepository personRepository;

    @GetMapping(path = "/{id}")
    public Person getPerson(@PathVariable long id) {
        return this.personRepository.findById(id);
    }

    @GetMapping(path = "")
    public Iterable<Person> getPeople() {
        return this.personRepository.findAll();
    }

    // BEGIN
    @PostMapping(path = "")
    public void savePerson(@RequestBody Map<String, Object> body) {
        Person person = new Person();
        person.setFirstName(body.get("firstName").toString());
        person.setLastName(body.get("lastName").toString());
        personRepository.save(person);
    }

    @DeleteMapping(path = "/{id}")
    public void removePerson(@PathVariable long id) {
        Person person = personRepository.findById(id);
        personRepository.delete(person);
    }

    @PatchMapping(path = "/{id}")
    public void updatePerson(@PathVariable long id, @RequestBody Map<String, Object> body) {
        Person person = personRepository.findById(id);
        person.setFirstName(body.get("firstName").toString());
        person.setLastName(body.get("lastName").toString());
        personRepository.save(person);
    }
    // END
}
