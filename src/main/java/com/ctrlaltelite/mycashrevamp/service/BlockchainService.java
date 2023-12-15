package com.ctrlaltelite.mycashrevamp.service;

import com.ctrlaltelite.mycashrevamp.entity.Block;
import com.ctrlaltelite.mycashrevamp.model.Blockchain;
import com.ctrlaltelite.mycashrevamp.model.Transaction;

import java.util.ArrayList;
import java.util.List;

public interface BlockchainService {



    public void processTransaction(Transaction transaction) ;

    public Block mineBlock();

    public Blockchain getBlockchain();


}
