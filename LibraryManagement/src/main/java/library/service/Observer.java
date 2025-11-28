package library.service;

import libraryy.User;

public interface Observer {
    void notifyUser(User user, String msg);
}