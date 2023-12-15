package com.ctrlaltelite.mycashrevamp.service.impl;

import com.ctrlaltelite.mycashrevamp.bean.GenericResponse;
import com.ctrlaltelite.mycashrevamp.entity.Block;
import com.ctrlaltelite.mycashrevamp.entity.User;
import com.ctrlaltelite.mycashrevamp.entity.Wallet;
import com.ctrlaltelite.mycashrevamp.exceptions.GenericException;
import com.ctrlaltelite.mycashrevamp.model.Balance;
import com.ctrlaltelite.mycashrevamp.model.Transaction;
import com.ctrlaltelite.mycashrevamp.repository.BlockRepository;
import com.ctrlaltelite.mycashrevamp.repository.TransactionRepository;
import com.ctrlaltelite.mycashrevamp.repository.UserRepository;
import com.ctrlaltelite.mycashrevamp.repository.WalletRepository;
import com.ctrlaltelite.mycashrevamp.service.BalanceService;
import com.ctrlaltelite.mycashrevamp.service.BlockchainService;
import com.ctrlaltelite.mycashrevamp.service.TransactionService;
import com.ctrlaltelite.mycashrevamp.service.WalletService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.KeyPair;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class WalletServiceImpl implements WalletService {
    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    TransactionService transactionService;
    @Autowired
    WalletRepository walletRepository;
    @Autowired
    BalanceService balanceService;
    @Autowired
    BlockchainService blockService;
    @Autowired
    BlockRepository blockRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public Transaction initiateTransaction(String recipientAddress, String senderAddress, double amount, String currencyCode, KeyPair keyPair, List<Balance> balance) throws GenericException {

        Transaction transaction = new Transaction(senderAddress, recipientAddress, amount, currencyCode);
        String signature = transactionService.signTransaction(senderAddress, recipientAddress, amount, keyPair);
        transaction.setSignature(signature);

        List<Balance> foundBalance = balance.stream().filter(x -> x.getCurrencyCode().equals(currencyCode)).collect(Collectors.toList());

        if (foundBalance.isEmpty()) {
            throw new GenericException("Balance not found", 400);
        }
        if (!transactionService.isValidTransaction(transaction, foundBalance.get(0), keyPair)) {
            throw new GenericException("Invalid transaction. Check transaction details and balance.", 400);
        }

        return transaction;
    }

    @Override
    public Wallet createWallet(User user) {
        Wallet newWallet = new Wallet();
        KeyPair keyPair = com.ctrlaltelite.mycashrevamp.model.Wallet.generateKeyPair();
        newWallet.setUser(user);

        byte[] privateKeyBytes = keyPair.getPrivate().getEncoded();
        newWallet.setPrivate_key(privateKeyBytes);

        byte[] publicKeyBytes = keyPair.getPublic().getEncoded();
        newWallet.setPublic_key(publicKeyBytes);
        return walletRepository.save(newWallet);
    }

    @Override
    public GenericResponse addBalanceToWallet(String name, double amount, boolean isCrypto, String currencyCode, String username) throws GenericException {
        com.ctrlaltelite.mycashrevamp.entity.Balance balance = null;
        try {

            User user = userRepository.findByUsername(username);
            if (user == null) {
                throw new GenericException("User not found", 400);
            }
            balance = balanceService.addBalance(name, amount, isCrypto, currencyCode, user.getWallet());

            Transaction transaction = new Transaction(String.valueOf(user.getWallet().getId()), String.valueOf(user.getWallet().getId()), amount, currencyCode);
            String signature = transactionService.signTransaction(String.valueOf(user.getWallet().getId()), String.valueOf(user.getWallet().getId()), amount, com.ctrlaltelite.mycashrevamp.model.Wallet.getKeyPairFromBytes(user.getWallet().getPrivate_key(), user.getWallet().getPublic_key()));
            transaction.setSignature(signature);
            com.ctrlaltelite.mycashrevamp.entity.Transaction transEntity = new com.ctrlaltelite.mycashrevamp.entity.Transaction();
            transEntity.setSignature(transaction.getSignature());
            transEntity.setAmount(transaction.getAmount());
            transEntity.setSenderAddress(transaction.getSenderAddress());
            transEntity.setReceiverAddress(transaction.getRecipientAddress());
            transEntity.setCurrency(transaction.getCurrency());
            Block block = blockService.mineBlock();
            blockService.processTransaction(transaction);
            blockRepository.save(block);

            transEntity.setBlock(block);
            transactionRepository.save(transEntity);


            user.getWallet().getBalances().add(balance);
            Wallet wallet = walletRepository.save(user.getWallet());
            return new GenericResponse(200, "Balance added successfully to waller", wallet);
        } catch (Exception e) {
            log.error(e.getMessage());
            if (balance == null) {
                throw new GenericException("Balance could not be added.", 400);
            }
        }
        return null;
    }



    @Override
    public void adjustBalance(double amount, String currencyCode, String username) throws GenericException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new GenericException("User not found", 400);
        }

        com.ctrlaltelite.mycashrevamp.entity.Balance balance = user.getWallet().getBalances().stream().findAny().filter(x -> x.getCurrencyCode().equals(currencyCode)).get();
        balance.setAmount(balance.getAmount() + amount);

    }


}
