package com.ominrio.catalog.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ominrio.catalog.domain.Product;
/**
@author Santosh
* @version 1.0
* @since  28-Aug-2021
*/
@Repository
public interface ProductRepository extends CrudRepository<Product, String> {
}
