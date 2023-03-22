import java.util.ArrayList;
import java.util.Random;


public class DataManipulation {

    ArrayList<Book> Books;
    ArrayList<Person> Persons;

    public ArrayList<Person> DataManipulation(Names_Excel_Provider provider) {
        GeneratePersons(provider);
        GenerateData(provider);
        GiveBooksToPeople(Persons, Books);
        Person person = Persons.get(1);
        System.out.println(person.getBooks());
        ArrayList<Book> books = person.getBooks();
        for (Book book : books) {
            System.out.println(book.getTitle());
        }
        return Persons;
    }

    public void GeneratePersons(Names_Excel_Provider provider) {
        PersonFactory factory = new PersonFactory(provider);
        ArrayList<Person> persons = new ArrayList<Person>();
        int i = 0;
        while (i < 100) {
            if (i % 2 == 0) {
                Person person = factory.createPerson("professor");
                persons.add(person);
            } else {

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

    public void GenerateData(Names_Excel_Provider provider) {

        BookFactory factory;
        ArrayList<Book> BookList = new ArrayList<Book>();
        int i = 0;
        while (i < 100) {
            int R = i - (int) Math.floor(i / 4) * 4;
            switch (R) {
                case 0: {

                    factory = new RussianBookFactory(provider);
                    Fiction book = factory.createFiction();
                    book.generate_info();
                    BookList.add((Book) book);
                    break;
                }
                case 1: {

                    factory = new RussianBookFactory(provider);
                    BookList.add(factory.createTextbook());
                    break;
                }

                case 2: {
                    factory = new EnglishBookFactory(provider);
                    BookList.add(factory.createTextbook());
                    break;
                }

                case 3: {
                    factory = new EnglishBookFactory(provider);
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
        System.out.println((((Book) book)).getTitle());

        System.out.println(((Book) book).getAuthor());

    }

    public void GiveBooksToPeople(ArrayList<Person> persons, ArrayList<Book> books) {
        Random random = new Random();
        for (Person person : persons) {
            ArrayList<Book> person_books = new ArrayList<Book>();
            int book_number = random.nextInt(8) + 3;
            for (int i = 0; i <= book_number; i++) {

                int n = random.nextInt(books.size());
                while (person_books.contains(books.get(n))){
                    n = random.nextInt(books.size());
                }
                person_books.add(books.get(n));
            }
            person.setBooks(person_books);

        }
    }
}
