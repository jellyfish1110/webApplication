package com.example.webApplication.userpost;

import java.util.List;

public interface UserPostServiceInterface {

    public List<UserPost> getAllPosts();

    public void postSomething(UserPost userPost);
}
