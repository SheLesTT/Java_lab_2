import java.util.ArrayList;

public abstract class Person {
    String name;
    String second_name;
    String third_name;
    ArrayList<Book> books;



    public String getName() {
        return name;
    }

    public String getSecond_name() {
        return second_name;
    }

    public ArrayList<Book> getBooks() {
        return books;
    }

    public void setBooks(ArrayList<Book> books) {
        this.books = books;
    }

    public String getThird_name() {
        return third_name;
    }
}
