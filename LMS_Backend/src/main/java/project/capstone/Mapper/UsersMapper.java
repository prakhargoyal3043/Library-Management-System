package project.capstone.Mapper;

import project.capstone.DTO.UsersDTO;
import project.capstone.EntityModels.Users;

public class UsersMapper {
	
	public static UsersDTO mapToUsersDTO(Users users)
	{
		return new UsersDTO(
				users.getUserId(),
				users.getUserName(),
				users.getUserEmail(),
				users.getUserPhoneNumber(),
				users.getUserGovId(),
				users.getUserPassword(),
				users.getUserAddress(),
				users.getUserRole(),
				users.getUserStatus(),
				users.getUserVerificationStatus()
				);
	}
	
	public static Users mapToUsers(UsersDTO usersDTO)
	{
		return new Users(
				usersDTO.getUserId(),
				usersDTO.getUserName(),
				usersDTO.getUserEmail(),
				usersDTO.getUserPhoneNumber(),
				usersDTO.getUserGovId(),
				usersDTO.getUserPassword(),
				usersDTO.getUserAddress(),
				usersDTO.getUserRole(),
				usersDTO.getUserStatus(),
				usersDTO.getUserVerificationStatus()
				);
	}

}
