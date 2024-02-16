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
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class UserEntity {
	    @Id
	    private Long u_id;
		private String username;
        private String password;	    
		
		@Enumerated(EnumType.STRING)
	    private UserType userType; 
	    @CreationTimestamp
	    @Column(name = "created_on", nullable = false, updatable = false)
	    private LocalDateTime createdOn;
		@UpdateTimestamp
	    @Column(name = "updated_on", nullable = false)
	    private LocalDateTime updatedOn;
		@PrePersist
	    public void onCreate() {
	        this.createdOn = LocalDateTime.now();
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

	public enum UserType {
	    ADMIN,
	    AUTHOR,
	    LEARNER
	}

}

