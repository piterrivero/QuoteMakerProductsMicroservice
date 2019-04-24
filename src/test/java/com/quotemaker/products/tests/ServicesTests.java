package com.quotemaker.products.tests;

import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

import com.quotemaker.products.dao.ProductRepository;
import com.quotemaker.products.model.Products;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ServicesTests {

	@Autowired
	ProductRepository productRepository;
	
	@Test
	public void listTest() {
		List<Products> list = productRepository.findAll(new Sort(Sort.Direction.ASC, "name"));
		assertNotNull(list);
	}
	
}
