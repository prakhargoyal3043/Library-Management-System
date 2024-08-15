package project.capstone.Services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import project.capstone.DTO.BooksDTO;

/**
 * Service interface for managing books.
 */
@Service
@Transactional
public interface BooksServices {
	
	/**
     * Adds a new book.
     * @param booksDTO The BooksDTO object containing details of the book to be added.
     * @return The BooksDTO object representing the added book.
     */
	BooksDTO addNewBooks(BooksDTO booksDTO);

	/**
     * Deletes a book by ID.
     * @param bookId The ID of the book to delete.
     */
	void deleteByBookId(String bookId);

	/**
     * Updates an existing book.
     * @param bookId The ID of the book to update.
     * @param booksDTO The BooksDTO object containing updated details.
     * @return The updated BooksDTO object.
     */
	BooksDTO updateBook(String bookId, BooksDTO booksDTO);
	
	/**
     * Updates the status and rent ID of a book.
     * @param bookId The ID of the book to update status.
     * @param status The new status of the book.
     * @param rentId The rent ID associated with the book (null if not rented).
     */
	void updateBookStatus(String bookId, Integer status, Integer rentId);

	/**
     * Retrieves all books.
     * @return List of BooksDTO objects representing all books.
     */
	List<BooksDTO> getAllBooks();

	/**
     * Retrieves a book by ID.
     * @param bookId The ID of the book to retrieve.
     * @return The BooksDTO object representing the retrieved book.
     */
	BooksDTO getBookByID(String bookId);
	
	/**
     * Bulk uploads a list of books.
     * @param booksDTOs The list of BooksDTO objects to be uploaded.
     * @return List of BooksDTO objects representing the added books.
     */
	List<BooksDTO> bulkUpload(List<BooksDTO> booksDTOs);
	
	/**
     * Retrieves all categories of books.
     * @return List of category names.
     */
	List<String> getAllCategories();

}
