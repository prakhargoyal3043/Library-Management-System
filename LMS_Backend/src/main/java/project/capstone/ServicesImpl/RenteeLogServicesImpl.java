package project.capstone.ServicesImpl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.AllArgsConstructor;
import project.capstone.DTO.BooksDTO;
import project.capstone.DTO.RenteeLogDTO;
import project.capstone.EntityModels.RenteeLog;
import project.capstone.Exception.ResourceNotFoundException;
import project.capstone.Mapper.RenteeLogMapper;
import project.capstone.Repository.RenteeLogRepository;
import project.capstone.Services.BooksServices;
import project.capstone.Services.RenteeLogServices;

/**
 * Implementation of RenteeLogServices interface providing operations related to rentee logs.
 */
@Service
@AllArgsConstructor
@Transactional
public class RenteeLogServicesImpl implements RenteeLogServices {

	@Autowired
	private RenteeLogRepository renteeLogRepository;
	@Autowired
	private BooksServices booksServices;

	/**
     * Records a new rentee log entry.
     * @param renteeLogDTO The RenteeLogDTO object containing details of the rentee log.
     * @return The rent ID of the newly recorded rentee log.
     */
	@Override
	public Integer enterRenteeLog(RenteeLogDTO renteeLogDTO) {
		// TODO Auto-generated method stub
		RenteeLog renteeLog = RenteeLogMapper.mapToRenteeLog(renteeLogDTO);
		RenteeLog renteeLog2 =  renteeLogRepository.save(renteeLog);
		String bookId = Integer.toString(renteeLog.getBookId());
		booksServices.updateBookStatus(bookId, 0, renteeLog.getRentId());
		return renteeLog2.getRentId();

	}

	/**
     * Updates an existing rentee log entry.
     * @param rentId The ID of the rentee log entry to update.
     * @param renteeLogDTO The RenteeLogDTO object containing updated details.
     * @return The updated RenteeLogDTO object.
     * @throws ResourceNotFoundException if no rentee log with the specified ID exists.
     */
	@Override
	public RenteeLogDTO updateRenteeLog(String rentId, RenteeLogDTO renteeLogDTO) {
	    RenteeLog renteeLog = renteeLogRepository.findById(rentId)
	            .orElseThrow(() -> new ResourceNotFoundException("No such entry"));

	    renteeLog.setReturnDate(renteeLogDTO.getReturnDate());

	    if (renteeLogDTO.getTotalAmount() != null) {
	        renteeLog.setTotalAmount(renteeLogDTO.getTotalAmount());
	    }

	    RenteeLog updatedLog = renteeLogRepository.save(renteeLog);

	    Integer bookId = updatedLog.getBookId();
	    if (bookId != null) {
	        String bookIdString = String.valueOf(bookId);
	        booksServices.updateBookStatus(bookIdString, 1, null);
	    } else {
	        // Handle case where bookId is null
	        // You might throw an exception or log an error depending on your application's logic
	        throw new ResourceNotFoundException("Book ID is null for the updated rentee log.");
	    }

	    return RenteeLogMapper.mapToRenteeLogDTO(updatedLog);
	}
	
	/**
     * Retrieves all rentee logs.
     * @return List of RenteeLogDTO objects representing all rentee logs.
     */
	@Override
	@Transactional(readOnly = true)
	public List<RenteeLogDTO> viewRentee() {
		// TODO Auto-generated method stub
		List<RenteeLog> renteeLog = renteeLogRepository.findAll();

		return renteeLog.stream().map((log) -> RenteeLogMapper.mapToRenteeLogDTO(log)).collect(Collectors.toList());
		
	}

	 /**
     * Retrieves rentee logs for a specific book.
     * @param bookId The ID of the book for which rentee logs are to be retrieved.
     * @return List of RenteeLogDTO objects representing rentee logs for the specified book.
     * @throws ResourceNotFoundException if no rentee logs are found for the specified book.
     */
	@Override
    @Transactional(readOnly = true)
    public List<RenteeLogDTO> viewRenteeByBookId(String bookId) {
        BooksDTO booksDTO = booksServices.getBookByID(bookId);
        
        if (booksDTO == null || booksDTO.getRentId() == null) {
            throw new ResourceNotFoundException("This book is not rented.");
        }

        Integer rentId = booksDTO.getRentId();
        
        Optional<RenteeLog> renteeLogOptional = renteeLogRepository.findById(Integer.toString(rentId));
        
        if (renteeLogOptional.isPresent()) {
            RenteeLog renteeLog = renteeLogOptional.get();
            List<RenteeLogDTO> renteeLogDTOs = new ArrayList<>();
            renteeLogDTOs.add(RenteeLogMapper.mapToRenteeLogDTO(renteeLog));
            return renteeLogDTOs;
        } else {
            throw new ResourceNotFoundException("No rentee logs found for this book.");
        }
    }
		
	/**
     * Retrieves rentee logs grouped by user and book IDs.
     * @return Map where keys are user IDs and values are lists of book IDs rented by each user.
     */
	@Override
	  public Map<Integer, List<Integer>> getRenteeLogsGroupedByUserAndBookIds() {
	        List<Object[]> results = renteeLogRepository.findRenteeLogs();

	        Map<Integer, List<Integer>> groupedData = new HashMap<>();
	        for (Object[] result : results) {
	            Integer userId = ((BigDecimal) result[0]).intValue();  // Convert BigDecimal to Integer
	            Integer bookId = ((BigDecimal) result[1]).intValue();  // Convert BigDecimal to Integer

	            // If userId already exists in the map, add bookId to the existing list
	            if (groupedData.containsKey(userId)) {
	                groupedData.get(userId).add(bookId);
	            } else {
	                // If userId doesn't exist, create a new list and add bookId
	                List<Integer> bookIds = new ArrayList<>();
	                bookIds.add(bookId);
	                groupedData.put(userId, bookIds);
	            } }
	        
	        return groupedData;
	    }
	
	
}
