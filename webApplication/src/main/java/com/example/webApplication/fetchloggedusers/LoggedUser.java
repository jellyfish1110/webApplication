package com.example.webApplication.fetchloggedusers;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;
import java.util.List;

@Component
@NoArgsConstructor
@Getter
@Setter
public class LoggedUser implements HttpSessionBindingListener {

    private String username;
    private ActiveUserStorage activeUserStorage;

    public LoggedUser (String username, ActiveUserStorage activeUserStorage) {
        this.username = username;
        this.activeUserStorage = activeUserStorage;
    }

    @Override
    public void valueBound(HttpSessionBindingEvent event) {
        List<String> users = activeUserStorage.getUsers();
        LoggedUser user = (LoggedUser) event.getValue();
        if (!users.contains(user.getUsername())) {
            users.add(user.getUsername());
        }
    }

    @Override
    public void valueUnbound(HttpSessionBindingEvent event) {
        List<String> users = activeUserStorage.getUsers();
        LoggedUser user = (LoggedUser) event.getValue();
        if (users.contains(user.getUsername())) {
            users.remove(user.getUsername());
        }
    }
}