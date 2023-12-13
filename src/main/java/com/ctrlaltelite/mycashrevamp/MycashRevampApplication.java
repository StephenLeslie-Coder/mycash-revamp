package com.ctrlaltelite.mycashrevamp;

import com.ctrlaltelite.mycashrevamp.model.Block;
import com.ctrlaltelite.mycashrevamp.model.Blockchain;
import com.ctrlaltelite.mycashrevamp.model.Transaction;
import com.ctrlaltelite.mycashrevamp.model.Wallet;
import com.ctrlaltelite.mycashrevamp.utils.CryptoUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

import java.util.List;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class MycashRevampApplication {

	public static void main(String[] args) {
		SpringApplication.run(MycashRevampApplication.class, args);
		MycashRevampApplication app = new MycashRevampApplication();
		app.start();
	}

	public void start() {

			Blockchain blockchain = new Blockchain();
		    Wallet wallet = new Wallet("1","MonoKari",2000);
		    Wallet wallet2 = new Wallet("2","MonoKari2",4000);
			Transaction transaction = wallet.createTransaction(wallet2.getAddress(), 100.0);
			blockchain.processPayment(transaction);
			blockchain.minePendingPayments();
			printBlockchain(blockchain);
		}

		// Helper method to print the blockchain
		private void printBlockchain(Blockchain blockchain) {
			List<Block> chain = blockchain.getChain();
			for (Block block : chain) {
				System.out.println("Block Hash: " + (CryptoUtils.calculateBlockHash(block.getPreviousHash(),block.getTimestamp(),block.getNonce(),block.getTransactions())));
				System.out.println("Previous Hash: " + block.getPreviousHash());

			}
		}
	}


