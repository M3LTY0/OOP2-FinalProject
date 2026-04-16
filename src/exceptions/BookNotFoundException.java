package exceptions;

public class BookNotFoundException extends ManagerException {

    public BookNotFoundException(String message){
        super(message);
    }
    
}
