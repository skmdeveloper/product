package com.ominrio.catalog.controller;

import java.util.ArrayList;
import java.util.List;

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

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.ominrio.catalog.domain.Attribute;
import com.ominrio.catalog.domain.Category;
import com.ominrio.catalog.service.CategoryService;

@RestController
@RequestMapping("/category")
public class CategoryController {


    /**
     * Logger for the class
     */
    public static final Logger LOGGER = LoggerFactory.getLogger(CategoryController.class);


    @Autowired
    private CategoryService categoryService;


    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private Gson gson;


    @RequestMapping(method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = {
            MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<?> create(@RequestBody String request) throws Exception {
        try {
        
            Category category = gson.fromJson(request, Category.class);
            return ResponseEntity.ok(mapper.writeValueAsString(categoryService.createCategory(category)));
        }
        catch (Exception ex){
            return  ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.GET, produces = {
            MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<?> get(@PathVariable(name = "id", required = true) int id) throws Exception {
        try{
            LOGGER.info("Category get request with  ::::: "+id);

            return ResponseEntity.ok(mapper.writeValueAsString(categoryService.getById(id)));
        }
        catch (Exception ex)
        {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }

    }


    @RequestMapping(value = "/{id}/attribute",method = RequestMethod.PUT)
    public ResponseEntity<?> addAttributes(@PathVariable(name = "id",required = true) int id
            , @RequestBody String request) throws Exception {
        try {
            LOGGER.info("Category Create Request Body ::::: "+request);

            List<Attribute> attributeList = gson.fromJson(request, ArrayList.class);

            return ResponseEntity.ok(mapper.writeValueAsString(categoryService.addAttributes(attributeList,id)));
        }
        catch (Exception ex){
            return  ResponseEntity.badRequest().body(ex.getMessage());
        }
    }


}
