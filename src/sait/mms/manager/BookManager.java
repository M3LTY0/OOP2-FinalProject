import java.util.ArrayList;
import sait.mms.exceptions.BookNotFoundException;

public class BookManager extends Manager<Book>{

    ArrayList<Book>bookList=db.getBookList();



    public Book addBook(int ISBN, String title, String genre, String publisher, String status, String quality) {
        Book newBook = new Book(ISBN, title, genre, publisher, status, quality);
        bookList.add(newBook);
        db.addBooksql(ISBN, title, genre, publisher, status, quality);
        return newBook;
    }

    public void displayBooks() {
        for (Book iter : bookList) {
            System.out.println(iter.toString());
        }
    }

    public Book searchBooks(int ISBN) throws BookNotFoundException {
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
                System.out.println("Please enter new value for ISBN");
                int newISBN = input.nextInt();
                select.setISBN(newISBN);

                
            } else if (choice == 2) {
                System.out.println("Please enter new value for Title");
                String newTitle = input.nextLine();
                select.setTitle(newTitle);

               
            } else if (choice == 3) {
                System.out.println("Please enter new value for Genre");
                String newGenre = input.nextLine();
                select.setGenre(newGenre);
                
            } else if (choice == 4) {
                System.out.println("Please enter new value for Publisher");
                String newPublisher = input.nextLine();
                select.setPublisher(newPublisher);
            
            } else if (choice == 5) {
                System.out.println("Please enter new value for Status");
                String newStatus = input.nextLine();
                select.setStatus(newStatus);
                
            } else if (choice == 6) {
                System.out.println("Please enter new value for Quality");
                String newQuality = input.nextLine();
                select.setQuality(newQuality);
                
            }

        } while (choice != 0);
        db.updateBooksql(select.getISBN(), select.getTitle(), select.getGenre(), select.getPublisher(), select.getStatus(), select.getQuality());
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
        db.deleteBooksql(ISBN);
    }

}