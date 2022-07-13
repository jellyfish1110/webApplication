package com.example.webApplication.fetchloggedusers;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ActiveUserStorage {

    public List<String> users;

    public ActiveUserStorage() {
        users = new ArrayList<String>();
    }
}
