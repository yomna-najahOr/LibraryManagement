/**
 * Observer implementation that sends notifications to users via email.
 * In this simulation, the notification is printed to the console.
 */
package library.service;

import libraryy.User;

public class EmailNotifier implements Observer {

    @Override
    public void notifyUser(User user, String msg) {
        System.out.println("Email sent to " + user.getEmail() + ": " + msg);
    }
}