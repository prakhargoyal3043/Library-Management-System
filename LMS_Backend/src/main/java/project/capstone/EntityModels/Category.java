package project.capstone.EntityModels;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter

@NoArgsConstructor

@Entity
@Table(name = "Book_Category")
public class Category {
	
	@Id
	@Column(name = "Book_Category")
	private String bookCategory;
	
	@Column(name = "Book_Location")
	private Integer bookLocation;

	
}
