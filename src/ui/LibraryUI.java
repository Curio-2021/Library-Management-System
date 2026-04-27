package ui;

import javax.swing.*;
import service.LibraryService;

public class LibraryUI {

    public static void main(String[] args) {

        JFrame frame = new JFrame("Library Management System");
        frame.setSize(500, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        JLabel heading = new JLabel("Library Management System");
        heading.setBounds(140, 20, 250, 30);

        JButton addBtn = new JButton("Add Book");
        addBtn.setBounds(150, 80, 200, 30);

        JButton viewBtn = new JButton("View Books");
        viewBtn.setBounds(150, 130, 200, 30);

        JButton deleteBtn = new JButton("Delete Book");
        deleteBtn.setBounds(150, 180, 200, 30);

        frame.add(heading);
        frame.add(addBtn);
        frame.add(viewBtn);
        frame.add(deleteBtn);

        frame.setVisible(true);

	LibraryService service = new LibraryService();

addBtn.addActionListener(e -> {

    JFrame addFrame = new JFrame("Add Book");
    addFrame.setSize(400, 300);
    addFrame.setLayout(null);

    JLabel t = new JLabel("Title:");
    t.setBounds(50, 50, 100, 30);
    JTextField titleField = new JTextField();
    titleField.setBounds(150, 50, 150, 30);

    JLabel a = new JLabel("Author:");
    a.setBounds(50, 100, 100, 30);
    JTextField authorField = new JTextField();
    authorField.setBounds(150, 100, 150, 30);

    JLabel q = new JLabel("Quantity:");
    q.setBounds(50, 150, 100, 30);
    JTextField qtyField = new JTextField();
    qtyField.setBounds(150, 150, 150, 30);

    JButton submit = new JButton("Submit");
    submit.setBounds(150, 200, 100, 30);

    addFrame.add(t);
    addFrame.add(titleField);
    addFrame.add(a);
    addFrame.add(authorField);
    addFrame.add(q);
    addFrame.add(qtyField);
    addFrame.add(submit);

    addFrame.setVisible(true);

    submit.addActionListener(ev -> {
        try {
            String title = titleField.getText();
            String author = authorField.getText();
            int qty = Integer.parseInt(qtyField.getText());

            service.addBook(new dto.LibraryDTO(0, title, author, qty));

            JOptionPane.showMessageDialog(addFrame, "Book Added!");
            addFrame.dispose();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(addFrame, "Invalid Input!");
        }
    });
});

viewBtn.addActionListener(e -> {

    JFrame viewFrame = new JFrame("All Books");
    viewFrame.setSize(600, 400);

    String[] columns = {"ID", "Title", "Author", "Quantity"};

    java.util.List<dto.LibraryDTO> books = service.getAllBooks();

    String[][] data = new String[books.size()][4];

    for (int i = 0; i < books.size(); i++) {
        data[i][0] = String.valueOf(books.get(i).getId());
        data[i][1] = books.get(i).getTitle();
        data[i][2] = books.get(i).getAuthor();
        data[i][3] = String.valueOf(books.get(i).getQuantity());
    }

    JTable table = new JTable(data, columns);
    JScrollPane sp = new JScrollPane(table);

    viewFrame.add(sp);
    viewFrame.setVisible(true);
});

deleteBtn.addActionListener(e -> {

    String input = JOptionPane.showInputDialog("Enter Book ID:");

    try {
        int id = Integer.parseInt(input);
        service.deleteBook(id);

        JOptionPane.showMessageDialog(null, "Book Deleted!");

    } catch (Exception ex) {
        JOptionPane.showMessageDialog(null, "Invalid ID!");
    }
});
    }
}