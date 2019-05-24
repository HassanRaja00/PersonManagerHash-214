import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

/**
 * This class prints the names alphabetically
 */
public class NameComparator {
    public NameComparator(PersonDataHashMap p){
        HashMap<String, Person> data =p.getData();
        String[] keys = p.getKeys();
        int numKeys = p.getNumKeys();
        ArrayList<Person> nameSorter = new ArrayList<Person>();
        for(int i=0; i<numKeys;i++){
            nameSorter.add(data.get(keys[i]));
        }
        Collections.sort(nameSorter, new Comparator<Person>() {
            @Override
            public int compare(Person o1, Person o2) {
                if(o1.getName().compareTo(o2.getName())>1){
                    return 1;
                } else if(o1.getName().compareTo(o2.getName())<1){
                    return -1;
                } else{
                    return 0;
                }
            }
        });
        System.out.println("  Name   |   Age   |  Gender |       Height      |      Weight  ");
        System.out.println("=======================================================================");
        for(int i=0; i<numKeys; i++){
            Person temp = nameSorter.get(i);
            System.out.println("  " + temp.getName() + "   |   " + temp.getAge() + "    |    " + temp.getGender() + "    |   " +
                    (int)(temp.getHeight()/12) + " feet " + (int)(temp.getHeight()%12) + " inches |   " + (int)temp.getWeight() + "   pounds");

        }
    }
}
