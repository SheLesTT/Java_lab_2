
import java.util.Random;
public class GenerateEnglishBookData  implements GenerateBookData {

    Names_Excel_Provider provider;
    GenerateEnglishBookData(Names_Excel_Provider provider){
        this.provider = provider;
    }
    @Override
    public String createFictionAuthor() {
    return provider.getEnglish_professors();
    }


    @Override
    public String createFictionTitle() {

            String[] KEYWORDS = {"Love", "Heart", "Passion", "Desire", "Destiny", "Dreams", "Secrets", "Mystery", "Magic", "Adventure", "Forgiveness", "Betrayal", "Courage", "Hope", "Fate", "Surrender", "Whispers", "Shadows", "Enchantment", "Reflections"};
            String[] GENRES = {"Romance", "Poem", "Adventure"};
        Random random = new Random();
        String keyword1 = KEYWORDS[random.nextInt(KEYWORDS.length)];
        String keyword2 = KEYWORDS[random.nextInt(KEYWORDS.length)];
        String genre = GENRES[random.nextInt(GENRES.length)];
        while (keyword2.equals(keyword1)) {
            keyword2 = KEYWORDS[random.nextInt(KEYWORDS.length)];
        }

        String title = keyword1 + "'s " + keyword2 + " " +genre;

        return title;

    }





}
