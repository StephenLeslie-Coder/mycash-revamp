package com.ctrlaltelite.mycashrevamp.service.impl;

import com.ctrlaltelite.mycashrevamp.entity.User;
import com.ctrlaltelite.mycashrevamp.exceptions.UserNotFoundException;
import com.ctrlaltelite.mycashrevamp.model.Wallet;
import com.ctrlaltelite.mycashrevamp.repository.UserRepository;
import com.ctrlaltelite.mycashrevamp.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository repository;


    @Override
    public void createUser(String email, String pw) {
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

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = repository.findByUsername(s);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with username: " + s);
        }
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER"))
        );
    }

    @Override
    public boolean existsByUsername(String username) {
        return repository.existsByUsername(username);
    }
}
