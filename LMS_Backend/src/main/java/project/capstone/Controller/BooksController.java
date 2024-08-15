package project.capstone.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import project.capstone.DTO.BooksDTO;
import project.capstone.Services.BooksServices;

/**
 * Controller class for managing operations related to books.
 */

@AllArgsConstructor
@RestController
@RequestMapping("/books")
@CrossOrigin
public class BooksController {
	
	@Autowired
	private BooksServices booksServices;
	
	 /**
     * Endpoint to add a new book.
     * @param booksDTO The DTO object representing the book to add.
     * @return ResponseEntity containing the saved BooksDTO with HTTP status CREATED.
     */
	@PostMapping
	public ResponseEntity<BooksDTO> addNewBooks(@RequestBody BooksDTO booksDTO)
	{
		BooksDTO savedBooksDTO = booksServices.addNewBooks(booksDTO);
		return new ResponseEntity<BooksDTO>(savedBooksDTO, HttpStatus.CREATED);
	}
	
	/**
     * Endpoint to perform bulk upload of books.
     * @param booksDTOs List of BooksDTO objects to upload.
     * @return ResponseEntity containing the list of saved BooksDTOs with HTTP status CREATED.
     */
	@PostMapping("/bulkupload")
	public ResponseEntity<List<BooksDTO>> bulkUpload(@RequestBody List<BooksDTO> booksDTOs)
	{
		List<BooksDTO> savedBooksDTOs = booksServices.bulkUpload(booksDTOs);
		return new ResponseEntity<List<BooksDTO>>(savedBooksDTOs, HttpStatus.CREATED);
	}
	
	/**
     * Endpoint to retrieve all books.
     * @return ResponseEntity containing the list of BooksDTOs with HTTP status OK.
     */
	@GetMapping
	public ResponseEntity<List<BooksDTO>> getAllBooks()
	{
		List<BooksDTO> booksDTOs = booksServices.getAllBooks();
		return ResponseEntity.ok(booksDTOs);
	}
	
	 /**
     * Endpoint to find a book by its ID.
     * @param bookId The ID of the book to find.
     * @return ResponseEntity containing the found BooksDTO with HTTP status OK.
     */
	@GetMapping("/find/{id}")
	public ResponseEntity<BooksDTO> findBookById(@PathVariable("id") String bookId)
	{
		BooksDTO booksDTO = booksServices.getBookByID(bookId);
		return ResponseEntity.ok(booksDTO);	
	}
	
	 /**
     * Endpoint to update a book by its ID.
     * @param bookId The ID of the book to update.
     * @param booksDTO The updated information in BooksDTO format.
     * @return ResponseEntity containing the updated BooksDTO with HTTP status OK.
     */
	@PutMapping("/update/{id}")
	public ResponseEntity<BooksDTO> updateBooks(@PathVariable("id") String bookId, @RequestBody BooksDTO booksDTO)
	{
		BooksDTO booksDTO2 = booksServices.updateBook(bookId, booksDTO);
		return ResponseEntity.ok(booksDTO2);
	}
	
	/**
     * Endpoint to delete a book by its ID.
     * @param bookId The ID of the book to delete.
     * @return ResponseEntity with a success message and HTTP status OK.
     */
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteBookById(@PathVariable("id") String bookId)
	{
		booksServices.deleteByBookId(bookId);
		return ResponseEntity.ok("Book Deleted Successfully.");
	}

	 /**
     * Endpoint to retrieve all categories of books.
     * @return ResponseEntity containing the list of categories with HTTP status OK.
     */
	@GetMapping("/categories")
	public ResponseEntity<List<String>> getAllCategories()
	{
		List<String> categories = booksServices.getAllCategories();
		
		return new ResponseEntity<List<String>>(categories, HttpStatus.OK);
	}
}
