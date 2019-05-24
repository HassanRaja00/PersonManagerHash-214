import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.HashMap;
public class PersonDataHashMap {
    /**
     * Hashmap data has key and String and stores Person
     * keys array stores the keys to access the data from hashmap
     * numKeys says how many keys are in the keys array
     */
    private HashMap<String, Person> data;
    private String[] keys; //used to store keys
    private int numKeys; //number of keys

    /**
     * Constructor for person data Hashmap
     */
    public PersonDataHashMap(){
        this.data = new HashMap<String, Person>();
        this.keys = new String[100];
        this.numKeys = 0;
    }

    /**
     * @return the Hashmap of data
     */
    public HashMap<String, Person> getData(){
        return this.data;
    }

    /**
     * @return String of keys
     */
    public String[] getKeys(){
        return this.keys;
    }

    /**
     * @return the number of keys that are stored
     */
    public int getNumKeys() {
        return this.numKeys;
    }

    /**
     * adds to the hashmap from a file
     * @param location the name of the file
     * @return the person data manager for main method
     * @throws IllegalArgumentException if the file does not exist
     */
    public static PersonDataHashMap buildFromFile(String location) throws IllegalArgumentException{
        PersonDataHashMap manager = new PersonDataHashMap();
        String inputLine=null;
        try{
            Scanner reader = new Scanner(new File(location));
            reader.nextLine(); //skips the first line with titles
            while(reader.hasNextLine()){
                inputLine = reader.nextLine();
                inputLine.trim();
                inputLine.replaceAll("\"","");
                String[] splitter = inputLine.split(",");
                String personName = splitter[0].replaceAll("\"","");
                String personGender = splitter[1].replaceAll("\"","").trim();
                int personAge = Integer.parseInt(splitter[2].trim().trim());
                double personHeight = Double.parseDouble(splitter[3].trim().trim());
                double personWeight = Double.parseDouble(splitter[4].trim().replaceAll("\\s",""));
                if(splitter.length==5){
                    Person temp = new Person(personName, personGender, personAge, personHeight, personWeight);
                    manager.add(temp.getName(), temp);
                }
            }

            reader.close();
            System.out.println("Loading...\nPerson data loaded successfully!");

        } catch(FileNotFoundException ex){
            throw new IllegalArgumentException("Sorry the file does not exist!");
        } catch(PersonAlreadyExistsException ex2){
            System.out.println(ex2.getMessage());
        }
        return manager;
    }

    /**
     * adds to the hashmap
     * @param personName String name that is the key for hashmap
     * @param newPerson Person object gets added into hashmap
     * @throws PersonAlreadyExistsException if the person with the same attributes exists
     */
    public void add(String personName, Person newPerson) throws PersonAlreadyExistsException{
        if(numKeys>0) {
            for (int i = 0; i < numKeys; i++) {
                String getName =keys[i];
                Person temp = data.get(getName);
                if (temp.getName().equals(newPerson.getName()) && temp.getGender().equals(newPerson.getGender()) && temp.getAge() == newPerson.getAge() &&
                        temp.getHeight() == newPerson.getHeight() && temp.getWeight() == newPerson.getWeight()) {
                    throw new PersonAlreadyExistsException(personName + " already exists!"); //if all attributes match, throw exception
                }
            }
        }
        this.keys[numKeys] =personName;
        this.numKeys++;
        this.data.put(personName, newPerson) ;
    }

    /**
     * prints the toString method
     * @param name the key for the hashmap
     * @throws PersonDoesNotExistException if the person's name is not in the keys array
     */
    public void get(String name) throws PersonDoesNotExistException{
        boolean exists = false;
        for(int i=0; i<numKeys;i++){
            if(keys[i].equals(name)){
                exists=true;
                Person temp = data.get(name);
                System.out.println(temp.toString());
                return;
            }
        }
        if(!exists){
            throw new PersonDoesNotExistException(name+" doesn't exist!");
        }
    }

    /**
     * removes a person from hashmap
     * @param name the key
     * @throws PersonDoesNotExistException if the person's name is not in the keys array
     */
    public void remove(String name) throws PersonDoesNotExistException{
        //search through array of keys to see if person exists
        boolean exists = false;
        for(int i=0; i<numKeys;i++){
            if(keys[i].equals(name)){
                exists=true;
                data.remove(keys[i]);
                this.numKeys--;
                for(int j=i; j<this.numKeys;j++){
                    keys[j]=keys[j+1];
                }
                return;
            }
        }
        if(!exists){
            throw new PersonDoesNotExistException(name+" doesn't exist!");
        }
    }

    /**
     * Prints the data in tabular form
     */
    public void printTable(){
        System.out.println("  Name   |   Age   |  Gender |       Height      |      Weight  ");
        System.out.println("=======================================================================");
        for(int i =0; i<numKeys; i++){
            Person temp = data.get(keys[i]);
            System.out.println("  " + temp.getName() + "   |   " + temp.getAge() + "    |    " + temp.getGender() + "    |   " +
                    (int)(temp.getHeight()/12) + " feet " + (int)(temp.getHeight()%12) + " inches |   " + (int)temp.getWeight() + "   pounds");
        }
    }

}

/**
 * Exception for if person already exists
 */
class PersonAlreadyExistsException extends Exception{
    public PersonAlreadyExistsException(String message) {
        super(message);
    }
}

/**
 * Exception for if person does not exists
 */
class PersonDoesNotExistException extends Exception{
    public PersonDoesNotExistException(String message) {
        super(message);
    }
}