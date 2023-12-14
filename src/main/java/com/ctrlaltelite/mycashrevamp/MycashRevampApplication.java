package com.ctrlaltelite.mycashrevamp;

import com.ctrlaltelite.mycashrevamp.model.*;
import com.ctrlaltelite.mycashrevamp.utils.CryptoUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class MycashRevampApplication {

    public static void main(String[] args) {
        SpringApplication.run(MycashRevampApplication.class, args);
        MycashRevampApplication app = new MycashRevampApplication();
        app.start();
    }

    public void start() {

        Balance balance = new Balance();
        balance.setAmount(1000);
        balance.setCrypto(true);
        balance.setCurrencyCode("CTRL");
        balance.setName("CTRL");

        Balance balance2 = new Balance();
        balance2.setAmount(10000);
        balance2.setCrypto(false);
        balance2.setCurrencyCode("JMD");
        balance2.setName("JMD");

        List<Balance> balances = new ArrayList<>();
        balances.add(balance);
        balances.add(balance2);

        Blockchain blockchain = new Blockchain();
        Wallet wallet = new Wallet("1", "MonoKari", balances);
        Wallet wallet2 = new Wallet("2", "MonoKari2", balances);
        Transaction transaction = wallet.createTransaction(wallet2.getAddress(), 100.0, "JMD");
        blockchain.processPayment(transaction);
        blockchain.minePendingPayments();
        printBlockchain(blockchain);
    }

    private void printBlockchain(Blockchain blockchain) {
        List<Block> chain = blockchain.getChain();
        for (Block block : chain) {
            System.out.println("Block Hash: " + (CryptoUtils.calculateBlockHash(block.getPreviousHash(), block.getTimestamp(), block.getNonce(), block.getTransactions())));
            System.out.println("Previous Hash: " + block.getPreviousHash());

        }
    }
}


