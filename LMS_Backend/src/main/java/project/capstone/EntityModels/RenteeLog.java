package project.capstone.EntityModels;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Rentee_log")
public class RenteeLog {
	

	@Id
	@Column(name = "Rent_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer rentId;
	
	@Column(name = "User_Id")
	private Integer userId;
	
	@Column(name = "Book_Id")
	private Integer bookId;
	
	@Column(name = "Issue_Date")
	private Date issueDate;
	
	@Column(name = "Due_Date")
	private Date dueDate;
	
	@Column(name = "Total_Amount")
	private Integer totalAmount;
	
	@Column(name = "Amount_Paid")
	private Integer amountPaid;
	
	@Column(name = "Return_Date")
	private Date returnDate;
	


	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public void setBookId(Integer bookId) {
		this.bookId = bookId;
	}

	public void setIssueDate(Date issueDate) {
		this.issueDate = issueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public void setTotalAmount(Integer totalAmount) {
		this.totalAmount = totalAmount;
	}

	public void setAmountPaid(Integer amountPaid) {
		this.amountPaid = amountPaid;
	}

	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}

}
