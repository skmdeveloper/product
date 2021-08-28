package com.ominrio.catalog.service.impl;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ominrio.catalog.domain.Category;
import com.ominrio.catalog.domain.Product;
import com.ominrio.catalog.dto.ProductResponse;
import com.ominrio.catalog.repository.CategoryRepository;
import com.ominrio.catalog.repository.ProductRepository;
import com.ominrio.catalog.service.ProductService;

/**
 * @author Santosh
 * @version 1.0
 * @since 28-Aug-2021
 */
@Service
public class ProductServiceImpl implements ProductService {
	/**
     * Logger for the class
     */
    public static final Logger LOGGER = LoggerFactory.getLogger(ProductServiceImpl.class);

	@Autowired
	private ProductRepository repository;

	@Autowired
	private CategoryRepository categoryRepository;

	@Override
	public Product create(Product product) throws Exception {
		LOGGER.info("In create Product method with request Product ::::: " + product);
		if (product != null) {

			if (product.getProductId() != null)
				throw new Exception("Product id is auto generated and can not be specified explicitly");

			if (product.getProductName() == null)
				throw new Exception("Product name can not be null");

			return repository.save(product);
		} else
			throw new Exception("Product is required!");

	}

	@Override
	public ProductResponse getById(String id) throws Exception {
		LOGGER.info("In get Product By Id method with request id ::::: " + id);
		if (id != null) {

			Optional<Product> product = repository.findById(id);

			if (product.isPresent()) {
				Product existingProduct = product.get();
				ProductResponse response = new ProductResponse();
				response.setProductID(existingProduct.getProductId());
				response.setProductName(existingProduct.getProductName());
				if (existingProduct.getCategory() != null) {
					response.setCategoryId(existingProduct.getCategory().getCategoryId());
					response.setCategryName(existingProduct.getCategory().getCategoryName());
					response.setProductAttributes(existingProduct.getCategory().getProductAttributes());
				}
				return response;
			} else
				throw new Exception("Product with id " + id + " not found");
		} else
			throw new Exception("Product id is required!");
	}

	@Override
	public ProductResponse addCategory(Category category, String id) throws Exception {
		LOGGER.info("In add Category method with request Category ::::: " + category);
		if (category != null && category.getCategoryId() != null) {
			Optional<Product> product = repository.findById(id);

			if (product.isPresent()) {
				product.get().setCategory(category);
				Product updatedProduct = repository.save(product.get());
				ProductResponse response = new ProductResponse();
				response.setProductID(updatedProduct.getProductId());
				response.setProductName(updatedProduct.getProductName());
				if (updatedProduct.getCategory() != null) {

					Optional<Category> existingCategory = categoryRepository
							.findById(updatedProduct.getCategory().getCategoryId());
					if (existingCategory.isPresent()) {
						response.setCategoryId(existingCategory.get().getCategoryId());
						response.setCategryName(existingCategory.get().getCategoryName());
						response.setProductAttributes(existingCategory.get().getProductAttributes());
					}
				}
				return response;
			} else
				throw new Exception("Product with id " + id + " not found");

		} else
			throw new Exception("Category required!");

	}

}
