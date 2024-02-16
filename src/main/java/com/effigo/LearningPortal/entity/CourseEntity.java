package com.effigo.LearningPortal.entity;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
public class CourseEntity {
	    @Id
	    private Long course_id;
	    private String course_name;
	    @ManyToOne
	    @JoinColumn(name = "category_id",referencedColumnName="category_id")
	    private CategoryEntity category;
	    @ManyToOne
	    @JoinColumn(name = "u_id",referencedColumnName="u_id")
	    private UserEntity u_id;
		@CreationTimestamp
	    @Column(name = "created_on", nullable = false, updatable = false)
	    private LocalDateTime createdOn;
	    @UpdateTimestamp
	    @Column(name = "updated_on", nullable = false)
	    private LocalDateTime updatedOn;

		public Long getCourse_id() {
			return course_id;
		}
		public void setCourse_id(Long course_id) {
			this.course_id = course_id;
		}
		public String getCourse_name() {
			return course_name;
		}
		public void setCourse_name(String course_name) {
			this.course_name = course_name;
		}
		public CategoryEntity getCategory() {
			return category;
		}
		public void setCategory(CategoryEntity category) {
			this.category = category;
		}
		public UserEntity getU_id() {
			return u_id;
		}
		public void setU_id(UserEntity u_id) {
			this.u_id = u_id;
		}
	    public LocalDateTime getCreatedOn() {
	        return createdOn;
	    }

	    public void setCreatedOn(LocalDateTime createdOn) {
	        this.createdOn = createdOn;
	    }
	    @PrePersist
	    public void onCreate() {
	        this.createdOn = LocalDateTime.now();
	    }
	    public String getFormattedCreatedOn() {
	    	if (createdOn == null) {
	            return "";
	        }
	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm");
	        return updatedOn.format(formatter);
	    }
	    public String getFormattedUpdatedOn() {
	    	if (updatedOn == null) {
	            return "";
	        }
	        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm");
	        return updatedOn.format(formatter);
	    }
	    
}
