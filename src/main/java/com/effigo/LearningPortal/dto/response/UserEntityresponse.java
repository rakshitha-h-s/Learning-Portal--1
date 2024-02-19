package com.effigo.LearningPortal.dto.response;

import java.io.Serializable;

import com.effigo.LearningPortal.entity.UserEntity.UserType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserEntityresponse implements Serializable {
	private Long uId;
	private String username;
	private String password;

	public Long getuId() {
		return uId;
	}

	public void setuId(Long uId) {
		this.uId = uId;
	}

	private UserType userType;

}
