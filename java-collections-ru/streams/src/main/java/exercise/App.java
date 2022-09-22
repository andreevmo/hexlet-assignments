package exercise;

import java.util.List;

// BEGIN
class App {

    public static long getCountOfFreeEmails(List<String> emails) {
        List<String> freeDomains = List.of("gmail.com", "yandex.ru", "hotmail.com");
        return emails.stream()
                .map(email -> email.split("@"))
                .map(email -> email[email.length - 1])
                .filter(freeDomains::contains)
                .count();
    }
}
// END
