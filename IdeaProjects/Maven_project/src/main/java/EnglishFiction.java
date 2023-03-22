public class EnglishFiction extends Book implements Fiction {

    GenerateBookData generateData ;

    public EnglishFiction(GenerateBookData generateData) {
        this.generateData = generateData;
    }

    @Override
    public void generate_info() {
       // degree_level = generateData.createLevel();
        author = generateData.createFictionAuthor();
        title = generateData.createFictionTitle();
        //uni = generateData.createUni();
    }
}