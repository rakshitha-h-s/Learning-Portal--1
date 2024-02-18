package com.effigo.LearningPortal.entity;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
public class UserCourseEnrollmentEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long enrollmentId;
	@ManyToOne
	@JoinColumn(name = "uId",referencedColumnName="uId")
	private UserEntity uId;
	@ManyToOne
	@JoinColumn(name = "courseId",referencedColumnName="courseId")
	private CourseEntity courseId;
	
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

