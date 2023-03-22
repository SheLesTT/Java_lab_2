import java.util.ArrayList;
import java.util.Random;
import java.util.HashMap;

public class DataManipulation {

    ArrayList<Book> Books;
    ArrayList<Person> Persons;

    public ArrayList<Person>  DataManipulation(Courses_Excel_Provider course_provider,Names_Excel_Provider provider) {
        GeneratePersons(provider);
        GenerateData(course_provider, provider);
        GiveBooksToPeople(Persons, Books);
        Person person = Persons.get(1);
        System.out.println(person.getBooks());
        ArrayList<Book> books= person.getBooks();
        for (Book book:books){
            System.out.println(book.getTitle());
        }
        return Persons;
    }

    public void GeneratePersons(Names_Excel_Provider provider){
        PersonFactory factory = new PersonFactory(provider);
        ArrayList<Person> persons = new ArrayList<Person>();
        int i= 0;
        while (i < 100){
            if(i %2 == 0){
                Person person = factory.createPerson("professor");
                persons.add(person);
            }else {

                Person person = factory.createPerson("student");
                persons.add(person);
            }
            i++;
        }
        Persons = persons;
//        for(Person person: Persons) {
//
//            System.out.println(person.getSecond_name());
//        }
    }
    public  void GenerateData(Courses_Excel_Provider course_provider, Names_Excel_Provider provider) {

        BookFactory factory;
        ArrayList<Book> BookList = new ArrayList<Book>();
        int i = 0;
        while (i < 100) {
            int R = i - (int)Math.floor(i / 4) * 4;
            switch (R) {
                case 0: {

                    factory = new RussianBookFactory(provider, course_provider);
                    Fiction book = factory.createFiction();
                    book.generate_info();
                    BookList.add((Book) book);
                    break;
                }
                case 1: {

                    factory = new RussianBookFactory(provider, course_provider);
                    BookList.add(factory.createTextbook());
                    break;
                }

                case 2: {
                    factory = new EnglishBookFactory(course_provider);
                    BookList.add(factory.createTextbook());
                    break;
                }

                case 3: {
                    factory = new EnglishBookFactory(course_provider);
                    Fiction book = factory.createFiction();
                    book.generate_info();
                    BookList.add((Book) book);
                    break;
                }
            }
            i += 1;
        }
     Books = BookList;
        Bookable book = Books.get(0);
        System.out.println(((Book) book).getTitle());
//        factory = new EnglishBookFactory();
//        EnglishTextbook book =  (EnglishTextbook) factory.createTextbook();
//        book.generate_info();
        System.out.println(((Book) book).getAuthor());

    }

    public void GiveBooksToPeople(ArrayList<Person> persons,ArrayList<Book> books ){
        Random random = new Random();
        for(Person person: persons){
            ArrayList<Book> person_books = new ArrayList<Book>();
            int book_number = random.nextInt(8) + 3;
            for (int i =0; i <= book_number; i++){
                int n = random.nextInt(books.size());
                person_books.add(books.get(n));
            }
            person.setBooks(person_books);

        }
    }
}
