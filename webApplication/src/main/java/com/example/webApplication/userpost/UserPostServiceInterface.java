package com.example.webApplication.userpost;

import com.example.webApplication.exceptions.UserNotLoggedInException;

import java.util.List;

public interface UserPostServiceInterface {

    List<UserPost> getAllPosts();

    void postSomething(UserPost userPost) throws UserNotLoggedInException;

    void updateOwnerId(Long id);
}
