/*************************************************************
- Main class is the user interface layer.
- It takes input using Scanner.
- It calls service layer, not DAO directly.
- This maintains separation of concerns.
- Menu-driven program helps test all CRUD operations.
***************************************************************/

import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        LibraryService service = new LibraryService();
        Scanner sc = new Scanner(System.in);

        while (true) {

            System.out.println("\n1. Add Book");
            System.out.println("2. Get Book By ID");
            System.out.println("3. Get All Books");
            System.out.println("4. Update Book");
            System.out.println("5. Delete Book");
            System.out.println("6. Exit");

            System.out.print("Enter choice: ");
            int choice = sc.nextInt();

            switch (choice) {

                case 1:
                    System.out.print("Enter Book Title: ");
                    String title = sc.next();

                    System.out.print("Enter Author Name: ");
                    String author = sc.next();

                    System.out.print("Enter Quantity: ");
                    int quantity = sc.nextInt();

                    service.addBook(new LibraryDTO(0, title, author, quantity));
                    break;

                case 2:
                    System.out.print("Enter Book ID: ");
                    int id = sc.nextInt();

                    LibraryDTO book = service.getBook(id);

                    if (book != null) {
                        System.out.println(
                                book.getId() + " " +
                                book.getTitle() + " " +
                                book.getAuthor() + " " +
                                book.getQuantity()
                        );
                    } else {
                        System.out.println("Book not found");
                    }
                    break;

                case 3:
                    List<LibraryDTO> list = service.getAllBooks();

                    list.forEach(b ->
                            System.out.println(
                                    b.getId() + " " +
                                    b.getTitle() + " " +
                                    b.getAuthor() + " " +
                                    b.getQuantity()
                            )
                    );
                    break;

                case 4:
                    System.out.print("Enter Book ID: ");
                    int uid = sc.nextInt();

                    System.out.print("Enter New Title: ");
                    String newTitle = sc.next();

                    System.out.print("Enter New Author: ");
                    String newAuthor = sc.next();

                    System.out.print("Enter New Quantity: ");
                    int newQty = sc.nextInt();

                    service.updateBook(
                            new LibraryDTO(uid, newTitle, newAuthor, newQty)
                    );
                    break;

                case 5:
                    System.out.print("Enter Book ID: ");
                    int did = sc.nextInt();
                    service.deleteBook(did);
                    break;

                case 6:
                    System.out.println("Exiting Library Management System...");
                    System.exit(0);

                default:
                    System.out.println("Invalid choice");
            }
        }
    }
}
