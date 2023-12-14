package com.ctrlaltelite.mycashrevamp.repository;

import com.ctrlaltelite.mycashrevamp.entity.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WalletRepository extends JpaRepository<Wallet, Integer> {
}