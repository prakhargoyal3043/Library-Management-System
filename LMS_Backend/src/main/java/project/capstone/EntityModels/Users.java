package project.capstone.EntityModels;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "LMS_Users")
public class Users {
	@Id
	@Column(name = "user_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer userId;

	@Column(name = "User_Name")
	private String userName;

	@Column(name = "User_Email")
	private String userEmail;

	@Column(name = "User_Phone_No")
	private Long userPhoneNumber;

	@Column(name = "User_Gov_Id")
	private String userGovId;

	@Column(name = "User_Password")
	private String userPassword;

	@Column(name = "User_Address")
	private String userAddress;

	@Column(name = "User_Role")
	private String userRole;

	@Column(name = "User_Status")
	private Integer userStatus;

	@Column(name = "User_Verification_Status")
	private Integer userVerificationStatus;



	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public void setUserPhoneNumber(Long userPhoneNumber) {
		this.userPhoneNumber = userPhoneNumber;
	}

	public void setUserGovId(String userGovId) {
		this.userGovId = userGovId;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public void setUserAddress(String userAddress) {
		this.userAddress = userAddress;
	}

	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}

	public void setUserStatus(Integer userStatus) {
		this.userStatus = userStatus;
	}

	public void setUserVerificationStatus(Integer userVerificationStatus) {
		this.userVerificationStatus = userVerificationStatus;
	}
	

}
