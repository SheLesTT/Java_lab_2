

import java.util.Random;

public class EnglishBookFactory implements BookFactory {

    Courses_Excel_Provider course_provider;

    public EnglishBookFactory(Courses_Excel_Provider course_provider){
        this.course_provider = course_provider;
    }

    @Override
    public Fiction createFiction() {

        GenerateBookData generateData = new GenerateEnglishBookData(this.course_provider);
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
        return course_provider.getEnglish_courses();
    }

    private String EnglishTextbookAuthor(){
        return course_provider.getEnglish_professors();
    }
    private  String EnglishTextbookUni(){
        return course_provider.getEnglish_uni();
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
