package project.capstone.Mapper;

import project.capstone.DTO.BooksDTO;
import project.capstone.EntityModels.Books;

public class BooksMapper {
	public static BooksDTO mapToBooksDTO(Books books)
	{
		return new BooksDTO(
				books.getBookId(),
				books.getBookName(),
				books.getBookISBNNo(),
				books.getBookAuthor(),
				books.getBookPublisher(),
				books.getBookDescription(),
				books.getBookcategory(),
				books.getBookStatus(),
				books.getBookPrice(),
				books.getBookEdition(),
				books.getBookLocation(),
				books.getBookImageUrl(),
				books.getRentId()
				);
	}
	
	public static Books mapToBooks(BooksDTO booksDTO)
	{
		return new Books(
				booksDTO.getBookId(),
				booksDTO.getBookName(),
				booksDTO.getBookISBNNo(),
				booksDTO.getBookAuthor(),
				booksDTO.getBookPublisher(),
				booksDTO.getBookDescription(),
				booksDTO.getBookCategory(),
				booksDTO.getBookStatus(),
				booksDTO.getBookPrice(),
				booksDTO.getBookEdition(),
				booksDTO.getBookLocation(),
				booksDTO.getBookImageUrl(),
				booksDTO.getRentId()
				);
				
	}
}
