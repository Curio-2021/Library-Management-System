---
title:
created: 25th Apr 2026, 08:12
aliases:
tags:
cssclasses:
link:
---

### Package Fix

**DTO**
```Java
package dto;
```

**DAO**
```Java
package dao;
import dto.LibraryDTO;
```

**Service**
```Java
package service;
import dao.*;
import dto.LibraryDTO;
```

**MainApp (optional)**
```Java
package ui; // or remove later
```

---
### First GUI Window
Create ui/LibraryUI.java:

```Java
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
```

---
### Commands to Compile and Run Java Programs
Run these all from the root dir of the project, not src
 
```bash
$ find . -name "*.class" -delete
```

```bash
$ tree.com //f //a
Folder PATH listing for volume Windows-SSD
Volume serial number is 3036-E42B
C:.
|   .classpath
|   .gitignore
|   .project
|   README.md
|
\---src
    |   MainApp.java
    |   mysql-connector-j-9.7.0.jar
    |
    +---dao
    |       LibraryDAO.java
    |       LibraryDAOImpl.java
    |
    +---dto
    |       LibraryDTO.java
    |
    +---service
    |       LibraryService.java
    |
    \---ui
            LibraryUI.java
```

```bash
$ javac -d . -cp src/mysql-connector-j-9.7.0.jar src/*/*.java
```

```bash
$ tree.com //f //a
Folder PATH listing for volume Windows-SSD
Volume serial number is 3036-E42B
C:.
|   .classpath
|   .gitignore
|   .project
|   README.md
|
+---dao
|       LibraryDAO.class
|       LibraryDAOImpl.class
|
+---dto
|       LibraryDTO.class
|
+---service
|       LibraryService.class
|
+---src
|   |   MainApp.java
|   |   mysql-connector-j-9.7.0.jar
|   |
|   +---dao
|   |       LibraryDAO.java
|   |       LibraryDAOImpl.java
|   |
|   +---dto
|   |       LibraryDTO.java
|   |
|   +---service
|   |       LibraryService.java
|   |
|   \---ui
|           LibraryUI.java
|
\---ui
        LibraryUI.class
```

```bash
$ java -cp ".;src/mysql-connector-j-9.7.0.jar" ui.LibraryUI
```

---
### MySQL Connector
- Download it from: <https://dev.mysql.com/downloads/connector/j/>
- Choose:
	- Platform Independent
	- Download ZIP
- Extract: ==mysql-connector-j-9.7.0.jar==
- Move it here:
```
Library-Management-System/
 ├── src/
 ├── mysql-connector-j-9.7.0.jar   👈 HERE
```

---
### MySQL Installer
- Download it from: <https://dev.mysql.com/downloads/installer/>

- Create Database
```SQL
CREATE DATABASE rnsitdb;
USE rnsitdb;
```

- Create Table
```SQL
CREATE TABLE books (
    id INT PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(100),
    author VARCHAR(100),
    quantity INT
);
```

- Update Your Java Code
```SQL
private static final String URL = "jdbc:mysql://localhost:3306/rnsitdb";
private static final String USER = "root";
private static final String PASS = "your_password";
```