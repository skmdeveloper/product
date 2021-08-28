package com.ominrio.catalog.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.stereotype.Indexed;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Indexed
@Entity
@Table(name = "attribute")
public class Attribute {
	 @Id
	    @GeneratedValue(generator = "UUID")
	    @GenericGenerator(
	            name = "UUID",
	            strategy = "org.hibernate.id.UUIDGenerator"
	    )
	    @Column(name = "id",updatable = true, nullable = false)
    private String id;
    @NotNull
    private String attributeName;
    @NotNull
    private String attributeValue;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;


    public Attribute() {
    }




	public String getId() {
		return id;
	}




	public void setId(String id) {
		this.id = id;
	}




	public String getAttributeName() {
		return attributeName;
	}


	public void setAttributeName(String attributeName) {
		this.attributeName = attributeName;
	}


	public String getAttributeValue() {
		return attributeValue;
	}


	public void setAttributeValue(String attributeValue) {
		this.attributeValue = attributeValue;
	}


	public Category getCategory() {
		return category;
	}


	public void setCategory(Category category) {
		this.category = category;
	}

	@Override
	public String toString() {
		return "Attribute [id=" + id + ", attributeName=" + attributeName + ", attributeValue=" + attributeValue
				+ ", category=" + category + "]";
	}


}
