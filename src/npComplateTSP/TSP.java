package npComplateTSP;

import java.util.ArrayList;

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

    ArrayList<ArrayList<Float>> getPaths() {
        return paths;
    }

}
