package com.effigo.LearningPortal.dto.response;

import java.io.Serializable;

import lombok.Data;
@Data
public class CategoryEntityresponse implements Serializable{
	private Long category_id;
	private String name;
	public Long getCategory_id() {
		return category_id;
	}
	public void setCategory_id(Long category_id) {
		this.category_id = category_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public CategoryEntityresponse() {
		super();
	}

}
