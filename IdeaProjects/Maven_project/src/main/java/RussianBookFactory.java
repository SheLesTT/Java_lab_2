public class RussianBookFactory implements BookFactory {
    private Names_Excel_Provider provider;


    public RussianBookFactory(Names_Excel_Provider provider){
        this.provider = provider;

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
        return provider.getRussian_courses();
    }

    private String RussianTextbookAuthor(){
        String author;
        String name = provider.getM_firstname();
        String second_name = provider.getM_s_last_name();
        author = name + " " + second_name;
        return author;
    }

}

