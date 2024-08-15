package project.capstone.ServicesImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.AllArgsConstructor;
import project.capstone.DTO.BooksDTO;
import project.capstone.EntityModels.Books;
import project.capstone.EntityModels.Category;
import project.capstone.Exception.ResourceNotFoundException;
import project.capstone.Mapper.BooksMapper;
import project.capstone.Repository.BooksRepository;
import project.capstone.Repository.CategoryRepository;
import project.capstone.Services.BooksServices;

/**
 * Implementation of BooksServices interface providing CRUD operations for books.
 */
@Service
@AllArgsConstructor
@Transactional
public class BooksServicesImpl implements BooksServices {

	@Autowired
	private BooksRepository booksRepository;
	@Autowired
	private CategoryRepository categoryRepository;

	/**
     * Adds a new book to the system.
     * @param booksDTO The BooksDTO object representing the book to be added.
     * @return BooksDTO object of the saved book.
     * @throws ResourceNotFoundException if the specified category does not exist.
     */
	@Override
	public BooksDTO addNewBooks(BooksDTO booksDTO) {
		// TODO Auto-generated method stub
		Books books = BooksMapper.mapToBooks(booksDTO);
		books.setBookStatus(1);
		Integer location = null;
		Optional<Category> category = categoryRepository.findById(books.getBookcategory());
		if(category.isPresent())
		{
			location = category.get().getBookLocation();
		}
		else {
			throw new ResourceNotFoundException("No such category found");
		}
		books.setBookLocation(location);
		books.setRentId(null);
		Books savedBooks = booksRepository.save(books);
		return BooksMapper.mapToBooksDTO(savedBooks);
	}

	/**
     * Deletes a book by its ID.
     * @param bookId The ID of the book to delete.
     * @throws ResourceNotFoundException if no book with the specified ID exists.
     */
	@Override
	public void deleteByBookId(String bookId) {
		// TODO Auto-generated method stub
		booksRepository.findById(bookId)
				.orElseThrow(() -> new ResourceNotFoundException("No such book found"));
		booksRepository.deleteById(bookId);
	}

	 /**
     * Updates an existing book's details.
     * @param bookId The ID of the book to update.
     * @param booksDTO The updated BooksDTO object.
     * @return BooksDTO object of the updated book.
     * @throws ResourceNotFoundException if no book with the specified ID exists.
     */
	@Override
	public BooksDTO updateBook(String bookId, BooksDTO booksDTO) {
		// TODO Auto-generated method stub
		Books books = booksRepository.findById(bookId)
				.orElseThrow(() -> new ResourceNotFoundException("No such book found"));

		books.setBookAuthor(booksDTO.getBookAuthor());
		books.setBookcategory(booksDTO.getBookCategory());
		books.setBookDescription(booksDTO.getBookDescription());
		books.setBookEdition(booksDTO.getBookEdition());
		books.setBookISBNNo(booksDTO.getBookISBNNo());
		books.setBookLocation(booksDTO.getBookLocation());
		books.setBookName(booksDTO.getBookName());
		books.setBookPrice(booksDTO.getBookPrice());
		books.setBookPublisher(booksDTO.getBookPublisher());
		books.setBookStatus(booksDTO.getBookStatus());
		books.setBookImageUrl(booksDTO.getBookImageUrl());

		Books savedBooks = booksRepository.save(books);
		return BooksMapper.mapToBooksDTO(savedBooks);

	}

	 /**
     * Retrieves all books from the system.
     * @return List of BooksDTO objects representing all books.
     */
	@Override
	@Transactional(readOnly = true)
	public List<BooksDTO> getAllBooks() {
		// TODO Auto-generated method stub
		List<Books> books = booksRepository.findAll();

		return books.stream().map((book) -> BooksMapper.mapToBooksDTO(book)).collect(Collectors.toList());
	}

	 /**
     * Retrieves a book by its ID.
     * @param bookId The ID of the book to retrieve.
     * @return BooksDTO object of the requested book.
     * @throws ResourceNotFoundException if no book with the specified ID exists.
     */
	@Override
	@Transactional(readOnly = true)
	public BooksDTO getBookByID(String bookId) {
		// TODO Auto-generated method stub
		Books books = booksRepository.findById(bookId)
				.orElseThrow(() -> new ResourceNotFoundException("No such book found"));
		return BooksMapper.mapToBooksDTO(books);
	}

	/**
     * Performs bulk upload of books into the system.
     * @param booksDTOs List of BooksDTO objects representing books to be uploaded.
     * @return List of BooksDTO objects representing the saved books.
     * @throws ResourceNotFoundException if the specified category for any book does not exist.
     */
	@Override
	public List<BooksDTO> bulkUpload(List<BooksDTO> booksDTOs) {
	    List<BooksDTO> booksDTOs2 = new ArrayList<>();
	    int n = booksDTOs.size();
	    for (int i = 0; i < n; i++) {
	        Books books = BooksMapper.mapToBooks(booksDTOs.get(i));
	        books.setBookStatus(1);
	        books.setRentId(null);
	        Integer location = null;
	        Optional<Category> category = categoryRepository.findById(books.getBookcategory());
	        if (category.isPresent()) {
	            location = category.get().getBookLocation();
	        } else {
	            throw new ResourceNotFoundException("No such category found");
	        }
	        books.setBookLocation(location);
	        // Save the book and check if savedBooks is not null before mapping to DTO
	        Books savedBooks = booksRepository.save(books);
	        
	        booksDTOs2.add(BooksMapper.mapToBooksDTO(savedBooks));
	    }
	    return booksDTOs2;
	}


	/**
     * Updates the status and rent ID of a book.
     * @param bookId The ID of the book to update.
     * @param status The new status of the book.
     * @param rentId The ID of the rent associated with the book (null if not rented).
     * @throws ResourceNotFoundException if no book with the specified ID exists.
     */
	@Override
	public void updateBookStatus(String bookId, Integer status, Integer rentId) {
		// TODO Auto-generated method stub
		Books books = booksRepository.findById(bookId)
				.orElseThrow(() -> new ResourceNotFoundException("No such book found"));

		books.setBookStatus(status);
		books.setRentId(rentId);

		booksRepository.save(books);

	}

	/**
     * Retrieves all book categories.
     * @return List of Strings representing all categories.
     */
	@Override
	public List<String> getAllCategories() {
		// TODO Auto-generated method stub
		List<String> categories = new ArrayList<>();
		List<Category> c = categoryRepository.findAll();
		for(int i=0;i<c.size();i++)
		{
			categories.add(c.get(i).getBookCategory());
		}
		return categories;
	}
}
