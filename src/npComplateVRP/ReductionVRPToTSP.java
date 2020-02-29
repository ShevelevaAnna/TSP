package npComplateVRP;

import java.util.ArrayList;

public class ReductionVRPToTSP {
    private VRP vrp;

    public ReductionVRPToTSP(VRP VRP) {
        vrp = VRP;
    }

    public ArrayList <ArrayList<Integer>> toTSP (){
        System.out.println("toTSP() - ReductionVRPToTSP.java");
        ArrayList <Integer>  strToTSP;

        for (int i = 0; i < vrp.getNumRows(); i++){
            for (int j = 0; j < vrp.getNumRows(); j++) {
                if(j == vrp.getNumRoutes() - 1) {
                    for (int k = 0; k < vrp.getNumRoutes() - 1; k++){
                        vrp.getCosts().get(i).add(vrp.getCosts().get(i).get(0));
                    }
                }
            }
        }

        for (int i = 0; i < vrp.getNumRoutes() - 1; i++){
            strToTSP = new ArrayList<>(vrp.getNumRows());
            for (int j = 0; j < vrp.getNumRoutes() + vrp.getNumRows() - 1; j++){
                if (j == 0 || j > vrp.getNumRows()) strToTSP.add(100000);
                else strToTSP.add(vrp.getCosts().get(0).get(j));
            }
            vrp.getCosts().add(strToTSP);
        }

        return vrp.getCosts();
    }
}
