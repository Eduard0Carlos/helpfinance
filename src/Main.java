import entities.Address;
import entities.User;

import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {

        var user = new User("Carlos", LocalDateTime.now(), "carlos@gmail.com", "123456");

        var address = new Address(user.getId(), "53172", "221", "Blumenau", "SC", "Brasil");

        System.out.print("Hello and welcome!");
    }
}