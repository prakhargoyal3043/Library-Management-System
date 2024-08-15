package project.capstone.ServicesImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.AllArgsConstructor;
import project.capstone.DTO.UsersDTO;
import project.capstone.EntityModels.Users;
import project.capstone.Exception.ResourceNotFoundException;
import project.capstone.Mapper.UsersMapper;
import project.capstone.Repository.UsersRepository;
import project.capstone.Services.UsersServices;

/**
 * Implementation of UsersServices interface providing operations related to users.
 */
@Service
@AllArgsConstructor
@Transactional
public class UsersServicesImpl implements UsersServices {

	@Autowired
	private UsersRepository usersRepository;

	/**
     * Adds a new user.
     * @param usersDTO The UsersDTO object containing details of the user to be added.
     * @return The UsersDTO object representing the added user.
     */
	@Override
	public UsersDTO addUsers(UsersDTO usersDTO) {
		// TODO Auto-generated method stub
		Users users = UsersMapper.mapToUsers(usersDTO);
		users.setUserRole("User");
		users.setUserStatus(1);
		users.setUserVerificationStatus(0);
		Users savedUsers = usersRepository.save(users);
		return UsersMapper.mapToUsersDTO(savedUsers);
	}

	/**
     * Deletes a user by ID.
     * @param userId The ID of the user to delete.
     * @throws ResourceNotFoundException if no user with the specified ID exists.
     */
	@Override
	public void deleteUserById(String userId) {
		// TODO Auto-generated method stub
		usersRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("No such user found"));
		usersRepository.deleteById(userId);

	}

	/**
     * Retrieves all users.
     * @return List of UsersDTO objects representing all users.
     */
	@Override
	@Transactional(readOnly = true)
	public List<UsersDTO> viewAllUsers() {
		// TODO Auto-generated method stub
		List<Users> users = usersRepository.findAll();
		return users.stream().map((user) -> UsersMapper.mapToUsersDTO(user)).collect(Collectors.toList());
	}

	/**
     * Retrieves a user by ID.
     * @param userId The ID of the user to retrieve.
     * @return The UsersDTO object representing the retrieved user.
     * @throws ResourceNotFoundException if no user with the specified ID exists.
     */
	@Override
	@Transactional(readOnly = true)
	public UsersDTO viewUserById(String userId) {
		// TODO Auto-generated method stub
		Users users = usersRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("No such user found"));

		return UsersMapper.mapToUsersDTO(users);
	}
	
	/**
     * Retrieves a user by email.
     * @param userEmail The email of the user to retrieve.
     * @return The UsersDTO object representing the retrieved user.
     * @throws ResourceNotFoundException if no user with the specified email exists.
     */
	@Override
	@Transactional(readOnly = true)
	public UsersDTO viewUserByMail(String userId) {
		// TODO Auto-generated method stub
		Users users = usersRepository.findUser(userId);
		
		if(users == null)
		{
			throw new ResourceNotFoundException("No such user found");
		}

		return UsersMapper.mapToUsersDTO(users);
	}

	/**
     * Updates an existing user.
     * @param userId The ID of the user to update.
     * @param usersDTO The UsersDTO object containing updated details.
     * @return The updated UsersDTO object.
     * @throws ResourceNotFoundException if no user with the specified ID exists.
     */
	@Override
	public UsersDTO updateUser(String userId, UsersDTO usersDTO) {
		// TODO Auto-generated method stub
		Users users = usersRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("No such user found"));
		
		users.setUserName(usersDTO.getUserName());
		users.setUserEmail(usersDTO.getUserEmail());
		users.setUserGovId(usersDTO.getUserGovId());
		users.setUserAddress(usersDTO.getUserAddress());
		users.setUserPassword(usersDTO.getUserPassword());
		users.setUserPhoneNumber(usersDTO.getUserPhoneNumber());
		
		Users updatedUsers = usersRepository.save(users);
		return UsersMapper.mapToUsersDTO(updatedUsers);
	}

	 /**
     * Bans a user by setting user status to 0.
     * @param userId The ID of the user to ban.
     * @throws ResourceNotFoundException if no user with the specified ID exists.
     */
	@Override
	public void banUser(String userId) {
		// TODO Auto-generated method stub
		Users users = usersRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("No such user found"));
		
		users.setUserStatus(0);
		usersRepository.save(users);
		
	}
	
	/**
     * Unbans a user by setting user status to 1.
     * @param userId The ID of the user to unban.
     * @throws ResourceNotFoundException if no user with the specified ID exists.
     */
	@Override
	public void unbanUser(String userId) {
		// TODO Auto-generated method stub
		Users users = usersRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("No such user found"));
		
		users.setUserStatus(1);
		usersRepository.save(users);
		
	}

	/**
     * Verifies user credentials and returns a role identifier.
     * @param userName The username of the user.
     * @param password The password of the user.
     * @return A string representing the role identifier.
     * @throws ResourceNotFoundException if the username or password is invalid.
     */
	@Override
	public String verifyUser(String userName, String password) {
		// TODO Auto-generated method stub
		String k;
		Users users = usersRepository.findUser(userName);
		if(users == null)
		{
			throw new ResourceNotFoundException("Invalid Username or Password");
		}
		if(users.getUserPassword().equals(password))
		{
			k = "898767934";
			if(users.getUserRole().equalsIgnoreCase("librarian"))
			{
				k = "765897953";				
			}
		}
		else {
			k = "13";
		}
		
		return k;
	}

	/**
     * Adds a new admin user.
     * @param usersDTO The UsersDTO object containing details of the admin user to be added.
     * @return The UsersDTO object representing the added admin user.
     */
	@Override
	public UsersDTO addAdmin(UsersDTO usersDTO) {
		// TODO Auto-generated method stub
		Users users = UsersMapper.mapToUsers(usersDTO);
		users.setUserRole("Librarian");
		users.setUserStatus(1);
		users.setUserVerificationStatus(0);
		Users savedUsers = usersRepository.save(users);
		return UsersMapper.mapToUsersDTO(savedUsers);
	}
	

}
