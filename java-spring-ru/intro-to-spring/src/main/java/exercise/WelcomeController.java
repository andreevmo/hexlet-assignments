package exercise;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

// BEGIN
@RestController
public class WelcomeController {

    @GetMapping("/")
    public String welcome() {
        return "Welcome to Spring";
    }

    @GetMapping("hello")
    public String sayHelo(@RequestParam(required = false) String name) {
        if (name == null) {
            name = "World";
        }
        return "Hello, " + name + "!";
    }
}
// END
