package com.ctrlaltelite.mycashrevamp.repository;

import com.ctrlaltelite.mycashrevamp.entity.WalletBalance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WalletBalanceRepository extends JpaRepository<WalletBalance, String> {
}