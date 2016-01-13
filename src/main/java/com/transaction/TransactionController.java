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
 * Controller class that includes all the end-points needed for creating,
 * deleting and searching for transactions
 * 
 * @author Khaled
 *
 */
@RestController
@RequestMapping(value = "/transactionservice")
public class TransactionController {

	//@Autowired
	private final TransactionService transactionService = new TransactionService();

	/**
	 * End-point for creating a new transaction
	 * 
	 * @param id
	 * @param tInput
	 */
	@RequestMapping(value = "/transaction/{id}", method = RequestMethod.PUT)
	public void createTransaction(@PathVariable long id, @RequestBody Transaction tInput) {

		transactionService.createTransaction(id, tInput);

	}

	/**
	 * End-point for loading a transaction by id
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/transaction/{id}", method = RequestMethod.GET)
	public @ResponseBody Transaction getTransaction(@PathVariable long id) {

		return transactionService.getTransaction(id);

	}

	/**
	 * End-point for loading one or more transactions by type
	 * 
	 * @param type
	 * @return
	 */
	@RequestMapping(value = "/types/{type}", method = RequestMethod.GET)
	public @ResponseBody ArrayList<Long> getTransactionsWithType(@PathVariable String type) {

		return transactionService.getTransactionsWithType(type);

	}

	/**
	 * End-point for summing amounts of a transaction and all transactions
	 * linked to it
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/sum/{id}", method = RequestMethod.GET)
	public @ResponseBody Double getSumOfTransaction(@PathVariable long id) {

		return transactionService.getSumOfTransaction(id);

	}

}
