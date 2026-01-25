/*************************************************************
- Service layer contains business rules and validations.
- DAO layer is kept clean and reusable.
- Service layer controls application flow.
- Validation like `quantity >= 0` is done before database access.
- This improves maintainability and scalability.
***************************************************************/

import java.util.List;

public class LibraryService {

    // DAO reference
    private LibraryDAO dao = new LibraryDAOImpl();

    // Validation before insert
    public void addBook(LibraryDTO book) {

        // Business rule: quantity cannot be negative
        if (book.getQuantity() < 0) {
            System.out.println("Book quantity cannot be negative");
            return;
        }

        dao.addBook(book);
    }

    // Fetch book by id
    public LibraryDTO getBook(int id) {
        return dao.getBookById(id);
    }

    // Fetch all books
    public List<LibraryDTO> getAllBooks() {
        return dao.getAllBooks();
    }

    // Update book details
    public void updateBook(LibraryDTO book) {
        dao.updateBook(book);
    }

    // Delete book by id
    public void deleteBook(int id) {
        dao.deleteBook(id);
    }
}
