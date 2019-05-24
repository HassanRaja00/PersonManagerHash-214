import java.util.InputMismatchException;
import java.util.Scanner;
public class PersonDataManager {
    static PersonDataHashMap manager;
    public static void main(String[] a){
        Scanner input = new Scanner(System.in);
        System.out.println("Starting...");
        System.out.println("Menu:");
        String initialStatement = "(I) - Import from File \n(A) - Add Person \n(R) - Remove Person \n(G) - Get Information on Person \n" +
                "(P) - Print Table \n(S) - Sort\n(Q) - Quit";
        while(true){ //used to continue program
            System.out.println(initialStatement);
            System.out.print("Please select an option: ");
            String choice = input.nextLine();
            switch(choice){
                case "I":
                    System.out.print("Please enter a location: ");
                    String location = input.nextLine();
                    try{
                        manager = PersonDataHashMap.buildFromFile(location); //builds file and sets person data hashmap
                    } catch(IllegalArgumentException ex){
                        System.out.println(ex.getMessage()+"\n\n");
                    }
                    break;
                case "A":
                    if(manager==null){
                        System.out.println("You have an empty table. Import first!\n\n");
                    } else {
                        try {
                            System.out.print("Please enter the name: ");
                            String newName = input.nextLine();
                            System.out.print("Please enter the age: ");
                            int newAge = Integer.parseInt(input.nextLine());
                            System.out.print("Please enter the gender (M or F): ");
                            String newGender = input.nextLine();
                            System.out.print("Please enter the height (in inches): ");
                            double newHeight = Double.parseDouble(input.nextLine());
                            System.out.print("Please enter the weight (in lbs): ");
                            double newWeight = Double.parseDouble(input.nextLine());
                            Person temp = new Person(newName, newGender, newAge, newHeight, newWeight);
                            manager.add(newName, temp);
                            System.out.println(newName + " has been added!");
                        } catch (IllegalArgumentException ex1) { //catches exception of incorrect inputs
                            System.out.println("The input you entered is wrong. Try again!\n\n");
                        } catch (InputMismatchException ex2) {
                            System.out.println("The input you entered is wrong. Try again!\n\n");
                        } catch (PersonAlreadyExistsException ex3) {
                            System.out.println(ex3.getMessage()+"\n\n");
                        }
                    }
                    break;
                case "R":
                    System.out.println("Enter the person you want removed:");
                    String remove = input.nextLine();
                    try{
                        manager.remove(remove);
                        System.out.println(remove + " has been deleted!");
                    } catch(PersonDoesNotExistException ex){
                        System.out.println(ex.getMessage()+"\n\n");
                    } catch(NullPointerException e){
                        System.out.println("You have an empty table. Import first!\n\n");
                    }
                    break;
                case "G":
                    System.out.println("Please enter the name of the person:");
                    try{
                        manager.get(input.nextLine());
                    } catch(PersonDoesNotExistException ex){
                        System.out.println(ex.getMessage() +"\n\n");
                    } catch(NullPointerException e){
                        System.out.println("You have an empty table. Import first!\n\n");
                    }
                    break;
                case "P":
                    try {
                        manager.printTable();
                    } catch(NullPointerException e){ //is table is empty if throws null pointer
                        System.out.println("You have an empty table. Import first!\n\n");
                    }
                    break;
                case "S":
                    if(manager==null){
                        System.out.println("You have an empty table. Import first!\n\n");
                    } else {
                        System.out.println("Please select by what");
                        System.out.println("  *(N) - Name\n  *(W) - Weight\n  *(H) - Height");
                        String sorter = input.nextLine();
                        switch (sorter) {
                            case "N":
                                //sort by name
                                System.out.println("Here is the HashMap sorted by names:");
                                NameComparator nameSorted = new NameComparator(manager);
                                break;
                            case "H":
                                //sort by height
                                System.out.println("Here is the HashMap sorted by height:");
                                HeightComparator heightSorted = new HeightComparator(manager);
                                break;
                            case "W":
                                //sort by weight
                                System.out.println("Here is the HashMap sorted by weight:");
                                WeightComparator weightSorted = new WeightComparator(manager);
                                break;
                            default:
                                System.out.println("Incorrect choice! Try again");
                        }
                    }
                    break;
                case "Q":
                    input.close();
                    System.out.println("Sorry to see you go!");
                    System.exit(0);
                default:
                    System.out.println("Wrong choice! Try again\n\n");
            }
        }
    }
}
