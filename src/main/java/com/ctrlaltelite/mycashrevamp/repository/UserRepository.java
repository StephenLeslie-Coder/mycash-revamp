package com.ctrlaltelite.mycashrevamp.repository;

import com.ctrlaltelite.mycashrevamp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUsername(String username);


    boolean existsByUsername(String username);

    User findByEmail(String email);
}