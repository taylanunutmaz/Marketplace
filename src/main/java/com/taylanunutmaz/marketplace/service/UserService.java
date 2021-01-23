package com.taylanunutmaz.marketplace.service;

import com.taylanunutmaz.marketplace.model.User;
import org.springframework.stereotype.Service;

public interface UserService {
    void save(User user);

    User findByUsername(String username);
}
