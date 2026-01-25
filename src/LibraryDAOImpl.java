/*************************************************************
- This class implements DAO interface.
- All JDBC logic is inside DAO implementation.
- PreparedStatement is used to prevent SQL Injection.
- Each method corresponds to one CRUD operation.
- DTO object maps one database row to Java object.
***************************************************************/


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class LibraryDAOImpl implements LibraryDAO {

    // Database connection details
    private static final String URL = "jdbc:mysql://localhost:3306/rnsitdb";
    private static final String USER = "root";
    private static final String PASS = "Banni@123";

    // INSERT operation
    @Override
    public void addBook(LibraryDTO book) {
        try {
            // Step 1: Establish connection
            Connection con = DriverManager.getConnection(URL, USER, PASS);

            // Step 2: SQL query
            String sql = "INSERT INTO books(title, author, quantity) VALUES (?, ?, ?)";

            // Step 3: Prepare statement
            PreparedStatement pst = con.prepareStatement(sql);

            // Step 4: Set values
            pst.setString(1, book.getTitle());
            pst.setString(2, book.getAuthor());
            pst.setInt(3, book.getQuantity());

            // Step 5: Execute query
            pst.executeUpdate();

            // Step 6: Close connection
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // SELECT by ID
    @Override
    public LibraryDTO getBookById(int id) {
        LibraryDTO book = null;
        try {
            Connection con = DriverManager.getConnection(URL, USER, PASS);

            String sql = "SELECT * FROM books WHERE id=?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, id);

            ResultSet rs = pst.executeQuery();

            // If record exists
            if (rs.next()) {
                book = new LibraryDTO(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("author"),
                        rs.getInt("quantity")
                );
            }

            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return book;
    }

    // SELECT ALL
    @Override
    public List<LibraryDTO> getAllBooks() {
        List<LibraryDTO> list = new ArrayList<>();
        try {
            Connection con = DriverManager.getConnection(URL, USER, PASS);

            String sql = "SELECT * FROM books";
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                LibraryDTO book = new LibraryDTO(
                        rs.getInt("id"),
                        rs.getString("title"),
                        rs.getString("author"),
                        rs.getInt("quantity")
                );
                list.add(book);
            }

            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    // UPDATE
    @Override
    public void updateBook(LibraryDTO book) {
        try {
            Connection con = DriverManager.getConnection(URL, USER, PASS);

            String sql = "UPDATE books SET title=?, author=?, quantity=? WHERE id=?";
            PreparedStatement pst = con.prepareStatement(sql);

            pst.setString(1, book.getTitle());
            pst.setString(2, book.getAuthor());
            pst.setInt(3, book.getQuantity());
            pst.setInt(4, book.getId());

            pst.executeUpdate();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // DELETE
    @Override
    public void deleteBook(int id) {
        try {
            Connection con = DriverManager.getConnection(URL, USER, PASS);

            String sql = "DELETE FROM books WHERE id=?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, id);

            pst.executeUpdate();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
