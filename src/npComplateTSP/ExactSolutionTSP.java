package npComplateTSP;

import commonFunction.CommonFunction;

import java.util.ArrayList;

public class ExactSolutionTSP {
    private TSP tsp;
    private ArrayList <ArrayList<Float>> edges;
    private CommonFunction comFanc;
    private ArrayList<Integer> open;
    private ArrayList<Integer> close;
    private ArrayList<Integer> minPath;
    private float minWeight;


    public ExactSolutionTSP (TSP TSP) {
        tsp = TSP;
        edges = new ArrayList <>();
        comFanc = new CommonFunction();
        open = new ArrayList <>();
        close = new ArrayList <>();
        minPath = new ArrayList <>();

        minWeight = 0;
        close.add(0);
        minPath.add(0);
        for (int i = 1; i < tsp.getNumRows(); i++)
            open.add(i);
    }

    public boolean resultTSP(){
        System.out.println("greedyAlgorithm() - ExactSolutionTSP.java");
        greedyAlgorithm();
        System.out.println("tspAlgorithm(0) - ExactSolutionTSP.java");
        tspAlgorithm(0);

        if (minWeight < 100000){
            System.out.println("Length path ="+minWeight);
            return true;
        }
        return  false;
    }

    public float getMinWeight() {
        return minWeight;
    }

    public ArrayList<Integer> getMinPath() {
        return minPath;
    }

    private void tspAlgorithm(float minW){
        if (open.size() == 0) {
            //System.out.println(" open:"+open+" close:"+close);
            float currW = tsp.getPaths().get(close.get(close.size() - 1)).get(0);
            if (minW + currW < minWeight){
                minWeight = minW + currW;
                minPath = comFanc.add(close);
            }
        }

        for (int i = 0; i < open.size() && minW < minWeight; i++){
            float currW = tsp.getPaths().get(close.get(close.size() - 1)).get(open.get(i));
            minW += currW;
            close.add(open.get(i));
            open.remove(i);

            tspAlgorithm(minW);

            minW -=currW;
            open.add(i, close.get(close.size() - 1));
            close.remove(close.size() - 1);
        }
    }

    private void greedyAlgorithm(){
        ArrayList<Integer> open = new ArrayList <>();
        int currVert = 0;
        for (int i = 1; i < tsp.getNumRows(); i++)
            open.add(i);
        for (int i = 0; i < tsp.getNumRows() - 1; i++){
            float curMin = 100000;
            for (int j = 0; j < open.size(); j++){
                if(tsp.getPaths().get(minPath.get(minPath.size() - 1)).get(open.get(j)) <= curMin){
                    currVert = j;
                    curMin = tsp.getPaths().get(minPath.get(minPath.size() - 1)).get(open.get(j));
                }
            }
            minPath.add(open.get(currVert));
            open.remove(currVert);
            minWeight += curMin;
        }
        minWeight += tsp.getPaths().get(minPath.get(minPath.size() - 1)).get(0);
    }
}
