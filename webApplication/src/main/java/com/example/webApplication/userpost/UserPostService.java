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
        String title = userPost.getTitle();
        String content = userPost.getContent();

        if(title.length() < 16 || title.length() > 64) {
            throw new IndexOutOfBoundsException("Title must contain between 16 and 64 characters!");
        }

        if(content.length() > 8192) {
            throw new IndexOutOfBoundsException("Content section should not exceed 8192 characters!");
        }
        userPostRepository.save(userPost);
    }
}
