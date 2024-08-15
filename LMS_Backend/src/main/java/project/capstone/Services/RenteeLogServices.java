package project.capstone.Services;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import project.capstone.DTO.RenteeLogDTO;

/**
 * Service interface for managing rentee logs.
 */
@Service
@Transactional
public interface RenteeLogServices {
	
	 /**
     * Records a new rentee log entry.
     * @param renteeLogDTO The RenteeLogDTO object containing details of the rentee log.
     * @return The ID of the newly created rentee log entry.
     */
	Integer enterRenteeLog(RenteeLogDTO renteeLogDTO);

	/**
     * Updates an existing rentee log entry.
     * @param rentId The ID of the rentee log entry to update.
     * @param renteeLogDTO The RenteeLogDTO object containing updated details.
     * @return The updated RenteeLogDTO object.
     */
	RenteeLogDTO updateRenteeLog(String rentId, RenteeLogDTO renteeLogDTO);
	
	/**
     * Retrieves all rentee log entries.
     * @return List of RenteeLogDTO objects representing all rentee log entries.
     */
	List<RenteeLogDTO> viewRentee();
	
	/**
     * Retrieves rentee log entries associated with a specific book.
     * @param bookId The ID of the book to retrieve rentee logs for.
     * @return List of RenteeLogDTO objects representing rentee log entries for the book.
     */
	List<RenteeLogDTO> viewRenteeByBookId(String bookId);
	
	/**
     * Retrieves rentee log entries grouped by user and book IDs.
     * @return Map where keys are user IDs and values are lists of book IDs rented by each user.
     */
	Map<Integer, List<Integer>> getRenteeLogsGroupedByUserAndBookIds();

}
