package exercise;

import static org.assertj.core.api.Assertions.assertThat;
import java.util.List;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;

class AppTest {

    List<Integer> numbers;

    @Test
    void testTake() {
        // BEGIN
        numbers = new ArrayList<>();
        numbers.add(1);
        numbers.add(2);
        numbers.add(3);
        numbers.add(4);
        numbers.add(5);
        assertThat(App.take(numbers, 0)).isEmpty();
        assertThat(App.take(numbers, 3).size()).isEqualTo(3);
        assertThat(App.take(numbers, 4).size()).isEqualTo(4);
        assertThat(App.take(numbers, numbers.size())).containsAll(numbers);
        assertThat(App.take(numbers, 2)).doesNotContain(numbers.get(4));
        assertThat(App.take(numbers, 1)).isInstanceOf(numbers.getClass());
        assertThat(App.take(numbers, 3)).startsWith(numbers.get(0));
        assertThat(App.take(numbers, 3)).endsWith(numbers.get(2));

        numbers.remove(3);
        assertThat(App.take(numbers, 5).size()).isEqualTo(4);

        numbers = new ArrayList<>();
        assertThat(App.take(numbers, numbers.size())).isEmpty();
        assertThat(App.take(numbers, 5)).doesNotContainNull();
        assertThat(App.take(numbers, 0).size()).isEqualTo(0);
        assertThat(App.take(numbers, 0).size()).isNotNegative();

        numbers.add(null);
        numbers.add(null);
        assertThat(App.take(numbers, numbers.size())).isNotEmpty();
        // END
    }
}
