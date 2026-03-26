import java.util.ArrayList;
import java.util.Scanner;

public class BookManager {

    private ArrayList<Book> bookList;
    public Scanner input = new Scanner(System.in);


    public Book addBook(int ISBN, String title, String genre, String publisher, String status, String quality){
        Book newBook = new Book(ISBN, title, genre, publisher, status, quality);
        bookList.add(newBook);
        return newBook;
    }

    public void displayBooks(){
        for(Book iter: bookList){
            iter.toString();
        }
    }

    public Book searchBooks(int ISBN){
        for(Book iter:bookList){
            if(iter.getISBN()==ISBN)
                return iter;
        }

        return null;//TODO: book not found exception
    }

    public void updateBook(int ISBN){
        Book select = searchBooks(ISBN);
        int choice = 50;
        do{
            System.out.println("What would you like to change:");
            System.out.println("1:ISBN");
            System.out.println("2:Title");
            System.out.println("3:Genre");
            System.out.println("4:Publisher");
            System.out.println("5:Status");
            System.out.println("6:Quality");
            System.out.println("0:Quit");

            //TODO: user input error traps
            choice=input.nextInt();
            if(choice==1){
                System.out.println("Please enter new value for ISBN");
                int newISBN=input.nextInt();
                select.setISBN(newISBN);
            }
            else if(choice==2){
                System.out.println("Please enter new value for Title");
                String newTitle=input.nextLine();
                select.setTitle(newTitle);
            }
            else if(choice==3){
                System.out.println("Please enter new value for Genre");
                String newGenre=input.nextLine();
                select.setGenre(newGenre);
            }
            else if(choice==4){
                System.out.println("Please enter new value for Publisher");
                String newPublisher=input.nextLine();
                select.setPublisher(newPublisher);
            }
            else if(choice==5){
                System.out.println("Please enter new value for Status");
                String newStatus=input.nextLine();
                select.setStatus(newStatus);
            }
            else if(choice==6){
                System.out.println("Please enter new value for Quality");
                String newQuality=input.nextLine();
                select.setQuality(newQuality);
            }
            
        } while (choice!=0);



    }

    public void deleteBook(int ISBN){
        Book select = searchBooks(ISBN);
        bookList.remove(select);
    }




}