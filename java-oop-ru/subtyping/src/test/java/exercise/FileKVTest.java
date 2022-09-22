package exercise;

import java.util.HashMap;
import org.junit.jupiter.api.BeforeEach;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
// BEGIN
import static org.assertj.core.api.Assertions.assertThat;
// END


class FileKVTest {

    private static Path filepath = Paths.get("src/test/resources/file").toAbsolutePath().normalize();

    @BeforeEach
    public void beforeEach() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        String content = mapper.writeValueAsString(new HashMap<String, String>());
        Files.writeString(filepath, content, StandardOpenOption.CREATE);
    }

    // BEGIN
    @Test
    public void testFileKV() {
        Map<String, String> mapForTest = Map.of("key", "value");
        KeyValueStorage fileKV = new FileKV(filepath.toString(), mapForTest);

        assertThat(fileKV.toMap()).isEqualTo(mapForTest);
        assertThat(fileKV.get("key", "default")).isEqualTo("value");
        assertThat(fileKV.get("key1", "default")).isEqualTo("default");
        fileKV.set("key2", "value2");
        assertThat(fileKV.get("key2", "default")).isEqualTo("value2");
        fileKV.unset("key2");
        assertThat(fileKV.get("key2", "default")).isEqualTo("default");
        fileKV.set("key", "newValue");
        assertThat(fileKV.get("key", "default")).isEqualTo("newValue");
        Map<String, String> newMap = fileKV.toMap();
        newMap.put("key3", "value3");
        assertThat(fileKV.get("key3", "default")).isEqualTo("default");

        KeyValueStorage storage = new FileKV(filepath.toString(), Map.of("foo", "bar",
                "bar", "zoo",
                "zoo", "value",
                "value", "value1"));
        App.swapKeyValue(storage);
        Map<String, String> expected = Map.of("bar", "foo",
                "zoo", "bar",
                "value", "zoo",
                "value1", "value");
        assertThat(storage.toMap()).isEqualTo(expected);
    }
    // END
}
