package com.effigo.LearningPortal.dto.request;

import java.io.Serializable;

import com.effigo.LearningPortal.entity.UserEntity.UserType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserEntityrequest implements Serializable {
	private Long uId;
	private String username;
	private String password;
	private UserType userType;

}
