/*************************************************************
- DTO is a POJO / Java Bean.
- It carries data between DAO, Service, and UI layers.
- No business logic, no JDBC code.
- One DTO object represents one row in the database table.
***************************************************************/

public class LibraryDTO {

    // Represents book id from database
    private int id;

    // Represents book title
    private String title;

    // Represents book author
    private String author;

    // Represents number of copies available
    private int quantity;

    // Default constructor (required for flexibility & frameworks)
    public LibraryDTO() {
    }

    // Parameterized constructor for easy object creation
    public LibraryDTO(int id, String title, String author, int quantity) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.quantity = quantity;
    }

    // Getter and Setter methods

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
