package com.projects.blog.services;

import com.projects.blog.entities.User;

import java.util.UUID;

public interface
UserService {

    User  getUserById(UUID id);

}
