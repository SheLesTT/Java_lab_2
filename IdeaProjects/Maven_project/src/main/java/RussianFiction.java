public class RussianFiction extends Book implements Fiction {

    GenerateBookData generateData;

    public RussianFiction(GenerateBookData generateData) {
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
