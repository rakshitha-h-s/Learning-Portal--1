package com.effigo.LearningPortal.entity;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import lombok.NoArgsConstructor;
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserEntity {
	    @Id
	    private Long u_id;
		private String username;
        private String password;	    
		public Long getU_id() {
			return u_id;
		}

		public void setU_id(Long u_id) {
			this.u_id = u_id;
		}
		@Enumerated(EnumType.STRING)
	    private UserType userType; 
	    @CreationTimestamp
	    @Column(name = "created_on", nullable = false, updatable = false)
	    private LocalDateTime createdOn;
	    public UserType getUserType() {
			return userType;
		}

		public void setUserType(UserType userType) {
			this.userType = userType;
		}
		@UpdateTimestamp
	    @Column(name = "updated_on", nullable = false)
	    private LocalDateTime updatedOn;
		@PrePersist
	    public void onCreate() {
	        this.createdOn = LocalDateTime.now();
	    }
	    public LocalDateTime getCreatedOn() {
	        return createdOn;
	    }

	    public void setCreatedOn(LocalDateTime createdOn) {
	        this.createdOn = createdOn;
	    }

	    // method to get createdOn field in "MM/DD/YYYY HH:MM" format
	    public String getFormattedCreatedOn() {
	    	if (createdOn == null) {
	            return "";
	        }
	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm");
	        return createdOn.format(formatter);
	    }
	    public String getFormattedUpdatedOn() {
	    	if (updatedOn == null) {
	            return "";
	        }
	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm");
	        return updatedOn.format(formatter);
	    }
	public String getUsername() {
			return username;
		}

		public LocalDateTime getUpdatedOn() {
		return updatedOn;
	}

	public void setUpdatedOn(LocalDateTime updatedOn) {
		this.updatedOn = updatedOn;
	}

		public void setUsername(String username) {
			this.username = username;
		}
	public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}
	public enum UserType {
	    ADMIN,
	    AUTHOR,
	    LEARNER
	}

}

