package npComplateVRP;

import commonFunction.CommonFunction;
import java.util.ArrayList;
import java.util.Random;

public class VRP {
    private Integer numRows;
    private Integer numRoutes;
    private ArrayList <ArrayList<Integer>> costs;

    public VRP () {}

    Integer getNumRows() {
        return numRows;
    }

    Integer getNumRoutes() {
        return numRoutes;
    }

    ArrayList <ArrayList <Integer>> getCosts() {
        return costs;
    }

    public void initialization () {
        System.out.println("initialization() - VRP.java");

        int numberOfTasks = 4;
        int INF = 100000;
        ArrayList <ArrayList <Integer>> costMatrix = new ArrayList <>();
        CommonFunction comFanc = new CommonFunction();
        final Random random = new Random();

        numRoutes = random.nextInt(numberOfTasks - 1) + 2;
        for(int i = 0; i<numberOfTasks;i++){
            ArrayList <Integer>  cost = new ArrayList<>();
            for (int j = 0; j<numberOfTasks;j++){
                if (j == i) cost.add(INF);
                else cost.add(random.nextInt(100) + 1);
            }
            costMatrix.add(comFanc.add(cost));
        }

        System.out.println("Cost array: ");
        for(int i = 0; i<numberOfTasks; i++)
            System.out.println(costMatrix.get(i));
        System.out.println("Number of routes: " + this.numRoutes);

        this.costs = costMatrix;
        numRows = numberOfTasks;
    }
}
