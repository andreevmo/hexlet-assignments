package exercise;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

// BEGIN
class SorterTest {
    @Test
    void testTakeOldestMans() {

        List<Map<String, String>> users1 = List.of(
                Map.of("name", "Vladimir Nikolaev", "birthday", "1990-12-27", "gender", "male"),
                Map.of("name", "Andrey Petrov", "birthday", "1989-11-23", "gender", "male"),
                Map.of("name", "Anna Sidorova", "birthday", "1996-09-09", "gender", "female"),
                Map.of("name", "John Smith", "birthday", "1989-03-11", "gender", "male"),
                Map.of("name", "Vanessa Vulf", "birthday", "1985-11-16", "gender", "female"),
                Map.of("name", "Alice Lucas", "birthday", "1986-01-01", "gender", "female"),
                Map.of("name", "Elsa Oscar", "birthday", "1970-03-10", "gender", "female")
        );
        assertThat(Sorter.takeOldestMans(users1)).isEqualTo(List.of("John Smith", "Andrey Petrov", "Vladimir Nikolaev"));

        List<Map<String, String>> users2 = List.of(
                Map.of("name", "Vladimir Nikolaev", "birthday", "1954-11-21", "gender", "male"),
                Map.of("name", "Andrey Petrov", "birthday", "1994-12-15", "gender", "male"),
                Map.of("name", "Anna Sidorova", "birthday", "1996-09-09", "gender", "female"),
                Map.of("name", "John Smith", "birthday", "1981-05-17", "gender", "male"),
                Map.of("name", "Vanessa Vulf", "birthday", "1985-11-16", "gender", "female"),
                Map.of("name", "Alice Lucas", "birthday", "1986-01-01", "gender", "female"),
                Map.of("name", "Elsa Oscar", "birthday", "1970-03-10", "gender", "female")
        );
        assertThat(Sorter.takeOldestMans(users2)).isEqualTo(List.of("Vladimir Nikolaev", "John Smith", "Andrey Petrov"));

        List<Map<String, String>> users3 = List.of(
                Map.of("name", "Vladimir Nikolaev", "birthday", "1954-11-21", "gender", "male"),
                Map.of("name", "Andrey Petrov", "birthday", "1994-12-15", "gender", "male"),
                Map.of("name", "Andrei Sidorov", "birthday", "1993-09-09", "gender", "male"),
                Map.of("name", "John Smith", "birthday", "1981-05-17", "gender", "male"),
                Map.of("name", "Vanessa Vulf", "birthday", "1985-11-16", "gender", "female"),
                Map.of("name", "Petr Petrov", "birthday", "1980-05-17", "gender", "male"),
                Map.of("name", "Elsa Oscar", "birthday", "1970-03-10", "gender", "female")
        );
        assertThat(Sorter.takeOldestMans(users3))
                .isEqualTo(List.of("Vladimir Nikolaev", "Petr Petrov", "John Smith", "Andrei Sidorov", "Andrey Petrov"));

        List<Map<String, String>> users4 = List.of(
                Map.of("name", "Vladimir Nikolaev", "birthday", "1954-11-21", "gender", "female"),
                Map.of("name", "Andrey Petrov", "birthday", "1994-12-15", "gender", "female"),
                Map.of("name", "Andrei Sidorov", "birthday", "1993-09-09", "gender", "female"),
                Map.of("name", "John Smith", "birthday", "1981-05-17", "gender", "female"),
                Map.of("name", "Vanessa Vulf", "birthday", "1985-11-16", "gender", "female"),
                Map.of("name", "Petr Petrov", "birthday", "1980-05-17", "gender", "female"),
                Map.of("name", "Elsa Oscar", "birthday", "1970-03-10", "gender", "female")
        );
        assertThat(Sorter.takeOldestMans(users4)).isEmpty();
    }
}
// END


