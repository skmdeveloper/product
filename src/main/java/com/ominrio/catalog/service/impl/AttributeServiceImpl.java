package com.ominrio.catalog.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ominrio.catalog.controller.AttributeController;
import com.ominrio.catalog.domain.Attribute;
import com.ominrio.catalog.repository.AttributeRepository;
import com.ominrio.catalog.service.AttributeService;

/**
 * @author Santosh
 * @version 1.0
 * @since 28-Aug-2021
 */
@Service
public class AttributeServiceImpl implements AttributeService {
	
	/**
	 * Logger for the class
	 */
	public static final Logger LOGGER = LoggerFactory.getLogger(AttributeServiceImpl.class);

	@Autowired
	private AttributeRepository repository;

	@Override
	public Attribute createAttribute(Attribute attribute) throws Exception {
		LOGGER.info("In create attribute method with request Attribute ::::: " + attribute);

		if (attribute.getAttributeId() != null)
			throw new Exception("Attribute id is auto generated and can not be specified explicitly");

		if (attribute.getAttributeName() == null)
			throw new Exception("Attribute name can not be null");

		if (attribute.getAttributeValue() == null)
			throw new Exception("Attribute value can not be null");

		attribute = repository.save(attribute);

		return attribute;
	}

}
