package project.capstone.Controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.persistence.criteria.CriteriaBuilder.In;
import lombok.AllArgsConstructor;
import project.capstone.DTO.RenteeLogDTO;
import project.capstone.Services.RenteeLogServices;

/**
 * Controller class for managing rentee log operations.
 */
@AllArgsConstructor
@RestController
@RequestMapping("/log")
@CrossOrigin
public class RenteeLogController {
	@Autowired
	private RenteeLogServices renteeLogServices;
	
	/**
     * Endpoint to enter a new rentee log.
     * @param renteeLogDTO The RenteeLogDTO object containing log information.
     * @return ResponseEntity containing the rent ID with HTTP status OK.
     */
	@PostMapping
	public ResponseEntity<Integer> enterLog(@RequestBody RenteeLogDTO renteeLogDTO)
	{
		Integer rentId = renteeLogServices.enterRenteeLog(renteeLogDTO);
		return ResponseEntity.ok(rentId);
	}
	
	/**
     * Endpoint to update an existing rentee log.
     * @param rentId The ID of the rentee log to update.
     * @param renteeLogDTO The updated RenteeLogDTO object.
     * @return ResponseEntity containing the updated RenteeLogDTO with HTTP status OK.
     */
	@PutMapping("/update/{id}")
	public ResponseEntity<RenteeLogDTO> updateLog(@PathVariable("id") String rentId, @RequestBody RenteeLogDTO renteeLogDTO)
	{
		RenteeLogDTO renteeLogDTO2 = renteeLogServices.updateRenteeLog(rentId, renteeLogDTO);
		return ResponseEntity.ok(renteeLogDTO2);
	}
	
	 /**
     * Endpoint to view all rentee logs.
     * @return ResponseEntity containing a list of RenteeLogDTOs with HTTP status OK.
     */
	@GetMapping("/view")
	public ResponseEntity<List<RenteeLogDTO>> viewAll()
	{
		List<RenteeLogDTO> renteeLogDTOs = renteeLogServices.viewRentee();
		return new ResponseEntity<List<RenteeLogDTO>>(renteeLogDTOs, HttpStatus.OK);
	}
	
	/**
     * Endpoint to view rentee logs by a specific book ID.
     * @param bookId The ID of the book to filter rentee logs.
     * @return ResponseEntity containing a list of RenteeLogDTOs filtered by book ID with HTTP status OK.
     */
	@GetMapping("/view/{id}")
	public ResponseEntity<List<RenteeLogDTO>> viewByBookId(@PathVariable("id") String bookId)
	{
		List<RenteeLogDTO> renteeLogDTO = renteeLogServices.viewRenteeByBookId(bookId);
		return new ResponseEntity<List<RenteeLogDTO>>(renteeLogDTO, HttpStatus.OK);
	}
	
	/**
     * Endpoint to retrieve rentee details grouped by user and book IDs.
     * @return ResponseEntity containing a map where keys are user IDs and values are lists of book IDs with HTTP status OK.
     */
	@GetMapping("/details")
	public ResponseEntity<Map<Integer, List<Integer>>> renteeDetails()
	{
		return new ResponseEntity<Map<Integer, List<Integer>>>(renteeLogServices.getRenteeLogsGroupedByUserAndBookIds(), HttpStatus.OK);
	}

}
