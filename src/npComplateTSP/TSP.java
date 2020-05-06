package npComplateTSP;

import commonFunction.CommonFunction;

import java.util.ArrayList;
import java.util.Random;

public class TSP {
    private Integer numRows;
    private ArrayList<ArrayList<Float>> paths;

    public TSP (ArrayList<ArrayList<Float>> TSP) {
        paths = TSP;
        numRows = TSP.size();
    }

    Integer getNumRows() {
        return numRows;
    }

    public ArrayList<ArrayList<Float>> getPaths() {
        return paths;
    }

    public TSP () {
        System.out.println("constructor initialization TSP() - TSP.java");

        int numberOfTasks = 9;
        int sparseness = 1; // вероятность 1 к sparseness

        ArrayList <ArrayList <Integer>> costMatrix = new ArrayList <>();
        CommonFunction comFanc = new CommonFunction();
        final Random random = new Random();

        for(int i=0; i<numberOfTasks;i++){
            ArrayList <Integer>  cost = new ArrayList<>();
            for (int j=0; j<numberOfTasks;j++){
                if(i==j) cost.add(100000);
                else if(random.nextInt(sparseness) + 1 == 2) cost.add(100000);
                     else cost.add(random.nextInt(100) + 1);
            }
            costMatrix.add(comFanc.add(cost));
        }
        System.out.println("Path array: ");
        for(int i = 0; i<numberOfTasks; i++)
            System.out.println(costMatrix.get(i));

        this.paths = comFanc.intToFloat(costMatrix);
        numRows = numberOfTasks;
    }
}
