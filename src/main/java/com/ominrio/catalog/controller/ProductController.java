package com.ominrio.catalog.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.ominrio.catalog.domain.Category;
import com.ominrio.catalog.domain.Product;
import com.ominrio.catalog.service.ProductService;

@RestController
@RequestMapping("/product")
public class ProductController {

	/**
	 * Logger for the class
	 */
	public static final Logger LOGGER = LoggerFactory.getLogger(ProductController.class);

	@Autowired
	private ProductService productService;

	@Autowired
	private ObjectMapper mapper;

	@Autowired
	private Gson gson;

	@RequestMapping(name = "/", method = RequestMethod.POST, consumes = {
			MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<?> create(@RequestBody String request) throws JsonProcessingException {

		try {

			Product product = gson.fromJson(request, Product.class);

			return ResponseEntity.ok(mapper.writeValueAsString(productService.create(product)));
		} catch (Exception ex) {
			return ResponseEntity.badRequest().body(ex.getMessage());
		}
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<?> get(@PathVariable(name = "id", required = true) String id) throws JsonProcessingException {
		try {

			return ResponseEntity.ok(mapper.writeValueAsString(productService.getById(id)));
		} catch (Exception ex) {
			return ResponseEntity.badRequest().body(ex.getMessage());
		}
	}

	@RequestMapping(value = "{id}/category", method = RequestMethod.PUT, consumes = {
			MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<?> addAttributes(@PathVariable(name = "id", required = true) String id,
			@RequestBody String request) throws Exception {
		try {

			Category category = gson.fromJson(request, Category.class);

			return ResponseEntity.ok(mapper.writeValueAsString(productService.addCategory(category, id)));
		} catch (Exception ex) {
			return ResponseEntity.badRequest().body(ex.getMessage());
		}
	}

}
