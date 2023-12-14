package com.ctrlaltelite.mycashrevamp.service;

import com.ctrlaltelite.mycashrevamp.entity.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;



public interface UserService extends UserDetailsService {


    void createUser(String email, String pw);

    public User getUser(String id);

    public User updateUser(String id, User user);

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;

    public boolean existsByUsername(String username);
}
