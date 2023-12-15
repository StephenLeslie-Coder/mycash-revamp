package com.ctrlaltelite.mycashrevamp.service;

import com.ctrlaltelite.mycashrevamp.bean.GenericResponse;
import com.ctrlaltelite.mycashrevamp.entity.User;
import com.ctrlaltelite.mycashrevamp.exceptions.GenericException;
import com.ctrlaltelite.mycashrevamp.exceptions.UserNotFoundException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;



public interface UserService extends UserDetailsService {


    GenericResponse createUser(String username,String email,String password,boolean isAdmin) throws GenericException;

    public User getUser(String id) throws UserNotFoundException;

    public User updateUser(String id, User user);

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;

    public boolean existsByUsername(String username);
}
