package com.ctrlaltelite.mycashrevamp.service;

import com.ctrlaltelite.mycashrevamp.entity.Balance;
import com.ctrlaltelite.mycashrevamp.entity.Wallet;

public interface BalanceService {
    public Balance addBalance(String name, double amount, boolean isCrypto, String currencyCode, Wallet wallet);
}
