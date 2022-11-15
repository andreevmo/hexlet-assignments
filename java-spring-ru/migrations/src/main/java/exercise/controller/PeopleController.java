package exercise.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/people")
public class PeopleController {
    @Autowired
    JdbcTemplate jdbc;

    @PostMapping(path = "")
    public void createPerson(@RequestBody Map<String, Object> person) {
        String query = "INSERT INTO person (first_name, last_name) VALUES (?, ?)";
        jdbc.update(query, person.get("first_name"), person.get("last_name"));
    }

    // BEGIN
    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Map<String, Object>> getAllPerson() {
        String query = "SELECT * FROM person";
        return jdbc.queryForList(query);
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> getPerson(@PathVariable String id) {
        String query = "SELECT * FROM person WHERE id = ?";
        Map<String, Object> result = new HashMap<>();
        try {
            result = jdbc.queryForMap(query, id);
        } catch (Exception e) {
            result.put("Not found", "Not found");
        }
        return result;
    }
    // END
}
