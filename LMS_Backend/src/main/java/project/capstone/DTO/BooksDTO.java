package project.capstone.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BooksDTO {
	private Integer bookId;
	private String bookName;
	private String bookISBNNo;
	private String bookAuthor;
	private String bookPublisher;
	private String bookDescription;
	private String bookCategory;
	private Integer bookStatus;
	private Integer bookPrice;
	private String bookEdition;
	private Integer bookLocation;
	private String bookImageUrl;
	private Integer rentId;

}
