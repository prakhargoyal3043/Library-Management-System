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
import project.capstone.DTO.LoginDTO;
import project.capstone.DTO.UsersDTO;
import project.capstone.Services.UsersServices;

/**
 * Controller class for managing operations related to users.
 */
@AllArgsConstructor
@RestController
@RequestMapping("/users")
@CrossOrigin
public class UsersController {
	
	@Autowired
	private UsersServices usersServices;
	
	 /**
     * Endpoint to add a new user.
     * @param usersDTO The UsersDTO object representing the user to add.
     * @return ResponseEntity containing the added UsersDTO with HTTP status CREATED.
     */
	@PostMapping
	public ResponseEntity<UsersDTO> addNewUser(@RequestBody UsersDTO usersDTO)
	{
		UsersDTO usersDTO2 = usersServices.addUsers(usersDTO);
		return new ResponseEntity<UsersDTO>(usersDTO2, HttpStatus.CREATED);
	}
	
	/**
     * Endpoint to add a new admin user.
     * @param usersDTO The UsersDTO object representing the admin user to add.
     * @return ResponseEntity containing the added admin UsersDTO with HTTP status CREATED.
     */
	@PostMapping("/admin")
	public ResponseEntity<UsersDTO> addAdmin(@RequestBody UsersDTO usersDTO)
	{
		UsersDTO usersDTO2 = usersServices.addAdmin(usersDTO);
		return new ResponseEntity<UsersDTO>(usersDTO2, HttpStatus.CREATED);
	}
	
	/**
     * Endpoint to retrieve all users.
     * @return ResponseEntity containing a list of UsersDTOs with HTTP status OK.
     */
	@GetMapping
	public ResponseEntity<List<UsersDTO>> getAllUsers(){
		List<UsersDTO> users = usersServices.viewAllUsers();
		return ResponseEntity.ok(users);
	}
	
	/**
     * Endpoint to view a user by their ID.
     * @param userId The ID of the user to retrieve.
     * @return ResponseEntity containing the UsersDTO of the requested user with HTTP status OK.
     */
	@GetMapping("/view/{id}")
	public ResponseEntity<UsersDTO> viewUser(@PathVariable("id") String userId)
	{
		UsersDTO usersDTO = usersServices.viewUserById(userId);
		
		return ResponseEntity.ok(usersDTO);
	}
	
	/**
     * Endpoint to view a user by their email (username).
     * @param userId The email (username) of the user to retrieve.
     * @return ResponseEntity containing the UsersDTO of the requested user with HTTP status OK.
     */
	@GetMapping("/view/username/{id}")
	public ResponseEntity<UsersDTO> viewUserByMail(@PathVariable("id") String userId)
	{
		UsersDTO usersDTO = usersServices.viewUserByMail(userId);
		
		return ResponseEntity.ok(usersDTO);
	}
	
	/**
     * Endpoint to delete a user by their ID.
     * @param userId The ID of the user to delete.
     * @return ResponseEntity with a success message and HTTP status OK.
     */
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteUser(@PathVariable("id") String userId)
	{
		usersServices.deleteUserById(userId);
		return ResponseEntity.ok("User Deleted Successfully.");
	}
	
	/**
     * Endpoint to update a user's information by their ID.
     * @param userId The ID of the user to update.
     * @param usersDTO The updated UsersDTO object.
     * @return ResponseEntity containing the updated UsersDTO with HTTP status OK.
     */
	@PutMapping("/update/{id}")
	public ResponseEntity<UsersDTO> updateUser(@PathVariable("id") String userId, @RequestBody UsersDTO usersDTO)
	{
		UsersDTO usersDTO2 = usersServices.updateUser(userId, usersDTO);
		return ResponseEntity.ok(usersDTO2);
	}
	
	/**
     * Endpoint to ban a user by their ID.
     * @param userId The ID of the user to ban.
     * @return ResponseEntity with a success message and HTTP status OK.
     */
	@PutMapping("/ban/{id}")
	public ResponseEntity<String> banUser(@PathVariable("id") String userId)
	{
		usersServices.banUser(userId);
		return ResponseEntity.ok("User Banned Successfully.");
	}
	
	 /**
     * Endpoint to unban a user by their ID.
     * @param userId The ID of the user to unban.
     * @return ResponseEntity with a success message and HTTP status OK.
     */
	@PutMapping("/unban/{id}")
	public ResponseEntity<String> unbanUser(@PathVariable("id") String userId)
	{
		usersServices.unbanUser(userId);
		return ResponseEntity.ok("User Unbanned Successfully.");
	}

	/**
     * Endpoint to verify user login credentials.
     * @param loginDTO The LoginDTO object containing user credentials.
     * @return ResponseEntity containing the role of the user with HTTP status OK.
     */
	@PostMapping("/login")
	public ResponseEntity<String> viewRole(@RequestBody LoginDTO loginDTO)
	{
		String role = usersServices.verifyUser(loginDTO.getUserId(), loginDTO.getPassword());
		
		return ResponseEntity.ok(role);
	}
	
}
