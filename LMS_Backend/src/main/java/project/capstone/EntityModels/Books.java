package project.capstone.EntityModels;

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
@Table(name = "Books")
public class Books {

	@Id
	@Column(name = "Book_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer bookId;

	@Column(name = "Book_Name")
	private String bookName;

	@Column(name = "Book_ISBN_No")
	private String bookISBNNo;

	@Column(name = "Book_Author")
	private String bookAuthor;

	@Column(name = "Book_Publisher")
	private String bookPublisher;

	@Column(name = "Book_Description")
	private String bookDescription;

	@Column(name = "Book_Category")
	private String bookcategory;

	@Column(name = "Book_Status")
	private Integer bookStatus;

	@Column(name = "Book_Price")
	private Integer bookPrice;

	@Column(name = "Book_Edition")
	private String bookEdition;

	@Column(name = "Book_Location")
	private Integer bookLocation;

	@Column(name = "Book_Image_Url")
	private String bookImageUrl;
	
	@Column(name = "Rent_Id")
	private Integer rentId;

	
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public void setBookISBNNo(String bookISBNNo) {
		this.bookISBNNo = bookISBNNo;
	}

	public void setBookAuthor(String bookAuthor) {
		this.bookAuthor = bookAuthor;
	}

	public void setBookPublisher(String bookPublisher) {
		this.bookPublisher = bookPublisher;
	}

	public void setBookDescription(String bookDescription) {
		this.bookDescription = bookDescription;
	}

	public void setBookcategory(String bookcategory) {
		this.bookcategory = bookcategory;
	}

	public void setBookStatus(Integer bookStatus) {
		this.bookStatus = bookStatus;
	}

	public void setBookPrice(Integer bookPrice) {
		this.bookPrice = bookPrice;
	}

	public void setBookEdition(String bookEdition) {
		this.bookEdition = bookEdition;
	}

	public void setBookLocation(Integer bookLocation) {
		this.bookLocation = bookLocation;
	}

	public void setBookImageUrl(String bookImageUrl) {
		this.bookImageUrl = bookImageUrl;
	}

	public void setRentId(Integer rentId) {
		this.rentId = rentId;
	}
	

}
