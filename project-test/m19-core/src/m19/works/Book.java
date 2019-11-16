package m19.works;

public class Book extends Work {
    private String _author;
    private String _ISBN;

    public Book(int id, String title, String author, int price, String category, String ISBN, int copies) {
        super(id, title, price, category, copies);
        _author = author;
        _ISBN = ISBN;
    }

    public String getAuthor() {
        return _author;
    }

    public String getISBN() {
        return _ISBN;
    }

    @Override
    public String toString() {
        return super.toString() + " - " + "BOOK - " + getWorkID() + " - " + getPrice() + " - " + getCategory().getDisplayed() + " - " + _author + " - " + _ISBN; 
    }
}