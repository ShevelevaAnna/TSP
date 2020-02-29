package npComplateHC;

import java.util.ArrayList;

public class ReductionHCToTSP {
    private HC hc;

    public ReductionHCToTSP(HC HC) {
        hc = HC;
    }

    public ArrayList <ArrayList<Integer>> toTSP (){
        System.out.println("toTSP() - ReductionHCToTSP.java");
        int INF = 100000;

        for (int i=0; i<hc.getNumRows(); i++){
            for (int j = 0; j < hc.getNumRows(); j++) {
                if(i != j) {
                    if (hc.getPaths().get(i).get(j) == INF) hc.getPaths().get(i).set(j, hc.getNumRows() + 1);
                }
            }
        }

        return hc.getPaths();
    }
}
