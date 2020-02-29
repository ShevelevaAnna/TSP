package npComplate2CAP;

import java.util.ArrayList;

public class Reduction2CAPToTSP {
    private CAP cap;

    public Reduction2CAPToTSP(CAP CAP) {
        cap = CAP;
    }

    public ArrayList <ArrayList<Float>> toTSP (){
        System.out.println("toTSP() - Reduction2CAPToTSP.java");
        ArrayList <ArrayList<Float>> toTSP = new ArrayList<>();
        ArrayList <Float>  strToTSP;
        float INF = 100000;

        for (int i=0; i<2*cap.getNumRows(); i++){
            strToTSP = new ArrayList<>(cap.getNumRows());
            if(i<cap.getNumRows())
                for (int j = 0; j < 2 * cap.getNumRows(); j++) {
                    if(j<cap.getNumRows()) strToTSP.add(INF);
                    else strToTSP.add((float)cap.getSalary().get(i).get(j - cap.getNumRows()) +
                            (float)0.1 / cap.getCosts().get(i).get(j - cap.getNumRows()));
                }
            else
                for (int j = 0; j < 2 * cap.getNumRows(); j++) {
                    if (j < cap.getNumRows()) strToTSP.add((float) 0);
                    else strToTSP.add(INF);
                }
            toTSP.add(strToTSP);
        }

        return toTSP;
    }
}
