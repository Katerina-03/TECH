package org.example.Controllers;

import org.example.Security.UserService;
import org.example.Entities.User;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private UserService userService;

    @PreAuthorize("hasAuthority('admin')")
    @PostMapping("/registration")
    public String addUser(@RequestBody User user) {

        if(user != null) {
            userService.save(user);
            return "Added a user";
        } else {
            return "Request does not contain a body";
        }
    }
}