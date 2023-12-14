package com.ctrlaltelite.mycashrevamp.service;

import com.ctrlaltelite.mycashrevamp.entity.User;

public interface UserService {

    public void createUser();

    public User getUser(String id);

    public User updateUser(String id, User user);
}
