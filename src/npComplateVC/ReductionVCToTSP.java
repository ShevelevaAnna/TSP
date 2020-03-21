package npComplateVC;

import commonFunction.CommonFunction;

import java.util.ArrayList;

public class ReductionVCToTSP {
    private VC vc;
    ArrayList <ArrayList <Integer>> toTSP;

    public ReductionVCToTSP(VC VC) {
        vc = VC;
        toTSP = new ArrayList <>();
    }

    public ArrayList <ArrayList<Integer>> toTSP (){
        System.out.println("toTSP() - ReductionVCToTSP.java");
        initialization();
        communicationInComponent();
        communicationBetweenCoverageAndComponent();
        communicationBetweenComponents();
        return toTSP;
    }

    private void initialization (){
        int INF = 100000;
        ArrayList <Integer> rowToTSP = new ArrayList <>();
        CommonFunction comFanc = new CommonFunction();

        for (int i=0; i<vc.getNumCoverage()+4*vc.getNumRows(); i++){
            rowToTSP.add(INF);
        }
        for (int i=0; i<vc.getNumCoverage()+4*vc.getNumRows(); i++){
            toTSP.add(comFanc.add(rowToTSP));
        }
    }

    private void communicationInComponent (){
        for (int i = 0; i < vc.getNumRows(); i++){
            setCoord(i*4,i*4+1);
            setCoord(i*4+2,i*4+3);
            toTSP.get(i*4+1).set(i*4+2, 1);
            toTSP.get(i*4+3).set(i*4, 1);
        }
    }

    private void communicationBetweenCoverageAndComponent(){
        for(int i=0; i < vc.getNumCoverage(); i++)
            for (int j = 0; j < 4*vc.getNumRows(); j += 2)
                setCoord(j, 4*vc.getNumRows()+i);
    }

    private void communicationBetweenComponents(){
        int firstCoord, currCoord;

        for (int i=0; i < vc.getNumVertices(); i++) {
            firstCoord = 0;
            for (int j = 0; j < vc.getNumRows(); j++)
                for (int k = 0; k < 2; k++)
                    if (vc.getPaths().get(j).get(k) == i) {
                        currCoord = 4 * j + 1 + k * 2;
                        if (firstCoord != 0) setCoord(firstCoord, currCoord);
                        firstCoord = currCoord;
                    }
        }
    }

    private void setCoord( int firstCoord, int secondCoord){
        toTSP.get(firstCoord).set(secondCoord, 1);
        toTSP.get(secondCoord).set(firstCoord, 1);
    }
}
