package com.example.webApplication.userpost;

import com.example.webApplication.exceptions.UserNotLoggedInException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/post")
public class UserPostController {

    private final UserPostService userPostService;

    public UserPostController(UserPostService userPostService) {
        this.userPostService = userPostService;
    }

    @GetMapping
    public List<UserPost> getAllPosts() {
        return userPostService.getAllPosts();
    }

    @PostMapping
    public void postSomething(@RequestBody UserPost userPost) throws UserNotLoggedInException {
        userPostService.postSomething(userPost);
    }
}
