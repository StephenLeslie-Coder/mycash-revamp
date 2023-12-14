package com.ctrlaltelite.mycashrevamp.controller;

import com.ctrlaltelite.mycashrevamp.model.*;
import com.ctrlaltelite.mycashrevamp.service.BlockchainService;
import com.ctrlaltelite.mycashrevamp.service.WalletService;
import com.ctrlaltelite.mycashrevamp.utils.CryptoUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/test")
public class TestController {

    @Autowired
    private WalletService walletService;
    @Autowired
    private BlockchainService blockchainService;

    @RequestMapping(value = "/create", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public void createTest() {
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

        //  Blockchain blockchain = new Blockchain();
        Wallet wallet = new Wallet("1", "MonoKari", balances);
        Wallet wallet2 = new Wallet("2", "MonoKari2", balances);

        //  walletService.initiateTransaction(wallet2.getAddress(), wallet.getAddress(), 100.0, "JMD", wallet.getKeyPair(), balances);
        Transaction transaction = walletService.initiateTransaction(wallet2.getAddress(), wallet.getAddress(), 100.0, "JMD", wallet.getKeyPair(), balances);
        blockchainService.processTransaction(transaction);
        blockchainService.mineBlock();
        printBlockchain(blockchainService.getBlockchain());
    }

    private void printBlockchain(Blockchain blockchain) {
        List<Block> chain = blockchain.getChain();
        for (Block block : chain) {
            System.out.println("Block Hash: " + (CryptoUtils.calculateBlockHash(block.getPreviousHash(), block.getTimestamp(), block.getNonce(), block.getTransactions())));
            System.out.println("Previous Hash: " + block.getPreviousHash());

        }
    }
}