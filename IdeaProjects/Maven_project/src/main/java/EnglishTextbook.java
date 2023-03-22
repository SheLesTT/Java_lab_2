public class EnglishTextbook extends Book implements TextBook {

    public EnglishTextbook(String author, String title, String uni, String degree_level){
        this.title = title;
        this.author = author;
        this.uni = uni;
        this.degree_level = degree_level;
    }
}
