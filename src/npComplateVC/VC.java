package npComplateVC;

import commonFunction.CommonFunction;

import java.util.ArrayList;
import java.util.Random;

public class VC {
    private Integer numRows;
    private Integer numVertices;
    private ArrayList <ArrayList<Integer>> paths;

    public VC () {}

    Integer getNumRows() {
        return numRows;
    }

    Integer getNumVertices() {
        return numVertices;
    }

    ArrayList <ArrayList <Integer>> getPaths() {
        return paths;
    }

    public void initialization () {
        System.out.println("initialization() - VC.java");

        int numberOfTasks = 3;
        int INF = 100000;
        ArrayList <ArrayList <Integer>> pathsMatrix = new ArrayList <>();
        CommonFunction comFanc = new CommonFunction();
        final Random random = new Random();

        this.numVertices = random.nextInt(numberOfTasks - 2) + 2;
        for(int i = 0; i<numberOfTasks;i++){
            ArrayList <Integer>  paths = new ArrayList<>();
            for (int j = 0; j<numberOfTasks;j++){
                if (j == i) paths.add(INF);
                else if (random.nextInt(2) == 0) paths.add(INF);
                else paths.add(1);
            }
            pathsMatrix.add(comFanc.add(paths));
        }

        System.out.println("Path array: " + pathsMatrix);
        System.out.println("Number of vertices for VC: " + this.numVertices);

        this.paths = pathsMatrix;
        this.numRows = numberOfTasks;
    }
}
