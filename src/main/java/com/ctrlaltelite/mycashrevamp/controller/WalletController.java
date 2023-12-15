package com.ctrlaltelite.mycashrevamp.controller;

import com.ctrlaltelite.mycashrevamp.bean.*;
import com.ctrlaltelite.mycashrevamp.service.WalletService;
import com.ctrlaltelite.mycashrevamp.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
@RestController
@RequestMapping("/api/wallet")
public class WalletController {
    @Autowired
    private WalletService walletService;

    @Autowired
    UserServiceImpl userService;

    @RequestMapping(value = "/addBalance", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addBalance(@RequestBody AddBalanceRequest balanceRequest) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        GenericResponse response;
        try {
            response = walletService.addBalanceToWallet(balanceRequest.getName(), balanceRequest.getAmount(), balanceRequest.getIsCrypto(), balanceRequest.getCurrencyCode(),authentication.getName());

        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }

        return new ResponseEntity<>(response, org.springframework.http.HttpStatus.OK);

    }

}
