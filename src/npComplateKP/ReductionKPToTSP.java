package npComplateKP;

import commonFunction.CommonFunction;

import java.util.ArrayList;

public class ReductionKPToTSP {
    private KP kp;

    public ReductionKPToTSP (KP KP) {
        kp = KP;
    }
    public ArrayList <ArrayList<Float>>  toTSP () {
        System.out.println("toTSP() - ReductionKPToTSP.java");
        ArrayList <ArrayList<Float>> toTSP = new ArrayList<>();
        ArrayList <Float>  strToTSP= new ArrayList<>();
        CommonFunction comFanc = new CommonFunction();

        int maxWeightKP = kp.getMaxWeight();
        ArrayList <ArrayList <Float>> thingsMatrix = new ArrayList <>();
        for(int j=0; j<kp.getNumberOfThings();j++){
            strToTSP= new ArrayList<>();
            strToTSP.add((float)kp.getThings().get(j).get(0));
            strToTSP.add((float)kp.getThings().get(j).get(1));
            thingsMatrix.add(comFanc.add(strToTSP));
        }
        //Конечный путь
        int min = kp.getMaxWeight();
        for(int i=0; i<kp.getNumberOfThings();i++){
            if(kp.getThings().get(i).get(0)<min)
                min = kp.getThings().get(i).get(0);
        }

        if(min != 1){
            for(int j=0; j<kp.getNumberOfThings();j++){
                float value = thingsMatrix.get(j).get(1) / thingsMatrix.get(j).get(0);
                thingsMatrix.get(j).set(0,thingsMatrix.get(j).get(0)-min+1);
                float curr = thingsMatrix.get(j).get(1)-value*thingsMatrix.get(j).get(0);
                thingsMatrix.get(j).set(1,thingsMatrix.get(j).get(1)-curr);
                maxWeightKP = kp.getMaxWeight() - min + 1;
            }
        }

        float INF = 100000;
        for(int j=0; j<2*maxWeightKP-1;j++){
            strToTSP.add(INF);
        }
        for(int j=0; j<2*maxWeightKP-1;j++){
            toTSP.add(comFanc.add(strToTSP));
        }

        // Нули
        for(int i=maxWeightKP;i<2*maxWeightKP-1;i++){
            toTSP.get(i).set(2*maxWeightKP-1-i,(float)0);
            if(i != 2*maxWeightKP-2) toTSP.get(i).set(i+1,(float)0);
            else toTSP.get(i).set(0,(float)0);
        }
        for(int i=maxWeightKP-1;i>0;i--){
            if(i != 1) toTSP.get(i).set(2*maxWeightKP-i,(float)0);
            else toTSP.get(i).set(0,(float)0);
        }
        // Числа
        for (int j=0;j<maxWeightKP+1;j++){
            for(int i=0; i<kp.getNumberOfThings();i++){
                float curr = (float)Math.pow(thingsMatrix.get(i).get(0), 2) / thingsMatrix.get(i).get(1);
                float weight = thingsMatrix.get(i).get(0);
                if(curr < toTSP.get(j).get(j+(int)weight) && j+thingsMatrix.get(i).get(0) <= maxWeightKP)
                    toTSP.get(j).set(j+(int)weight,curr);
            }
        }

        return toTSP;
    }
}
