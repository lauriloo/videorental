package ee.meriloo.clients;

/**
 * Created by Lauri on 10.10.2015.
 */
public class Person {

    private String name;



    Person(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString(){
        return name;
    }
}
