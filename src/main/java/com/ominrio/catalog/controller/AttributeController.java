package com.ominrio.catalog.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.ominrio.catalog.domain.Attribute;
import com.ominrio.catalog.service.AttributeService;

@RestController

@RequestMapping("/attribute")
public class AttributeController {

	/**
	 * Logger for the class
	 */
	public static final Logger LOGGER = LoggerFactory.getLogger(AttributeController.class);

	@Autowired
	private AttributeService attributeService;

	@Autowired
	private ObjectMapper mapper;

	@Autowired
	private Gson gson;

	@RequestMapping(method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<?> create(@RequestBody String request) throws Exception {
		try {
			
			Attribute attribute = gson.fromJson(request, Attribute.class);
			return ResponseEntity.ok(mapper.writeValueAsString(attributeService.createAttribute(attribute)));
			
		} catch (Exception ex) {
			return ResponseEntity.badRequest().body(ex.getMessage());
		}

	}

}
