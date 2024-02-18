package com.effigo.LearningPortal.dto.response;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryEntityresponse implements Serializable{
	private Long categoryId;
	private String name;
}
