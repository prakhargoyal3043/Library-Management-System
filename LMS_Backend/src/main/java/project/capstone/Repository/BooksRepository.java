package project.capstone.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import project.capstone.EntityModels.Books;

@Repository
public interface BooksRepository extends JpaRepository<Books, String>{

}
