package project.capstone.Services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import project.capstone.DTO.UsersDTO;

/**
 * Service interface for managing users.
 */
@Transactional
@Service
public interface UsersServices {
	
	/**
     * Adds a new user.
     * @param usersDTO The UsersDTO object containing user details.
     * @return The UsersDTO object representing the newly added user.
     */
	UsersDTO addUsers(UsersDTO usersDTO);
	
	/**
     * Adds a new administrator (admin) user.
     * @param usersDTO The UsersDTO object containing admin user details.
     * @return The UsersDTO object representing the newly added admin user.
     */
	UsersDTO addAdmin(UsersDTO usersDTO);

	/**
     * Deletes a user by their ID.
     * @param userId The ID of the user to delete.
     */
	void deleteUserById(String userId);

	/**
     * Retrieves a list of all users.
     * @return List of UsersDTO objects representing all users.
     */
	List<UsersDTO> viewAllUsers();

	/**
     * Retrieves a user by their ID.
     * @param userId The ID of the user to retrieve.
     * @return The UsersDTO object representing the user.
     */
	UsersDTO viewUserById(String userId);
	
	/**
     * Retrieves a user by their email (username).
     * @param userId The email (username) of the user to retrieve.
     * @return The UsersDTO object representing the user.
     */
	UsersDTO viewUserByMail(String userId);

	/**
     * Updates a user's information.
     * @param userId The ID of the user to update.
     * @param usersDTO The UsersDTO object containing updated user details.
     * @return The UsersDTO object representing the updated user.
     */
	UsersDTO updateUser(String userId, UsersDTO usersDTO);
	
	 /**
     * Bans a user by setting their status to inactive.
     * @param userId The ID of the user to ban.
     */
	void banUser(String userId);
	
	 /**
     * Unbans a user by setting their status to active.
     * @param userId The ID of the user to unban.
     */
	void unbanUser(String userId);
		
	/**
     * Verifies a user's credentials and returns their role.
     * @param userName The username of the user.
     * @param password The password of the user.
     * @return A string representing the user's role.
     * @throws ResourceNotFoundException if the username or password is invalid.
     */
	String verifyUser(String userName, String password);

}
