public abstract class Book implements Bookable {

    String author;
    String title;
    String uni;
    String degree_level;


    public String getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

    public String getUni() {
        return uni;
    }

    public String getDegree_level() {
        return degree_level;
    }
}
