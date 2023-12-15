package com.ctrlaltelite.mycashrevamp.service.impl;

import com.ctrlaltelite.mycashrevamp.bean.GenericResponse;
import com.ctrlaltelite.mycashrevamp.entity.User;
import com.ctrlaltelite.mycashrevamp.entity.Wallet;
import com.ctrlaltelite.mycashrevamp.exceptions.GenericException;
import com.ctrlaltelite.mycashrevamp.exceptions.UserNotFoundException;

import com.ctrlaltelite.mycashrevamp.repository.UserRepository;
import com.ctrlaltelite.mycashrevamp.service.UserService;
import com.ctrlaltelite.mycashrevamp.service.WalletService;
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
    private UserRepository userRepository;
    @Autowired
    private WalletService walletService;


    @Override
    public GenericResponse createUser(String username,String email,String password,boolean isAdmin) throws GenericException {
        log.debug("Enter method createUser: {}");
         com.ctrlaltelite.mycashrevamp.entity.User createdUser = null;

        try {
            if (userRepository.findByUsername(username) != null) {
                throw new GenericException("Username already exists",400);
            }
            if (userRepository.findByEmail(email) != null) {
                throw new GenericException("Email already exists",400);
            }
            createdUser = new com.ctrlaltelite.mycashrevamp.entity.User();
            createdUser.setEmail(email);
            createdUser.setPassword(password);
            createdUser.setUsername(username);
            createdUser.setRole(isAdmin?"ROLE_ADMIN":"ROLE_USER");
            // Save the user to get the generated ID
            createdUser = userRepository.save(createdUser);

            // Create a wallet for the user
            Wallet wallet = walletService.createWallet(createdUser);

            // Set the wallet to the user and save the changes
            createdUser.setWallet(wallet);
           // userRepository.save(createdUser);
            userRepository.saveAndFlush(createdUser);
            log.debug("Return method createUser: {}", createdUser);

            return new GenericResponse<>(200, "User created successfully", createdUser);


        } catch (GenericException e) {
            throw e;
        }

    }

    @Override
    public User getUser(String id) throws UserNotFoundException {
        log.debug("Enter method getUser: {}", id);
        User user = new User();
        try {
            if (userRepository.findById(Integer.valueOf(id)).isPresent()) {
                user = userRepository.findById(Integer.valueOf(id)).get();
            } else {
                throw new UserNotFoundException();
            }
        } catch (UserNotFoundException e) {
            log.error("Error calling getUser. Method params: {}", id);
            throw e;
        }
        log.debug("Return method getUser: {}", user);
        return user;
    }

    @Override
    public User updateUser(String id, User user) {
        log.debug("Enter method updateUser: {} {}", id, user);
        User updatedUser = new User();

        try{
            if(userRepository.findById(Integer.valueOf(id)).isPresent()){
                updatedUser = userRepository.findById(Integer.valueOf(id)).get();
                updatedUser.setEmail(user.getEmail());
                updatedUser.setUsername(user.getUsername());
                userRepository.save(updatedUser);
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
        User user = userRepository.findByUsername(s);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with username: " + s);
        }
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                Collections.singletonList(new SimpleGrantedAuthority(user.getRole()))
        );
    }

    @Override
    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }
}
