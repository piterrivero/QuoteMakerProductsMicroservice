package com.quotemaker.products.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.quotemaker.products.model.Products;
import com.quotemaker.products.model.SequenceId;

@Repository
public interface SequenceRepository extends MongoRepository<SequenceId, Long> {

		
}
