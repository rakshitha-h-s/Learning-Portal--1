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
public class CategoryEntity {
	@Id
	private Long categoryId;
	@Enumerated(EnumType.STRING)
    private CategoryType categoryType; 
	@CreationTimestamp
    @Column(name = "created_on", nullable = false, updatable = false)
    private LocalDateTime createdOn;
    @UpdateTimestamp
    @Column(name = "updated_on", nullable = false)
    private LocalDateTime updatedOn;
	public CategoryType getCategoryType() {
		return categoryType;
	}
	@PrePersist
    public void onCreate() {
        this.createdOn = LocalDateTime.now();
    }

    public void setCreatedOn(LocalDateTime createdOn) {
        this.createdOn = createdOn;
    }
	public enum CategoryType {
    JAVA,
    SQL,
    OOPS,
    SPA
}
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
}
