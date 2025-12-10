package librarytest;


import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.Test;

import library.service.EmailNotifier;
import libraryy.User;

public class EmailNotifierTest {

    @Test
    public void testNotifyUserPrintsCorrectMessage() {

       
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));

        EmailNotifier notifier = new EmailNotifier();
        User user = new User("Masa", "Masa@mail.com");

        notifier.notifyUser(user, "You have overdue books!");

        String printed = output.toString().trim();

        assertTrue(printed.contains("Email sent to Masa@mail.com"));
        assertTrue(printed.contains("You have overdue books!"));
    }
}
