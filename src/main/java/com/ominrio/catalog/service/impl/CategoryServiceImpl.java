package com.ominrio.catalog.service.impl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.ominrio.catalog.controller.CategoryController;
import com.ominrio.catalog.domain.Attribute;
import com.ominrio.catalog.domain.Category;
import com.ominrio.catalog.repository.CategoryRepository;
import com.ominrio.catalog.service.CategoryService;
/**
@author Santosh
* @version 1.0
* @since  28-Aug-2021
*/
@Service
public class CategoryServiceImpl implements CategoryService {
	/**
     * Logger for the class
     */
    public static final Logger LOGGER = LoggerFactory.getLogger(CategoryServiceImpl.class);

	@Autowired
    private CategoryRepository repository;


    @Override
    public Category createCategory(Category category) throws Exception {
    	LOGGER.info("In create Category method with request Category ::::: " + category);
        if(category!=null) {

            if (category.getCategoryId() != null)
                throw new Exception("Category id is auto generated and can not be specified explicitly");

            if (category.getCategoryName() == null)
                throw new Exception("Category name can not be null");

            return repository.save(category);
        }
        else
            throw new Exception("Category is required!");

    }

    @Override
    public Category getById(Integer id) throws Exception {
        if(id!=null) {
            Optional<Category> category = repository.findById(id);

            if(category.isPresent())
            {
                return  category.get();
            }
            else
                 throw new Exception("Category with id "+id+" not found");
        }
        else
            throw new Exception("Category id is required!");
    }

    @Override
    public Category addAttributes(List<Attribute> attributeList,Integer id) throws Exception {

        if(attributeList!=null && !attributeList.isEmpty()) {
            Optional<Category> category = repository.findById(id);

            if(category.isPresent())
            {

                category.get().setProductAttributes(attributeList);
                return  repository.save(category.get());
            }
            else
                throw new Exception("Category with id "+id+" not found");

        }
        else
            throw new Exception("Attributes required!");
    }


    
}
