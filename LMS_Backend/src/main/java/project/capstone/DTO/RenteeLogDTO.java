package project.capstone.DTO;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RenteeLogDTO {
	private Integer rentId;
	private Integer userId;
	private Integer bookId;
	private Date issueDate;
	private Date dueDate;
	private Integer totalAmount;
	private Integer amountPaid;
	private Date returnDate;

}
