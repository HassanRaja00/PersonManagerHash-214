/**
 * Hassan Raja 
 */
public class Person {
    /**
     * age is for persons's age
     * weight and height represent a person
     * name, gender also represent them
     */
    private int age;
    private double weight, height;
    private String name, gender;

    /**
     * Constructor for Person object
     * @param name their name
     * @param gender their gender
     * @param age their age
     * @param height their height
     * @param weight their weight
     */
    public Person(String name, String gender, int age, double height, double weight) {
        this.age = age;
        this.weight = weight; this.height = height;
        this.name = name; this.gender = gender;
    }

    /**
     * @return person's age
     */
    public int getAge() {
        return this.age;
    }

    /**
     * @param newAge sets person's age
     */
    public void setAge(int newAge) {
        this.age = newAge;
    }

    /**
     * @return person's weight
     */
    public double getWeight() {
        return this.weight;
    }

    /**
     * @param newWeight sets person's weight
     */
    public void setWeight(int newWeight) {
        this.weight = newWeight;
    }

    /**
     * @return person's height
     */
    public double getHeight() {
        return this.height;
    }

    /**
     * @param newHeight sets height
     */
    public void setHeight(int newHeight) {
        this.height = newHeight;
    }

    /**
     * @return person's name
     */
    public String getName() {
        return this.name;
    }

    /**
     * @param newName sets name
     */
    public void setName(String newName) {
        this.name = newName;
    }

    /**
     * @return person's gender
     */
    public String getGender() {
        return this.gender;
    }

    /**
     * @param newGender sets gender
     */
    public void setGender(String newGender) {
        this.gender = newGender;
    }

    /**
     * toString method that gives person's attributes
     * @return string sentence of person's attributes
     */
    public String toString() {
        if(this.gender.equals("F")) {

            return this.name + " is a " + this.age + " year old female who is " + (int)(this.height/12) +
                    " feet and " + (int)(this.height%12) + " inches tall and weighs " + (int)(this.weight) + " lbs.";
        }
        else {
            return this.name + " is a " + this.age + " year old male who is " + (int)(this.height/12) +
                    " feet and " + (int)(this.height%12) + " inches tall and weighs " + (int)(this.weight) + " lbs.";
        }

    }
}
