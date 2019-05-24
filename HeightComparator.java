import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

/**
 * This class prints the table with increasing heights
 */
public class HeightComparator{
    public HeightComparator(PersonDataHashMap p){
        HashMap<String, Person> data =p.getData();
        String[] keys = p.getKeys();
        int numKeys = p.getNumKeys();
        ArrayList<Person> heightSorter = new ArrayList<Person>();
        for(int i=0; i<numKeys;i++){
            heightSorter.add(data.get(keys[i]));
        }
        Collections.sort(heightSorter, new Comparator<Person>() {
            @Override
            public int compare(Person o1, Person o2) {
                if(o1.getHeight()>o2.getHeight()){
                    return 1;
                } else if(o1.getHeight()<o2.getHeight()){
                    return -1;
                } else{
                    return 0;
                }
            }
        });
        System.out.println("  Name   |   Age   |  Gender |       Height      |      Weight  ");
        System.out.println("=======================================================================");
        for(int i=0; i<numKeys; i++){
            Person temp = heightSorter.get(i);
            System.out.println("  " + temp.getName() + "   |   " + temp.getAge() + "    |    " + temp.getGender() + "    |   " +
                    (int)(temp.getHeight()/12) + " feet " + (int)(temp.getHeight()%12) + " inches |   " + (int)temp.getWeight() + "   pounds");

        }
    }
}
