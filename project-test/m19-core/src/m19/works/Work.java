package m19.works;

import java.io.Serializable;

public abstract class Work implements Serializable {
    //atributes
    private int _workID;
    private String _title;
    private int _price;
    private int _totalCopies;
    private int _libraryCopies;
    private Category _category;

    public enum Category {
        FICTION(true, "Ficção"),
        REFERENCE(false, "Referência"),
        SCITECH(true, "Técnica e Científica");

        private boolean _requestable;
        private String _displayed;

        Category(boolean requestable, String displayed) {
            _requestable = requestable;
            _displayed = displayed;
        }

        public boolean getRequestable() {
            return _requestable;
        }

        public String getDisplayed() {
            return _displayed;
        }

    }

    public Work(int id, String title, int price, String category, int totalCopies) {
        _workID = id;
        _title = title;
        _price = price;
        _category = Category.valueOf(category);
        _totalCopies = totalCopies;
        _libraryCopies = _totalCopies;
    }

    public int getWorkID() {
        return _workID;
    }

    public String getTitle() {
        return _title;
    }

    public int getPrice() {
        return _price;
    }

    public int getLibraryCopies() {
        return _libraryCopies;
    }

    public Category getCategory() {
        return _category;
    }

    @Override 
    public String toString() {
        return _workID + " - " + _libraryCopies + " de " + _totalCopies + " - " + "%s - " + _title + " - " + _price + " - " + getCategory().getDisplayed() + " - ";
    }
}
