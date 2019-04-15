package com.quotemaker.products.dao;

import org.springframework.stereotype.Repository;

import com.quotemaker.products.exceptions.SequenceException;

@Repository
public interface SequenceDao {

	long getNextSequenceId(String key) throws SequenceException;
	
}
