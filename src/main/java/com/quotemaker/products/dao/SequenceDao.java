package com.quotemaker.products.dao;

import org.springframework.stereotype.Repository;

@Repository
public interface SequenceDao {

	long getNextSequenceId(String key);
	
}
