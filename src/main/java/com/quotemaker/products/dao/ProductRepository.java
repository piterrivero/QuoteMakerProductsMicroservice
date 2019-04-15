package com.quotemaker.products.dao;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.quotemaker.products.model.Products;

@Repository
public interface ProductRepository extends MongoRepository<Products, Long> {
	
	List<Products> findAll(Sort sortByNameAtAsc);
	
	Products findById(long id);
		
}
