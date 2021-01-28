package se.lexicon;

import com.sun.deploy.util.StringUtils;
import se.lexicon.data.DataStorage;
import se.lexicon.model.Gender;
import se.lexicon.model.Person;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;


public class Exercises {

    private final static DataStorage storage = DataStorage.INSTANCE;

    /*
       1.	Find everyone that has firstName: “Erik” using findMany().
    */
    public static void exercise1(String message){
        System.out.println(message);
        //Write your code here
        
        List<Person> temp = storage.findMany(person -> person.getFirstName().equals("Erik"));
        temp.stream().forEach(person -> System.out.println(person.toString()));

        System.out.println("----------------------");
    }

    /*
        2.	Find all females in the collection using findMany().
     */
    public static void exercise2(String message){
        System.out.println(message);
        //Write your code here
        List<Person> temp = storage.findMany(person -> person.getGender().equals(Gender.FEMALE));
        temp.stream().forEach(person -> System.out.println(person.toString()));

        System.out.println("----------------------");
    }

    /*
        3.	Find all who are born after (and including) 2000-01-01 using findMany().
     */
    public static void exercise3(String message){
        System.out.println(message);
        //Write your code here
        List<Person> temp = storage.findMany(person -> person.getBirthDate().isEqual(LocalDate.parse("2000-01-01")) || person.getBirthDate().isAfter(LocalDate.parse("2000-01-01")));
        temp.stream().forEach(person -> System.out.println(person.toString()));

        System.out.println("----------------------");
    }

    /*
        4.	Find the Person that has an id of 123 using findOne().
     */
    public static void exercise4(String message){
        System.out.println(message);
        //Write your code here
        Person temp = storage.findOne(person -> person.getId() == 123);

        System.out.println(temp.toString());
        System.out.println("----------------------");

    }

    /*
        5.	Find the Person that has an id of 456 and convert to String with following content:
            “Name: Nisse Nilsson born 1999-09-09”. Use findOneAndMapToString().
     */
    public static void exercise5(String message){
        System.out.println(message);
        //Write your code here
        String temp = storage.findOneAndMapToString(person -> person.getId() == 456, p -> "Name: " + p.getFirstName()
                + " " + p.getLastName() + " born " + p.getBirthDate());

        System.out.println(temp);
        System.out.println("----------------------");
    }

    /*
        6.	Find all male people whose names start with “E” and convert each to a String using findManyAndMapEachToString().
     */
    public static void exercise6(String message){
        System.out.println(message);
        //Write your code here
        List<String> temp = storage.findManyAndMapEachToString(person -> person.getGender().equals(Gender.MALE) && person.getFirstName().startsWith("E")
                , person -> person.getFirstName() + " " + person.getGender());
        temp.forEach(p -> System.out.println(p));
        System.out.println("----------------------");
    }

    /*
        7.	Find all people who are below age of 10 and convert them to a String like this:
            “Olle Svensson 9 years”. Use findManyAndMapEachToString() method.
     */
    public static void exercise7(String message){
        System.out.println(message);
        //Write your code here
        List<String> temp = storage.findManyAndMapEachToString(person -> person.getBirthDate().isAfter(LocalDate.now().minusYears(10)),
                person -> person.getFirstName() + " " + person.getLastName() + " \t\t" + (LocalDate.now().getYear() - person.getBirthDate().getYear()) + " years");
        temp.forEach(p -> System.out.println(p));

        System.out.println("----------------------");
    }

    /*
        8.	Using findAndDo() print out all people with firstName “Ulf”.
     */
    public static void exercise8(String message){
        System.out.println(message);
        //Write your code here
        storage.findAndDo(person -> person.getFirstName().equals("Ulf"),
                person -> System.out.println(person.toString()));

        System.out.println("----------------------");
    }

    /*
        9.	Using findAndDo() print out everyone who have their lastName contain their firstName.
     */
    public static void exercise9(String message){
        System.out.println(message);
        //Write your code here
        storage.findAndDo(person -> person.getLastName().toLowerCase().contains(person.getFirstName().toLowerCase()),
                person -> System.out.println(person.toString()));

        System.out.println("----------------------");
    }

    /*
        10.	Using findAndDo() print out the firstName and lastName of everyone whose firstName is a palindrome.
     */
    public static void exercise10(String message){
        System.out.println(message);
        //Write your code here


        storage.findAndDo(person -> person.getFirstName().toLowerCase().equals(reverser(person.getFirstName().toLowerCase())) ,
            person -> System.out.println(person.toString()));



        System.out.println("----------------------");
    }

    /*
        11.	Using findAndSort() find everyone whose firstName starts with A sorted by birthdate.
     */
    public static void exercise11(String message){
        System.out.println(message);
        //Write your code here
        storage.findAndSort(person -> person.getFirstName().startsWith("A"),
                Comparator.comparing(p -> p.getBirthDate().toString()));

        System.out.println("----------------------");
    }

    /*
        12.	Using findAndSort() find everyone born before 1950 sorted reversed by lastest to earliest.
     */
    public static void exercise12(String message){
        System.out.println(message);
        //Write your code here

        System.out.println("----------------------");
    }

    /*
        13.	Using findAndSort() find everyone sorted in following order: lastName > firstName > birthDate.
     */
    public static void exercise13(String message){
        System.out.println(message);
        //Write your code here

        System.out.println("----------------------");
    }

    public static String reverser(String in){
        StringBuilder sb = new StringBuilder(in);
        String ss = sb.reverse().toString();
        return ss;
    }
}
