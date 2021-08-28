package com.ominrio.catalog.service;

import java.util.List;
import com.ominrio.catalog.domain.Attribute;
import com.ominrio.catalog.domain.Category;
/**
@author Santosh
* @version 1.0
* @since  28-Aug-2021
*/
public interface CategoryService {

	Category createCategory(Category category) throws Exception;

    Category getById(Integer id) throws Exception;

    Category addAttributes(List<Attribute> attributeList,Integer id) throws Exception;
}
