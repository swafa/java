package com.transaction;

import java.util.HashMap;

public class TransactionRepository {

	HashMap<Long, Transaction> transactions;

	public TransactionRepository() {

		transactions = new HashMap<Long, Transaction>();

	}

	public void add(Transaction t) {

		transactions.put(t.getId(), t);

	}

}