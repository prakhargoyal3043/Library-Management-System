package project.capstone;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.JsonPathResultMatchers;

import project.capstone.Controller.UsersController;
import project.capstone.DTO.BooksDTO;
import project.capstone.DTO.RenteeLogDTO;
import project.capstone.DTO.UsersDTO;
import project.capstone.Services.UsersServices;

import static org.mockito.ArgumentMatchers.intThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.sql.Date;
import java.util.Arrays;
import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class LibraryManagementSystemBackendApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private UsersServices usersServices; // Mocked service

    @Test
    @DisplayName("View User by Email - Success")
    @DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
    void testViewUserByMail() throws Exception {

        // Query the user by email
        mockMvc.perform(get("/users/view/username/{id}", "testUser4@example.com"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userName").value("Test User"))
                .andExpect(jsonPath("$.userEmail").value("testUser4@example.com"));
    }


    @Test
    @DisplayName("Add New User - Success")
    public void testAddNewUser() throws Exception {
        UsersDTO newUser = new UsersDTO(null, "New User", "newuser@example.com", 9876543210L,
                "EFGH5678", "newpassword", "456 New Avenue", "User", 1, 0);

        mockMvc.perform(post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(newUser)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.userName").value("New User"))
                .andExpect(jsonPath("$.userEmail").value("newuser@example.com"))
                .andExpect(jsonPath("$.userPhoneNumber").value(9876543210L))
                .andExpect(jsonPath("$.userGovId").value("EFGH5678"))
                .andExpect(jsonPath("$.userPassword").value("newpassword"))
                .andExpect(jsonPath("$.userAddress").value("456 New Avenue"))
                .andExpect(jsonPath("$.userRole").value("User"))
                .andExpect(jsonPath("$.userStatus").value(1))
                .andExpect(jsonPath("$.userVerificationStatus").value(0));
    }

    @Test
    @DisplayName("Get All Users - Success")
    public void testGetAllUsers() throws Exception {
        mockMvc.perform(get("/users"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());
    }
    
    @Test
    @DisplayName("Add Admin - Success")
    @DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
    void testAddAdmin() throws Exception {
        UsersDTO adminDTO = new UsersDTO(null, "Admin User", "admin@example.com", 9876543210L,
                "EFGH5678", "adminpassword", "123 Admin Street", "Admin", 1, 0);

        mockMvc.perform(post("/users/admin")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(adminDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.userName").value("Admin User"));
    }

    @Test
    @DisplayName("View User by ID - Success")
    @DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
    void testViewUserById() throws Exception {
    	UsersDTO newUser = new UsersDTO(null, "Test User", "testUser1@example.com", 9876543210L,
                "ABCD1234", "password", "123 Test Street", "User", 1, 0);

    	MockHttpServletResponse response = mockMvc.perform(post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(newUser)))
                .andExpect(status().isCreated())
                .andReturn().getResponse();
        
        String jsonResponse = response.getContentAsString();
        UsersDTO createdUser = objectMapper.readValue(jsonResponse, UsersDTO.class);
        Integer userId = createdUser.getUserId();
    	
        mockMvc.perform(get("/users/view/{id}", userId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userName").value("Test User")); // Replace with expected username
    }


    @Test
    @DisplayName("Delete User by ID - Success")
    @DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
    void testDeleteUserById() throws Exception {
    	
    	UsersDTO newUser = new UsersDTO(null, "Test User", "testUser1@example.com", 9876543210L,
                "ABCD1234", "password", "123 Test Street", "User", 1, 0);

    	MockHttpServletResponse response = mockMvc.perform(post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(newUser)))
                .andExpect(status().isCreated())
                .andReturn().getResponse();
        
        String jsonResponse = response.getContentAsString();
        UsersDTO createdUser = objectMapper.readValue(jsonResponse, UsersDTO.class);
        Integer userId = createdUser.getUserId();
        
        
        mockMvc.perform(delete("/users/delete/{id}", userId))
                .andExpect(status().isOk())
                .andExpect(content().string("User Deleted Successfully."));
    }

    @Test
    @DisplayName("Update User by ID - Success")
    @DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
    void testUpdateUserById() throws Exception {
    	UsersDTO newUser = new UsersDTO(null, "Test User", "testUser1@example.com", 9876543210L,
                "ABCD1234", "password", "123 Test Street", "User", 1, 0);

    	MockHttpServletResponse response = mockMvc.perform(post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(newUser)))
                .andExpect(status().isCreated())
                .andReturn().getResponse();
        
        String jsonResponse = response.getContentAsString();
        UsersDTO createdUser = objectMapper.readValue(jsonResponse, UsersDTO.class);
        Integer userId = createdUser.getUserId();
        
        UsersDTO updatedUser = new UsersDTO(1, "Updated User", "updateduser@example.com", 9876543210L,
                "EFGH5678", "newpassword", "456 New Avenue", "User", 1, 0);

        mockMvc.perform(put("/users/update/{id}", userId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updatedUser)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userName").value("Updated User")); // Replace with expected username
    }

    @Test
    @DisplayName("Ban User by ID - Success")
    @DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
    void testBanUserById() throws Exception {
    	UsersDTO newUser = new UsersDTO(null, "Test User", "testUser1@example.com", 9876543210L,
                "ABCD1234", "password", "123 Test Street", "User", 1, 0);

    	MockHttpServletResponse response = mockMvc.perform(post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(newUser)))
                .andExpect(status().isCreated())
                .andReturn().getResponse();
        
        String jsonResponse = response.getContentAsString();
        UsersDTO createdUser = objectMapper.readValue(jsonResponse, UsersDTO.class);
        Integer userId = createdUser.getUserId();
        
        mockMvc.perform(put("/users/ban/{id}", userId))
                .andExpect(status().isOk())
                .andExpect(content().string("User Banned Successfully."));
    }

    @Test
    @DisplayName("Unban User by ID - Success")
    @DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
    void testUnbanUserById() throws Exception {
    	UsersDTO newUser = new UsersDTO(null, "Test User", "testUser1@example.com", 9876543210L,
                "ABCD1234", "password", "123 Test Street", "User", 1, 0);

    	MockHttpServletResponse response = mockMvc.perform(post("/users")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(newUser)))
                .andExpect(status().isCreated())
                .andReturn().getResponse();
        
        String jsonResponse = response.getContentAsString();
        UsersDTO createdUser = objectMapper.readValue(jsonResponse, UsersDTO.class);
        Integer userId = createdUser.getUserId();
        
        mockMvc.perform(put("/users/unban/{id}", userId))
                .andExpect(status().isOk())
                .andExpect(content().string("User Unbanned Successfully."));
    }

    @Test
    @DisplayName("Add Admin - Success")
    @DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
    void testAddAdmin1() throws Exception {
        UsersDTO adminDTO = new UsersDTO(null, "Admin User", "admin@example.com", 9876543210L,
                "EFGH5678", "adminpassword", "123 Admin Street", "Admin", 1, 0);

        mockMvc.perform(post("/users/admin")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(adminDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.userName").value("Admin User")); // Replace with expected username
    }

    @Test
    @DisplayName("Verify User Login - Success")
    @DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
    void testVerifyUserLogin() throws Exception {
        // Example login request DTO
    	
        String loginJson = "{\"userId\": \"testuser21\", \"password\": \"password\"}";

        mockMvc.perform(post("/users/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(loginJson))
                .andExpect(status().isOk())
                .andExpect(content().string("898767934")); // Replace with expected role
    }
    
    @Test
    @DisplayName("View User by Email - User Not Found")
    @DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
    void testViewUserByMail_UserNotFound() throws Exception {
        mockMvc.perform(get("/users/view/username/{id}", "nonexistent@example.com"))
                .andExpect(status().isNotFound());
    }
    
    @Test
    @DisplayName("Delete User by ID - User Not Found")
    void testDeleteUserById_UserNotFound() throws Exception {
        mockMvc.perform(delete("/users/delete/{id}", 999)) // Assuming user with ID 999 doesn't exist
                .andExpect(status().isNotFound());
    }

//    
    
    @Test
    @DisplayName("Update User by ID - User Not Found")
    void testUpdateUserById_UserNotFound() throws Exception {
        UsersDTO updatedUser = new UsersDTO(999, "Updated User", "updateduser@example.com", 9876543210L,
                "EFGH5678", "newpassword", "456 New Avenue", "User", 1, 0); // Assuming user with ID 999 doesn't exist

        mockMvc.perform(put("/users/update/{id}", 999)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updatedUser)))
                .andExpect(status().isNotFound());
    }

    
    @Test
    @DisplayName("Ban User by ID - User Not Found")
    void testBanUserById_UserNotFound() throws Exception {
        mockMvc.perform(put("/users/ban/{id}", 999)) // Assuming user with ID 999 doesn't exist
                .andExpect(status().isNotFound());
    }

    
    @Test
    @DisplayName("Unban User by ID - User Not Found")
    void testUnbanUserById_UserNotFound() throws Exception {
        mockMvc.perform(put("/users/unban/{id}", 999)) // Assuming user with ID 999 doesn't exist
                .andExpect(status().isNotFound());
    }

    
    @Test
    @DisplayName("Verify Admin Login - Success")
    @DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
    void testVerifyLibrarianLogin() throws Exception {
        // Example login request DTO
        
        String loginJson = "{\"userId\": \"testadmin101\", \"password\": \"password\"}";
                
        mockMvc.perform(post("/users/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(loginJson))
                .andExpect(status().isOk());
    }
    
    @Test
    @DisplayName("Verify User Login - Failure")
    @DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
    void testVerifyInvalidLogin() throws Exception {
        // Example login request DTO
        
        String loginJson = "{\"userId\": \"testadmin19\", \"password\": \"passwor\"}";
                
        mockMvc.perform(post("/users/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(loginJson))
                .andExpect(status().isOk())
                .andExpect(content().string("13")); // Replace with expected role
    }
    
    @Test
    @DisplayName("Login User by ID - User Not Found")
    void testVerifyLogin_UserNotFound() throws Exception {

        String loginJson = "{\"userId\": \"testad\", \"password\": \"passwor\"}";
                
        mockMvc.perform(post("/users/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(loginJson)) // Assuming user with ID 999 doesn't exist
                .andExpect(status().isNotFound());
    }
    
    @Test
    @DisplayName("View User by ID - Failure")
    @DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
    void testViewUserById_Failure() throws Exception {
    	
        mockMvc.perform(get("/users/view/{id}", 10))
        .andExpect(status().isNotFound());
        
    }
    
    @Test
    @DisplayName("Add New Book - Success")
    @DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
    void testAddNewBooks_Success() throws Exception {
        BooksDTO newBook = new BooksDTO(null, "Book Name", "ISBN1234", "Author Name", "Publisher", "Description",
                "Science", 1, 100, "First Edition", 1, "https://example.com/image.jpg", null);

        mockMvc.perform(post("/books")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(newBook)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.bookName").value("Book Name"));
    }
    
    @Test
    @DisplayName("Bulk Upload Books - Success")
    @DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
    void testBulkUploadBooks_Success() throws Exception {
        List<BooksDTO> booksDTOs = Arrays.asList(
                new BooksDTO(null, "Book 1", "ISBN-1", "Author 1", "Publisher 1", "Description 1",
                        "Science", 1, 100, "First Edition", 1, "https://example.com/image1.jpg", null),
                new BooksDTO(null, "Book 2", "ISBN-2", "Author 2", "Publisher 2", "Description 2",
                        "Mystery", 1, 120, "Second Edition", 1, "https://example.com/image2.jpg", null)
        );

        mockMvc.perform(post("/books/bulkupload")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(booksDTOs)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$[0].bookName").value("Book 1"))
                .andExpect(jsonPath("$[1].bookName").value("Book 2"));
    }

    
    @Test
    @DisplayName("Get All Books - Success")
    void testGetAllBooks_Success() throws Exception {
    	BooksDTO newBook = new BooksDTO(null, "Book Name", "ISBN1234", "Author Name", "Publisher", "Description",
                "Science", 1, 100, "First Edition", 1, "https://example.com/image.jpg", null);

        mockMvc.perform(post("/books")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(newBook)))
                .andExpect(status().isCreated());
        
        mockMvc.perform(get("/books"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0].bookName").exists());
    }

    @Test
    @DisplayName("Find Book by ID - Success")
    void testFindBookById_Success() throws Exception {
    	BooksDTO newBook = new BooksDTO(null, "Book Name", "ISBN1234", "Author Name", "Publisher", "Description",
                "Mystery", 1, 100, "First Edition", 1, "https://example.com/image.jpg", null);

    	MockHttpServletResponse response = mockMvc.perform(post("/books")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(newBook)))
                .andExpect(status().isCreated())
                .andReturn().getResponse();
           	 
           	 String jsonResponse = response.getContentAsString();
                BooksDTO createdLog = objectMapper.readValue(jsonResponse, BooksDTO.class);
                Integer bookId = createdLog.getBookId();
        
        mockMvc.perform(get("/books/find/{id}", bookId))
                .andExpect(status().isOk());
    }
    
    @Test
    @DisplayName("Update Book - Success")
    @DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
    void testUpdateBooks_Success() throws Exception {
    	BooksDTO newBook = new BooksDTO(null, "Book Name", "ISBN1234", "Author Name", "Publisher", "Description",
                "Horror", 1, 100, "First Edition", 1, "https://example.com/image.jpg", null);

    	MockHttpServletResponse response = mockMvc.perform(post("/books")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(newBook)))
                .andExpect(status().isCreated())
                .andReturn().getResponse();
           	 
           	 String jsonResponse = response.getContentAsString();
                BooksDTO createdLog = objectMapper.readValue(jsonResponse, BooksDTO.class);
                Integer bookId = createdLog.getBookId();
                
        BooksDTO updatedBook = new BooksDTO(1, "Updated Book Name", "ISBN1234", "Author Name", "Publisher", "Description",
                "Horror", 1, 120, "Second Edition", 1, "https://example.com/image.jpg", null);

        mockMvc.perform(put("/books/update/{id}", bookId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updatedBook)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.bookName").value("Updated Book Name"));
    }
    
    @Test
    @DisplayName("Delete Book by ID - Success")
    @DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
    void testDeleteBookById_Success() throws Exception {
    	BooksDTO newBook = new BooksDTO(null, "Book Name", "ISBN1234", "Author Name", "Publisher", "Description",
                "Horror", 1, 100, "First Edition", 1, "https://example.com/image.jpg", null);

    	MockHttpServletResponse response = mockMvc.perform(post("/books")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(newBook)))
                .andExpect(status().isCreated())
                .andReturn().getResponse();
           	 
           	 String jsonResponse = response.getContentAsString();
                BooksDTO createdLog = objectMapper.readValue(jsonResponse, BooksDTO.class);
                Integer bookId = createdLog.getBookId();
                
        mockMvc.perform(delete("/books/delete/{id}", bookId))
                .andExpect(status().isOk())
                .andExpect(content().string("Book Deleted Successfully."));
    }
    
    @Test
    @DisplayName("Get All Categories - Success")
    void testGetAllCategories_Success() throws Exception {
        mockMvc.perform(get("/books/categories"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray())
                .andExpect(jsonPath("$[0]").exists()); // Assuming categories are returned as strings
    }


    
    @Test
    @DisplayName("Add New Book - Failure")
    @DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
    void testAddNewBooks_Failure() throws Exception {
        BooksDTO newBook = new BooksDTO(null, "Book Name", "ISBN1234", "Author Name", "Publisher", "Description",
                "Scien", 1, 100, "First Edition", 1, "https://example.com/image.jpg", null);

        mockMvc.perform(post("/books")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(newBook)))
                .andExpect(status().isNotFound());
    }
    
    @Test
    @DisplayName("Delete Book by ID - Failure")
    @DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
    void testDeleteBookById_Failure() throws Exception {                
        mockMvc.perform(delete("/books/delete/{id}", 123456))
                .andExpect(status().isNotFound());
    }
    
    @Test
    @DisplayName("Update Book - Failure")
    @DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
    void testUpdateBooks_Failure() throws Exception {
                
        BooksDTO updatedBook = new BooksDTO(1, "Updated Book Name", "ISBN1234", "Author Name", "Publisher", "Description",
                "Horror", 1, 120, "Second Edition", 1, "https://example.com/image.jpg", null);

        mockMvc.perform(put("/books/update/{id}", 987065)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updatedBook)))
                .andExpect(status().isNotFound());
    }


    @Test
    @DisplayName("Find Book by ID - Failure")
    void testFindBookById_Failure() throws Exception {        
        mockMvc.perform(get("/books/find/{id}", 87656))
                .andExpect(status().isNotFound());
    }
    
    
    @Test
    @DisplayName("Bulk Upload Books - Failure")
    @DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
    void testBulkUploadBooks_Failure() throws Exception {
        List<BooksDTO> booksDTOs = Arrays.asList(
                new BooksDTO(null, "Book 1", "ISBN-1", "Author 1", "Publisher 1", "Description 1",
                        "Sciene2", 1, 100, "First Edition", 1, "https://example.com/image1.jpg", null),
                new BooksDTO(null, "Book 2", "ISBN-2", "Author 2", "Publisher 2", "Description 2",
                        "Myste1", 1, 120, "Second Edition", 1, "https://example.com/image2.jpg", null)
        );

        mockMvc.perform(post("/books/bulkupload")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(booksDTOs)))
                .andExpect(status().isNotFound());
    }
    
    
//    
    @Test
    @DisplayName("Enter Rentee Log - Success")
    @DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
    void testEnterRenteeLog_Success() throws Exception {
    	BooksDTO newBook = new BooksDTO(null, "Book Name", "ISBN1234", "Author Name", "Publisher", "Description",
                "Mystery", 1, 100, "First Edition", 1, "https://example.com/image.jpg", null);

    	MockHttpServletResponse response = mockMvc.perform(post("/books")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(newBook)))
                .andExpect(status().isCreated())
                .andReturn().getResponse();
           	 
           	 String jsonResponse = response.getContentAsString();
                BooksDTO createdLog = objectMapper.readValue(jsonResponse, BooksDTO.class);
                Integer bookId = createdLog.getBookId();
                
                UsersDTO newUser = new UsersDTO(null, "Test User", "testUser1@example.com", 9876543210L,
                        "ABCD1234", "password", "123 Test Street", "User", 1, 0);

            	MockHttpServletResponse response1 = mockMvc.perform(post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newUser)))
                        .andExpect(status().isCreated())
                        .andReturn().getResponse();
                
                String jsonResponse2 = response.getContentAsString();
                UsersDTO createdUser = objectMapper.readValue(jsonResponse2, UsersDTO.class);
                Integer userId = createdUser.getUserId();
                
        RenteeLogDTO renteeLogDTO = new RenteeLogDTO(null, userId, bookId, Date.valueOf("2024-07-25"),
                Date.valueOf("2024-08-25"), 100, 0, null);

        mockMvc.perform(post("/log")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(renteeLogDTO)))
                .andExpect(status().isOk());
    }

	@Test
	@DisplayName("Update Rentee Log - Success")
	@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
	void testUpdateRenteeLog_Success() throws Exception {
		RenteeLogDTO updatedRenteeLogDTO = new RenteeLogDTO(null, 3, 4, Date.valueOf("2024-07-25"),
				Date.valueOf("2024-08-25"), 120, 20, Date.valueOf("2024-08-20"));
		
		
		mockMvc.perform(put("/log/update/{id}", 952).contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(updatedRenteeLogDTO))).andExpect(status().isOk())
				.andExpect(jsonPath("$.totalAmount").value(120));
	}

    @Test
    @DisplayName("View All Rentee Logs - Success")
    void testViewAllRenteeLogs_Success() throws Exception {
    	BooksDTO newBook = new BooksDTO(null, "Book Name", "ISBN1234", "Author Name", "Publisher", "Description",
                "Mystery", 1, 100, "First Edition", 1, "https://example.com/image.jpg", null);

    	MockHttpServletResponse response = mockMvc.perform(post("/books")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(newBook)))
                .andExpect(status().isCreated())
                .andReturn().getResponse();
           	 
           	 String jsonResponse = response.getContentAsString();
                BooksDTO createdLog = objectMapper.readValue(jsonResponse, BooksDTO.class);
                Integer bookId = createdLog.getBookId();
                
                UsersDTO newUser = new UsersDTO(null, "Test User", "testUser1@example.com", 9876543210L,
                        "ABCD1234", "password", "123 Test Street", "User", 1, 0);

            	MockHttpServletResponse response1 = mockMvc.perform(post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(newUser)))
                        .andExpect(status().isCreated())
                        .andReturn().getResponse();
                
                String jsonResponse2 = response.getContentAsString();
                UsersDTO createdUser = objectMapper.readValue(jsonResponse2, UsersDTO.class);
                Integer userId = createdUser.getUserId();
                
        RenteeLogDTO renteeLogDTO = new RenteeLogDTO(null, userId, bookId, Date.valueOf("2024-07-25"),
                Date.valueOf("2024-08-25"), 100, 0, null);

        mockMvc.perform(post("/log")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(renteeLogDTO)))
                .andExpect(status().isOk());
        
        mockMvc.perform(get("/log/view"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray()) // Ensure it's an array
                .andExpect(jsonPath("$[0].rentId").exists()); // Assuming structure, adjust as per your JSON
    }
	
	@Test
	@DisplayName("View Rentee Logs By Book ID - Success")
	@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
	void testViewRenteeLogsByBookId_Success() throws Exception {
	    // Assuming there are existing rentee logs related to the bookId "1"
		
	    mockMvc.perform(get("/log/view/{id}", 5956))
	            .andExpect(status().isOk())
	            .andExpect(jsonPath("$").isArray()); // Assuming response is a JSON array
	}
	
	@Test
	@DisplayName("View Rentee Details Grouped By User and Book IDs - Success")
	void testViewRenteeDetails_Success() throws Exception {
				
	    mockMvc.perform(get("/log/details"))
	            .andExpect(status().isOk())
	            .andExpect(jsonPath("$").isMap()); // Assuming response is a JSON object (map)
	}

	
	@Test
	@DisplayName("View Rentee Logs By Book ID - Not Found")
	void testViewRenteeLogsByBookId_NotFound() throws Exception {
	    // Assuming there are no rentee logs related to the bookId "999"
	    String bookId = "999";

	    mockMvc.perform(get("/log/view/{id}", bookId))
	            .andExpect(status().isNotFound());
	}
	
	@Test
	@DisplayName("Update Rentee Log - Failure")
	@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
	void testUpdateRenteeLog_Failure() throws Exception {
		RenteeLogDTO updatedRenteeLogDTO = new RenteeLogDTO(null, 3, 4, Date.valueOf("2024-07-25"),
				Date.valueOf("2024-08-25"), 120, 20, Date.valueOf("2024-08-20"));
		
		
		mockMvc.perform(put("/log/update/{id}", 987656).contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(updatedRenteeLogDTO)))
				.andExpect(status().isNotFound());
	}



	// Add more test cases for other controller methods (viewUser, deleteUser,
	// updateUser, etc.)
}
