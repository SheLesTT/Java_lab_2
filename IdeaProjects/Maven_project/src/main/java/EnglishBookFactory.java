

import java.util.Random;

public class EnglishBookFactory implements BookFactory {

    Names_Excel_Provider provider;

    public EnglishBookFactory(Names_Excel_Provider course_provider){
        this.provider = course_provider;
    }

    @Override
    public Fiction createFiction() {

        GenerateBookData generateData = new GenerateEnglishBookData(this.provider);
        return new EnglishFiction(generateData);
    }

    @Override
    public Book createTextbook() {
        String title = EnglishTextbookTitle();
        String author = EnglishTextbookAuthor();
        String uni = EnglishTextbookUni();
        String level = EnglishTextbookLevel();
        return new EnglishTextbook(author, title, uni, level);
    }

    private String EnglishTextbookTitle(){
        return provider.getEnglish_courses();
    }

    private String EnglishTextbookAuthor(){
        return provider.getEnglish_professors();
    }
    private  String EnglishTextbookUni(){
        return provider.getEnglish_uni();
    }

    private String EnglishTextbookLevel(){
        Random random = new Random();
        int n = random.nextInt(2);
        String level;
        if(n==0){
            level = "bachelor";
        }else {
            level = "masters";
        }
        return level;
    }
}
