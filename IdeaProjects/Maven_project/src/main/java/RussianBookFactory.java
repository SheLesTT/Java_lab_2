public class RussianBookFactory implements BookFactory {
    private Names_Excel_Provider provider;
    private Courses_Excel_Provider courses_provider;

    public RussianBookFactory(Names_Excel_Provider provider, Courses_Excel_Provider courses_provider){
        this.provider = provider;
        this.courses_provider = courses_provider;
    }
    @Override
    public Fiction createFiction() {

        GenerateBookData generateData = new GenerateRussianBookData(provider);
        return new RussianFiction(generateData);
    }

    @Override
    public Book createTextbook() {
        String title = RussianTextbookTitle();
        String author = RussianTextbookAuthor();
        return new RussianTextbook(author, title);
    }

    private String RussianTextbookTitle(){
        return courses_provider.getRussian_courses();
    }

    private String RussianTextbookAuthor(){
        String author;
        String name = provider.getM_firstname();
        String second_name = provider.getM_s_last_name();
        author = name + " " + second_name;
        return author;
    }

}

