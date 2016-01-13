package com.transaction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 
 * Service class responsible for doing any logic required for interacting with
 * the transactions information
 * 
 * @author Khaled
 *
 */
//@Service
public class TransactionService {

	// @Autowired
	private final TransactionRepository repo = new TransactionRepository();

	/**
	 * Creates a new transaction with a given id and other attributes included
	 * in tInput
	 * 
	 * @param id
	 * @param tInput
	 */
	public void createTransaction(long id, Transaction tInput) {

		Transaction t = new Transaction();

		t.setId(id);
		t.setAmount(tInput.getAmount());
		t.setType(tInput.getType());
		t.setParent_id(tInput.getParent_id());

		repo.addTransaction(t);

		//
		if (tInput.getParent_id() != 0)
			repo.addTransactionLink(id, tInput.getParent_id());

	}

	/**
	 * Retrieves transaction by id
	 * 
	 * @param id
	 * @return
	 */
	public Transaction getTransaction(long id) {

		return repo.transactions.get(id);

	}

	/**
	 * Retrieves one or more transactions by type
	 * 
	 * @param type
	 * @return
	 */
	public ArrayList<Long> getTransactionsWithType(String type) {

		ArrayList<Long> idList = new ArrayList<Long>();

		for (Transaction t : repo.transactions.values()) {

			if (t.getType().equals(type))
				idList.add(t.getId());

		}

		return idList;

	}

	/**
	 * A sum of all transactions that are transitively linked by their parent_id
	 * to id
	 * 
	 * @param id
	 * @return
	 */
	public Double getSumOfTransaction(long id) {

		double sum = 0;
		
		//Transaction does not exist
		if (!repo.transactions.containsKey(id))
			return new Double(0);

		sum += repo.transactions.get(id).getAmount();

		//Get amount of linked transactions
		if (repo.transactionsLink.containsKey(id))
			for (Long child_id : repo.transactionsLink.get(id)) {

				sum += getSumOfTransaction(child_id);

			}

		return sum;

	}

}
