package project.capstone.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import project.capstone.EntityModels.RenteeLog;

@Repository
public interface RenteeLogRepository extends JpaRepository<RenteeLog, String> {

	 @Query(value = "SELECT user_id, book_id FROM rentee_log", nativeQuery = true)
	    List<Object[]> findRenteeLogs();

}
