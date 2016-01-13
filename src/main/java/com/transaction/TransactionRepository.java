package com.transaction;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * 
 * A repository simulating the storage of the database, but instead a HashMap is
 * being used here as a data structure
 * 
 * @author Khaled
 *
 */
// @Repository
public class TransactionRepository {

	HashMap<Long, Transaction> transactions;
	HashMap<Long, ArrayList<Long>> transactionsLink;

	/**
	 * Instantiate data structures
	 */
	public TransactionRepository() {

		transactions = new HashMap<Long, Transaction>();
		transactionsLink = new HashMap<Long, ArrayList<Long>>();

	}

	/**
	 * Add a transaction to the HashMap of transactions where the id is the key
	 * 
	 * @param t
	 */
	public void addTransaction(Transaction t) {

		transactions.put(t.getId(), t);

	}

	/**
	 * Add a new link between a transaction and its parent
	 * 
	 * @param id
	 * @param parent_id
	 */
	public void addTransactionLink(long id, long parent_id) {

		if (!transactionsLink.containsKey(parent_id))
			transactionsLink.put(parent_id, new ArrayList<Long>());

		transactionsLink.get(parent_id).add(id);

	}

}