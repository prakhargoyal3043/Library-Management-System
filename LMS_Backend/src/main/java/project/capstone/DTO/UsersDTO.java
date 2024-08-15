package project.capstone.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UsersDTO {
	private Integer userId ;
	private String userName;
	private String userEmail;
	private Long userPhoneNumber;
	private String userGovId;
	private String userPassword;
	private String userAddress;
	private String userRole;
	private Integer userStatus;
	private Integer userVerificationStatus;

}
