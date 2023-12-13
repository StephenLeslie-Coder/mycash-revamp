package com.ctrlaltelite.mycashrevamp.model;

import com.ctrlaltelite.mycashrevamp.utils.CryptoUtils;
import lombok.Data;

import java.util.List;

@Data
public class Block {

        private String previousHash;
        private List<Transaction> transactions;
        private String hash;
        private long timestamp;
        private int nonce;

        public Block(String previousHash, List<Transaction> transactions, long timestamp) {
            this.previousHash = previousHash;
            this.transactions = transactions;
            this.timestamp = timestamp;
            this.nonce = 0;
            this.hash = calculateHash();
        }

        public void mineBlock(int difficulty) {
            String target = new String(new char[difficulty]).replace('\0', '0');
            while (!hash.substring(0, difficulty).equals(target)) {
                nonce++;
                hash = calculateHash();
            }
        }

        private String calculateHash() {
            return CryptoUtils.calculateBlockHash(previousHash,timestamp,nonce,transactions);
        }
    }



