
import java.util.Random;
public class PersonFactory {
    private Names_Excel_Provider provider;

    public PersonFactory(Names_Excel_Provider provider){
        this.provider = provider;
    }
    public Person createPerson(String type){
        Person person = null;
        String gender = generateGender();
        String middle_name = generateMiddleName(gender);
        String name = generateName(gender);
        System.out.println(gender);

        if (type.equals("professor")){
            String second_name = generateSecondName(gender);
            person = new Professor(name, second_name,middle_name);

        } else if(type.equals("student")){
            String second_name = generateStudentSecondName(gender);
            person = new Student(name, second_name, middle_name);
        }
        return person;
    }

    private String generateGender(){
        Random random = new Random();
         int n = random.nextInt(2);
        if( n== 0) {
            return "female";

        }else {
            return "male";}
    }
    private String generateSecondName(String gender){
        String second_name;
        System.out.println(gender);
        if (gender.equals("male")){
           second_name = provider.getM_teacher_second_name();
           System.out.println("M_sn" + second_name);
        }else {
            second_name = provider.getF_teacher_second_name();
            System.out.println("F_sn" + second_name);
        }
        return second_name;
    }
    private String generateMiddleName(String gender){
        System.out.println(gender);
        String name;
        if(gender.equals("male")){
            name = provider.getM_middle_name();
            System.out.println("M_mn" + name);
        } else {name = provider.getF_middle_name();
            System.out.println("F_mn" + name);}

        return name;
    }

    private String generateStudentSecondName(String gender){
        System.out.println(gender);
        String name;
        if(gender.equals("male")){
            name =provider.getM_s_last_name();
        } else {
            name = provider.getF_s_last_name();
        }
        return name;
    }
    private  String generateName(String gender){
        System.out.println(gender);
        String name;
        if (gender.equals("male")){
            name = provider.getM_firstname();
        }else {
            name = provider.getF_firstname();
        }
        return name;
    }

}
