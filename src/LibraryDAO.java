/*************************************************************
- DAO stands for Data Access Object.
- DAO contains only database-related method declarations.
- It hides JDBC logic from other layers.
- Using an interface ensures loose coupling.
- Multiple implementations are possible without changing service layer.
***************************************************************/

import java.util.List;

public interface LibraryDAO {

    // Insert book into database
    void addBook(LibraryDTO book);

    // Fetch single book by id
    LibraryDTO getBookById(int id);

    // Fetch all books
    List<LibraryDTO> getAllBooks();

    // Update book data
    void updateBook(LibraryDTO book);

    // Delete book by id
    void deleteBook(int id);
}
