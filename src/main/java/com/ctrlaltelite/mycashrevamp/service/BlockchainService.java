package com.ctrlaltelite.mycashrevamp.service;

import com.ctrlaltelite.mycashrevamp.model.Block;
import com.ctrlaltelite.mycashrevamp.model.Blockchain;
import com.ctrlaltelite.mycashrevamp.model.Transaction;

import java.util.ArrayList;
import java.util.List;

public interface BlockchainService {



    public void processTransaction(Transaction transaction) ;

    public void mineBlock();

    public Blockchain getBlockchain();


}
