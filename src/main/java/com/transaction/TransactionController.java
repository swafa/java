package com.transaction;

import java.util.concurrent.atomic.AtomicLong;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@RestController
public class TransactionController {

	private final TransactionRepository repo = new TransactionRepository();

	@RequestMapping(value = "/transaction", method = RequestMethod.POST)
	public @ResponseBody Transaction transaction(@RequestBody Transaction tInput) {
		
		Transaction t = new Transaction();
		
		t.setId(tInput.getId());
		t.setAmount(tInput.getAmount());
		t.setType(tInput.getType());
		t.setParentId(tInput.getParentId());

		repo.add(t);

		return t;
	}
}
