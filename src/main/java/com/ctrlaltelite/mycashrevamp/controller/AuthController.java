package com.ctrlaltelite.mycashrevamp.controller;

import com.ctrlaltelite.mycashrevamp.bean.GenericResponse;
import com.ctrlaltelite.mycashrevamp.bean.JwtResponse;
import com.ctrlaltelite.mycashrevamp.bean.LoginRequest;
import com.ctrlaltelite.mycashrevamp.bean.SignUpRequest;
import com.ctrlaltelite.mycashrevamp.repository.UserRepository;
import com.ctrlaltelite.mycashrevamp.service.impl.UserServiceImpl;
import com.ctrlaltelite.mycashrevamp.utils.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/api/auth")
public class AuthController {



    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JWTUtil jwtUtil;

    @Autowired
    private UserServiceImpl userService;// Your custom user service

    @Autowired
    private UserRepository  userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        UserDetails userDetails = userService.loadUserByUsername(loginRequest.getUsername());
        Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();
        String userRole = authorities.isEmpty() ? null : authorities.iterator().next().getAuthority();

        String token = jwtUtil.generateToken(userRole,userDetails.getUsername());

        return ResponseEntity.ok(new JwtResponse(token));
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> registerUser(@RequestBody SignUpRequest signUpRequest) {
        GenericResponse response;
        try{

        if (userService.existsByUsername(signUpRequest.getUsername())) {
            return new ResponseEntity<>("Username is already taken!", HttpStatus.BAD_REQUEST);
        }

        // Create a new user
         response =userService.createUser(signUpRequest.getUsername(),signUpRequest.getEmail(),passwordEncoder.encode(signUpRequest.getPassword()),signUpRequest.getIsAdmin());
        } catch (Exception e) {
            return new ResponseEntity<>("Error registering user!", HttpStatus.BAD_REQUEST);
        }


        return ResponseEntity.ok(response);
    }


}
