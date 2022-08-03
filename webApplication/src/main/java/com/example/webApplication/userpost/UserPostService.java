package com.example.webApplication.userpost;

import com.example.webApplication.exceptions.UserNotLoggedInException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserPostService implements UserPostServiceInterface {

    private final UserPostRepository userPostRepository;

    public UserPostService(UserPostRepository userPostRepository) {
        this.userPostRepository = userPostRepository;
    }

    @Override
    public List<UserPost> getAllPosts() {
        return userPostRepository.findAll();
    }

    @Override
    public void postSomething(UserPost userPost) throws UserNotLoggedInException {
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

    @Override
    public void updateOwnerId(Long id) {
    }
}