package npComplateKP;

import commonFunction.CommonFunction;

import java.util.ArrayList;
import java.util.Random;

public class KP {
    private Integer maxWeight;
    private Integer numberOfThings;
    private ArrayList <ArrayList<Integer>> things;

    public KP () {}

    Integer getMaxWeight() {
        return maxWeight;
    }
    Integer getNumberOfThings() {
        return numberOfThings;
    }
    ArrayList <ArrayList <Integer>> getThings() {
        return things;
    }

    public void initialization () {
        System.out.println("initialization() - KP.java");

        int numberOfThings = 8;
        int MAXVAL = 15;
        ArrayList <ArrayList <Integer>> thingsMatrix = new ArrayList <>();
        CommonFunction comFanc = new CommonFunction();
        final Random random = new Random();

        for(int i = 0; i<numberOfThings;i++){
            ArrayList <Integer>  things = new ArrayList<>();
            for (int j = 0; j<2;j++){
                things.add(random.nextInt(MAXVAL-1)+2);
            }
            thingsMatrix.add(comFanc.add(things));
        }
       /* ArrayList <Integer>  things = new ArrayList<>();
        things.add(8); things.add(8);
        thingsMatrix.add(comFanc.add(things));
        things = new ArrayList<>();
        things.add(3); things.add(1);
        thingsMatrix.add(comFanc.add(things));
        things = new ArrayList<>();
        things.add(7); things.add(8);
        thingsMatrix.add(comFanc.add(things));*/

        System.out.println("Things array (weight;value): ");
        for(int i = 0; i<numberOfThings; i++)
            System.out.println(thingsMatrix.get(i));

        this.things = thingsMatrix;
        this.maxWeight = MAXVAL + 2;
        this.numberOfThings = numberOfThings;
        System.out.println("Max weight="+this.maxWeight+"; number of things="+this.numberOfThings);
    }
}
