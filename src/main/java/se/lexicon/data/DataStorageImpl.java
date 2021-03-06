package se.lexicon.data;


import se.lexicon.model.Person;
import se.lexicon.util.PersonGenerator;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;


/**
 * Create implementations for all methods. I have already provided an implementation for the first method *
 */
public class DataStorageImpl implements DataStorage {

    private static final DataStorage INSTANCE;

    static {
        INSTANCE = new DataStorageImpl();
    }

    private final List<Person> personList;

    private DataStorageImpl(){
        personList = PersonGenerator.getInstance().generate(1000);
    }

    static DataStorage getInstance(){
        return INSTANCE;
    }


    @Override
    public List<Person> findMany(Predicate<Person> filter) {
        List<Person> result = new ArrayList<>();
        for(Person person : personList){
            if(filter.test(person)){
                result.add(person);
            }
        }
        return result;
    }

    @Override
    public Person findOne(Predicate<Person> filter) {
        for(Person p : personList){
            if(filter.test(p)){
                return p;
            }
        }
        return null;
    }

    @Override
    public String findOneAndMapToString(Predicate<Person> filter, Function<Person, String> personToString){
        Person p = findOne(filter);
        return personToString.apply(p);
    }

    @Override
    public List<String> findManyAndMapEachToString(Predicate<Person> filter, Function<Person, String> personToString){
        List<Person> temp = findMany(filter);
        List<String> temp2 = new ArrayList<String>();
        for(Person p : temp ){
            temp2.add(personToString.apply(p));
        }
       return temp2;
    }

    @Override
    public void findAndDo(Predicate<Person> filter, Consumer<Person> consumer){
        List<Person> many = findMany(filter);
        for(Person p : many){
            consumer.accept(p);
        }
    }

    @Override
    public List<Person> findAndSort(Comparator<Person> comparator){
        List<Person> result = new ArrayList<>();
        result = personList;
        result.sort(comparator);
        return result;

    }

    @Override
    public List<Person> findAndSort(Predicate<Person> filter, Comparator<Person> comparator){
        List<Person> result = new ArrayList<>();
        for(Person p : personList){
            if(filter.test(p)){
                result.add(p);
            }
        }
        result.sort(comparator);
       return result;
    }
}