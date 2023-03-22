public class PersonFactory {
    Names_Excel_Provider provider;

    public PersonFactory(Names_Excel_Provider provider){
        this.provider = provider;
    }
    public Person createPerson(String type){
        Person person = null;
        String gender = generateGender();
        String middle_name = generateMiddleName(gender);
        String name = generateName(gender);

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
        return "male";
    }
    private String generateSecondName(String gender){
        String second_name;
        if (gender.equals("male")){
           second_name = provider.getM_s_last_name();
        }else {
            second_name = provider.getF_s_last_name();
        }
        return second_name;
    }
    private String generateMiddleName(String gender){

        String name;
        if(gender.equals("male")){
            name = provider.getM_middle_name();
        } else {name = provider.getF_middle_name();}
        return name;
    }

    private String generateStudentSecondName(String gender){
        String name;
        if(gender.equals("male")){
            name =provider.getM_s_last_name();
        } else {
            name = provider.getF_s_last_name();
        }
        return name;
    }
    private  String generateName(String gender){
        String name;
        if (gender.equals("male")){
            name = provider.getM_firstname();
        }else {
            name = provider.getF_firstname();
        }
        return name;
    }

}
