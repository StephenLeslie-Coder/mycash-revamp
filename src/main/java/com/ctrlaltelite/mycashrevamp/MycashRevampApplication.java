package com.ctrlaltelite.mycashrevamp;

import com.ctrlaltelite.mycashrevamp.model.*;
import com.ctrlaltelite.mycashrevamp.service.BlockchainService;
import com.ctrlaltelite.mycashrevamp.service.WalletService;
import com.ctrlaltelite.mycashrevamp.utils.CryptoUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class MycashRevampApplication {
    public static void main(String[] args) {
        SpringApplication.run(MycashRevampApplication.class, args);
    }
}


