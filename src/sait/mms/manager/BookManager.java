import java.util.ArrayList;
import java.util.Scanner;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import sait.mms.exceptions.BookNotFoundException;

public class BookManager {

    public Scanner input = new Scanner(System.in);
    DatabaseConn db = new DatabaseConn();
    ArrayList<Book>bookList=db.getBookList();


    public Book addBook(int ISBN, String title, String genre, String publisher, String status, String quality) {
        Book newBook = new Book(ISBN, title, genre, publisher, status, quality);
        bookList.add(newBook);

        String sql = "INSERT INTO books(ISBN, title, genre, publisher, status, quality) VALUES (?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement pst = db.prepared(sql);

            pst.setInt(1, ISBN);
            pst.setString(2, title);
            pst.setString(3, genre);
            pst.setString(4, publisher);
            pst.setString(5, status);
            pst.setString(6, quality);

            int count = db.updated(pst);
            // remove
            System.out.println(count + " row inserted");
        } catch (SQLException e) {
            System.out.println("Error while executing statement\n" + sql + "\n" + e.getMessage());
        }
        return newBook;
    }

    public void displayBooks() {
        for (Book iter : bookList) {
            iter.toString();
        }
    }

    public Book searchBooks(int ISBN) throws BookNotFoundException {
        String sql = "SELECT * from books WHERE ISBN = ?";
        try {
            PreparedStatement pst = db.prepared(sql);
            pst.setInt(1, ISBN);
            ResultSet rs = db.query(pst);
            if (rs.next()) {
                int currISBN = rs.getInt("ISBN");
                String title = rs.getString("title");
                String genre = rs.getString("genre");
                String publisher = rs.getString("publisher");
                String status = rs.getString("status");
                String quality = rs.getString("quality");
                Book book = new Book(currISBN, title, genre, publisher, status, quality);
                System.out.println(book);
            } else {
                System.out.println("No student with ID " + ISBN);
            }
        } catch (SQLException e) {
            System.out.println("Error while retrieving student's data: " + e.getMessage());
        }
        for (Book iter : bookList) {
            if (iter.getISBN() == ISBN)
                return iter;
        }
        throw new BookNotFoundException("No book found with ISBN: " + ISBN);

    }

    public void updateBook(int ISBN) {
        Book select = null;
        try {
            select = searchBooks(ISBN);

        } catch (BookNotFoundException e) {
            System.out.println(e.getMessage());
            return;
        }
        int choice = 50;
        do {
            System.out.println("What would you like to change:");
            System.out.println("1:ISBN");
            System.out.println("2:Title");
            System.out.println("3:Genre");
            System.out.println("4:Publisher");
            System.out.println("5:Status");
            System.out.println("6:Quality");
            System.out.println("0:Quit");

            // TODO: user input error traps
            choice = input.nextInt();
            if (choice == 1) {
                // temporary memory = arraylist
                System.out.println("Please enter new value for ISBN");
                int newISBN = input.nextInt();
                select.setISBN(newISBN);

                // long term memory = db
                String sql = "UPDATE books SET ISBN = ? WHERE ISBN = ?";
                try {
                    PreparedStatement pst = db.prepared(sql);
                    pst.setInt(1, newISBN);
                    pst.setInt(2, ISBN);
                    int count = db.updated(pst);

                    if (count == 0) {
                        System.out.println("ISBN not updated");
                    } else {
                        System.out.println(count + " ISBN updated");
                    }
                } catch (SQLException e) {
                    System.out.println("Error while executing statement \n" + sql + "\n" + e.getMessage());
                }
            } else if (choice == 2) {
                System.out.println("Please enter new value for Title");
                String newTitle = input.nextLine();
                select.setTitle(newTitle);

                String sql = "UPDATE books SET title = ? WHERE ISBN = ?";
                try {
                    PreparedStatement pst = db.prepared(sql);
                    pst.setString(1, newTitle);
                    pst.setInt(2, ISBN);
                    int count = db.updated(pst);

                    if (count == 0) {
                        System.out.println("title not updated");
                    } else {
                        System.out.println(count + "title updated");
                    }
                } catch (SQLException e) {
                    System.out.println("Error while executing statement \n" + sql + "\n" + e.getMessage());
                }
            } else if (choice == 3) {
                System.out.println("Please enter new value for Genre");
                String newGenre = input.nextLine();
                select.setGenre(newGenre);

                String sql = "UPDATE books SET genre = ? ISBN = ?";
                try {
                    PreparedStatement pst = db.prepared(sql);
                    pst.setString(1, newGenre);
                    pst.setInt(2, ISBN);
                    int count = db.updated(pst);

                    if (count == 0) {
                        System.out.println("genre not updated");
                    } else {
                        System.out.println(count + "genre updated");
                    }
                } catch (SQLException e) {
                    System.out.println("Error while executing statement \n" + sql + "\n" + e.getMessage());
                }
            } else if (choice == 4) {
                System.out.println("Please enter new value for Publisher");
                String newPublisher = input.nextLine();
                select.setPublisher(newPublisher);

                String sql = "UPDATE books SET title = ? WHERE ISBN = ?";
                try {
                    PreparedStatement pst = db.prepared(sql);
                    pst.setString(1, newPublisher);
                    pst.setInt(2, ISBN);
                    int count = db.updated(pst);

                    if (count == 0) {
                        System.out.println("publisher not updated");
                    } else {
                        System.out.println(count + "publisher updated");
                    }
                } catch (SQLException e) {
                    System.out.println("Error while executing statement \n" + sql + "\n" + e.getMessage());
                }
            } else if (choice == 5) {
                System.out.println("Please enter new value for Status");
                String newStatus = input.nextLine();
                select.setStatus(newStatus);

                String sql = "UPDATE books SET title = ? WHERE ISBN = ?";
                try {
                    PreparedStatement pst = db.prepared(sql);
                    pst.setString(1, newStatus);
                    pst.setInt(2, ISBN);
                    int count = db.updated(pst);

                    if (count == 0) {
                        System.out.println("status not updated");
                    } else {
                        System.out.println(count + "status updated");
                    }
                } catch (SQLException e) {
                    System.out.println("Error while executing statement \n" + sql + "\n" + e.getMessage());
                }
            } else if (choice == 6) {
                System.out.println("Please enter new value for Quality");
                String newQuality = input.nextLine();
                select.setQuality(newQuality);

                String sql = "UPDATE books SET quality = ? WHERE ISBN = ?";

                try {
                    PreparedStatement pst = db.prepared(sql);
                    pst.setString(1, newQuality);
                    pst.setInt(2, ISBN);
                    int count = db.updated(pst);

                    if (count == 0) {
                        System.out.println("quality not updated");
                    } else {
                        System.out.println(count + " quality updated");
                    }
                } catch (SQLException e) {
                    System.out.println("Error while executing statement \n" + sql + "\n" + e.getMessage());
                }
            }

        } while (choice != 0);
    }

    public void deleteBook(int ISBN) {
        Book select = null;
        try {
            select = searchBooks(ISBN);
        } catch (BookNotFoundException e) {
            System.out.println(e.getMessage());
            return;
        }
        bookList.remove(select);

        String sql = "DELETE FROM books WHERE ISBN = ?";

        try {
            PreparedStatement pst = db.prepared(sql);
            pst.setInt(1, ISBN);
            int count = db.updated(pst);
            if (count == 0) {
                System.out.println("No such ISBN");
            } else {
                System.out.println(count + " book deleted");
            }
        } catch (SQLException e) {
            System.out.println("Error while executing statement\n" + sql + "\n" + e.getMessage());
        }
    }

}