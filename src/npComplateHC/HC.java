package npComplateHC;

import commonFunction.CommonFunction;

import java.util.ArrayList;
import java.util.Random;

public class HC {
    private Integer numRows;
    private ArrayList <ArrayList<Integer>> paths;

    public HC () {}

    Integer getNumRows() {
        return numRows;
    }

    ArrayList <ArrayList <Integer>> getPaths() {
        return paths;
    }

    public void initialization () {
        System.out.println("initialization() - HC.java");

        int numberOfTasks = 6;
        int INF = 0;
        ArrayList <ArrayList <Integer>> pathsMatrix = new ArrayList <>();
        CommonFunction comFanc = new CommonFunction();
        final Random random = new Random();

        for(int i = 0; i<numberOfTasks;i++){
            ArrayList <Integer>  paths = new ArrayList<>();
            for (int j = 0; j<numberOfTasks;j++){
                if (j == i) paths.add(INF);
                else if (random.nextInt(2) == 0) paths.add(INF);
                     else paths.add(1);
            }
            pathsMatrix.add(comFanc.add(paths));
        }

        System.out.println("Path array: ");
        for(int i = 0; i<numberOfTasks; i++)
            System.out.println(pathsMatrix.get(i));

        this.paths = pathsMatrix;
        numRows = numberOfTasks;
    }
}
