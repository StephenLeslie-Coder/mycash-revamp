package com.ctrlaltelite.mycashrevamp.service.impl;

import com.ctrlaltelite.mycashrevamp.entity.User;
import com.ctrlaltelite.mycashrevamp.exceptions.UserNotFoundException;
import com.ctrlaltelite.mycashrevamp.model.Wallet;
import com.ctrlaltelite.mycashrevamp.repository.UserRepository;
import com.ctrlaltelite.mycashrevamp.service.UserService;
import com.ctrlaltelite.mycashrevamp.service.WalletService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Date;
import java.sql.Timestamp;

@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private Wallet walletService;

    @Override
    public void createUser( String email, String pw) {
        log.debug("Enter method createUser: {}");
        User createdUser = new User();

        repository.save(createdUser);
        log.debug("Return method createUser: {}", createdUser);
    }

    @Override
    public User getUser(String id) {
        log.debug("Enter method getUser: {}", id);
        User user = new User();
        try {
            if (repository.findById(Integer.valueOf(id)).isPresent()) {
                user = repository.findById(Integer.valueOf(id)).get();
            } else {
                throw new UserNotFoundException();
            }
        } catch (UserNotFoundException e) {
            log.error("Error calling getUser. Method params: {}", id);
        }
        log.debug("Return method getUser: {}", user);
        return user;
    }

    @Override
    public User updateUser(String id, User user) {
        log.debug("Enter method updateUser: {} {}", id, user);
        User updatedUser = new User();

        try{
            if(repository.findById(Integer.valueOf(id)).isPresent()){
                updatedUser = repository.findById(Integer.valueOf(id)).get();
                updatedUser.setEmail(user.getEmail());
                updatedUser.setUsername(user.getUsername());
                repository.save(updatedUser);
            }else{
                throw new UserNotFoundException();
            }
        }catch(UserNotFoundException e){
            log.error("Error calling finding user. Method params: {}", id);
        }
        log.debug("Return method updatedUser: {}", updatedUser);
        return updatedUser;
    }
}
