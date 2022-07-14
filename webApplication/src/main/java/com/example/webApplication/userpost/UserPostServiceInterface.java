package com.example.webApplication.userpost;

import java.util.List;

public interface UserPostServiceInterface {

    List<UserPost> getAllPosts();

    void postSomething(UserPost userPost);
}
