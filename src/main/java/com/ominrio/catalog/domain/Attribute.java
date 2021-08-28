package com.ominrio.catalog.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.stereotype.Indexed;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Indexed
@Entity
@Table(name = "attribute")
public class Attribute {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long attributeId;
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


	public Attribute(Long attributeId, @NotNull String attributeName, @NotNull String attributeValue,
			Category category) {
		super();
		this.attributeId = attributeId;
		this.attributeName = attributeName;
		this.attributeValue = attributeValue;
		this.category = category;
	}


	public Long getAttributeId() {
		return attributeId;
	}


	public void setAttributeId(Long attributeId) {
		this.attributeId = attributeId;
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
		return "Attribute [attributeId=" + attributeId + ", attributeName=" + attributeName + ", attributeValue="
				+ attributeValue + ", category=" + category + "]";
	}

    
}
