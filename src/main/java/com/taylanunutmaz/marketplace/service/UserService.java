package com.taylanunutmaz.marketplace.service;

import com.taylanunutmaz.marketplace.model.User;

public interface UserService {
    void save(User user);

    User findByUsername(String username);
}
