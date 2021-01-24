package com.taylanunutmaz.marketplace.service;

import com.taylanunutmaz.marketplace.model.Role;
import com.taylanunutmaz.marketplace.model.User;
import org.springframework.stereotype.Service;

import java.util.Set;

public interface UserService {
    void save(User user);

    User findByUsername(String username);
}
