import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

/**
 * This class prints the weights in increasing order
 */
public class WeightComparator {
    public WeightComparator(PersonDataHashMap p){
        HashMap<String, Person> data =p.getData();
        String[] keys = p.getKeys();
        int numKeys = p.getNumKeys();
        ArrayList<Person> weightSorter = new ArrayList<Person>();
        for(int i=0; i<numKeys;i++){
            weightSorter.add(data.get(keys[i]));
        }
        Collections.sort(weightSorter, new Comparator<Person>() {
            @Override
            public int compare(Person o1, Person o2) {
                if(o1.getWeight()>o2.getWeight()){
                    return 1;
                } else if(o1.getWeight()<o2.getWeight()){
                    return -1;
                } else{
                    return 0;
                }
            }
        });
        System.out.println("  Name   |   Age   |  Gender |       Height      |      Weight  ");
        System.out.println("=======================================================================");
        for(int i=0; i<numKeys; i++){
            Person temp = weightSorter.get(i);
            System.out.println("  " + temp.getName() + "   |   " + temp.getAge() + "    |    " + temp.getGender() + "    |   " +
                    (int)(temp.getHeight()/12) + " feet " + (int)(temp.getHeight()%12) + " inches |   " + (int)temp.getWeight() + "   pounds");

        }
    }
}
