package com.ominrio.catalog.service;

import com.ominrio.catalog.domain.Category;
import com.ominrio.catalog.domain.Product;
import com.ominrio.catalog.dto.ProductResponse;
/**
@author Santosh
* @version 1.0
* @since  28-Aug-2021
*/
public interface ProductService {
	
	Product create(Product product) throws Exception;

	ProductResponse getById(String id) throws Exception;

	ProductResponse addCategory(Category category, String id) throws Exception;

}
