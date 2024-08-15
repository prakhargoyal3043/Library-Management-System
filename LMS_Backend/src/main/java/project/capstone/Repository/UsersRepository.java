package project.capstone.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import project.capstone.EntityModels.Users;

@Repository
public interface UsersRepository extends JpaRepository<Users, String>{
	
	@Query("Select u FROM Users u Where u.userEmail = :email")
	Users findUser(@Param("email") String email);
	

}
