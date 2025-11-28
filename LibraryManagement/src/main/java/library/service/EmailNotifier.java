package library.service;

import libraryy.User;

public class EmailNotifier implements Observer {

    @Override
    public void notifyUser(User user, String msg) {
        System.out.println("Email sent to " + user.getEmail() + ": " + msg);
    }
}