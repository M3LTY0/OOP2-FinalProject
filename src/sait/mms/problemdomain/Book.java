public class Book {

    private int ISBN;
    private String title;
    private String genre;
    private String publisher;
    private String status;
    private String quality;

    
    public Book(int ISBN, String title, String genre, String publisher, String status, String quality) {
        this.ISBN = ISBN;
        this.title = title;
        this.genre = genre;
        this.publisher = publisher;
        this.status = status;
        this.quality = quality;
    }


    public int getISBN() {
        return ISBN;
    }
    public void setISBN(int iSBN) {
        ISBN = iSBN;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getGenre() {
        return genre;
    }
    public void setGenre(String genre) {
        this.genre = genre;
    }
    public String getPublisher() {
        return publisher;
    }
    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public String getQuality() {
        return quality;
    }
    public void setQuality(String quality) {
        this.quality = quality;
    }


    @Override
    public String toString() {
        return "Book [ISBN=" + ISBN + ", title=" + title + ", genre=" + genre + ", publisher=" + publisher + ", status="
                + status + ", quality=" + quality + "]";
    }

    

}