package npComplateVC;

import commonFunction.CommonFunction;

import java.util.ArrayList;
import java.util.Random;

public class VC {
    private Integer numRows;
    private Integer numVertices;
    private Integer numCoverage;
    private ArrayList <ArrayList<Integer>> paths;

    public VC () {}

    Integer getNumRows() {
        return numRows;
    }

    Integer getNumVertices() {
        return numVertices;
    }

    Integer getNumCoverage() {
        return numCoverage;
    }

    ArrayList <ArrayList <Integer>> getPaths() {
        return paths;
    }

    public void initialization () {
        System.out.println("initialization() - VC.java");

        numVertices = 5;
        numRows = 5;

        ArrayList <ArrayList <Integer>> pathsMatrix = new ArrayList <>();
        CommonFunction comFanc = new CommonFunction();
        final Random random = new Random();

        numCoverage = 1;//random.nextInt(numVertices - 2) + 2;


        ArrayList <Integer>  paths = new ArrayList<>();
        paths.add(0); paths.add(1);
        pathsMatrix.add(comFanc.add(paths));
        paths = new ArrayList<>();
        paths.add(0); paths.add(2);
        pathsMatrix.add(comFanc.add(paths));
        paths = new ArrayList<>();
        paths.add(1); paths.add(2);
        pathsMatrix.add(comFanc.add(paths));
        paths = new ArrayList<>();
        paths.add(1); paths.add(3);
        pathsMatrix.add(comFanc.add(paths));
        paths = new ArrayList<>();
        paths.add(1); paths.add(4);
        pathsMatrix.add(comFanc.add(paths));

        System.out.println("Path array: ");
        for(int i = 0; i<numRows;i++)
            System.out.println(pathsMatrix.get(i));
        System.out.println("Number of vertices for VC: " + numCoverage);

        this.paths = pathsMatrix;
    }
}
