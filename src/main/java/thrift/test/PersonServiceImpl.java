package thrift.test;

import org.apache.thrift.TException;
import thrift.generated.DataException;
import thrift.generated.Person;
import thrift.generated.PersonService;

public class PersonServiceImpl implements PersonService.Iface {

    @Override
    public Person getPersonByUsername(String username) throws DataException, TException {
        System.out.println("got client param: " + username);
        System.out.println("--------------");

        Person person = new Person();
        person.setAge(18);
        person.setUsername(username);
        person.setMarried(false);

        return person;
    }

    @Override
    public void savePerson(Person person) throws DataException, TException {
        System.out.println("got client param: ");

        System.out.println(person);
    }
}
