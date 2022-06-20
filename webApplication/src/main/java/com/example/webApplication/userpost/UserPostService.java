package com.example.webApplication.userpost;

import org.apache.catalina.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserPostService {

    private final UserPostRepository userPostRepository;

    public UserPostService(UserPostRepository userPostRepository) {
        this.userPostRepository = userPostRepository;
    }

    public List<UserPost> getAllPosts() {
        return userPostRepository.findAll();
    }

    public void postSomething(UserPost userPost) {
        userPostRepository.save(userPost);
    }
}
