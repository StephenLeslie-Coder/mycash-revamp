package com.ctrlaltelite.mycashrevamp.service.impl;

import com.ctrlaltelite.mycashrevamp.entity.Balance;
import com.ctrlaltelite.mycashrevamp.entity.Wallet;
import com.ctrlaltelite.mycashrevamp.repository.BalanceRepository;
import com.ctrlaltelite.mycashrevamp.service.BalanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.AccessType;
import org.springframework.stereotype.Service;

@Service
public class BalanceServiceImpl implements BalanceService {

    @Autowired
    BalanceRepository balanceRepository;

    @Override
    public Balance addBalance(String name, double amount, boolean isCrypto, String currencyCode, Wallet wallet) {
        Balance balance = new Balance();
        balance.setName(name);
        balance.setAmount(amount);
        balance.setIsCrypto(isCrypto);
        balance.setCurrencyCode(currencyCode);
        balance.setWallet(wallet);
        balanceRepository.save(balance);
        return balance;
    }
}
