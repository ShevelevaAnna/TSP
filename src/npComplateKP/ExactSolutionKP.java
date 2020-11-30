package npComplateKP;

import commonFunction.CommonFunction;

import java.util.ArrayList;

public class ExactSolutionKP {
    private KP kp;

    public ExactSolutionKP (KP KP) {
        kp = KP;
    }
    public void knapsack () {
        ArrayList<Integer> array = new ArrayList<>();
        ArrayList<Integer> arrayMax = new ArrayList<>();
        ArrayList <ArrayList <Float>> thingsMatrix = new ArrayList <>();

        ArrayList <Float>  str= new ArrayList<>();
        CommonFunction comFanc = new CommonFunction();
        for(int j=0; j<kp.getNumberOfThings();j++){
            str= new ArrayList<>();
            str.add((float)kp.getThings().get(j).get(0));
            str.add((float)kp.getThings().get(j).get(1));
            thingsMatrix.add(comFanc.add(str));
        }

        int min = kp.getMaxWeight();
        for(int i=0; i<kp.getNumberOfThings();i++){
            if(kp.getThings().get(i).get(0)<min)
                min = kp.getThings().get(i).get(0);
        }
        int maxWeightKP = kp.getMaxWeight();
        if(min != 1){
            for(int j=0; j<kp.getNumberOfThings();j++){
                float value = thingsMatrix.get(j).get(1) / thingsMatrix.get(j).get(0);
                thingsMatrix.get(j).set(0,thingsMatrix.get(j).get(0)-min+1);
                float curr = thingsMatrix.get(j).get(1)-value*thingsMatrix.get(j).get(0);
                thingsMatrix.get(j).set(1,thingsMatrix.get(j).get(1)-curr);
                maxWeightKP = kp.getMaxWeight() - min + 1;
            }
        }
        for(int i = 0; i<kp.getNumberOfThings(); i++)
            System.out.println(thingsMatrix.get(i));

        //System.out.println(thingsMatrix.get(0).get(0));
        float max = (maxWeightKP / thingsMatrix.get(0).get(0))*thingsMatrix.get(0).get(1);
        //System.out.println(max);

        for (int i=0; i<maxWeightKP;i++){
            array.add(0);
        }
        int maxWeight=0;
        arrayMax.add(0);
        for (int i=0; i<maxWeightKP;i++) {
            if (maxWeight + thingsMatrix.get(array.get(i)).get(0) <= maxWeightKP) {
                maxWeight += thingsMatrix.get(array.get(i)).get(0);
                arrayMax.add(maxWeight);
            }
            else {
                if( maxWeight != maxWeightKP) arrayMax.add(maxWeightKP);
                break;
            }
        }

        while (NextSet(array, maxWeightKP)){
            float maxCurr = 0;
            maxWeight=0;
            for (int i=0; i<maxWeightKP;i++) {
                //System.out.println(array);
                //System.out.println(thingsMatrix.get(array.get(i)).get(0));
                if (maxWeight + thingsMatrix.get(array.get(i)).get(0) <= maxWeightKP) {
                    maxWeight += thingsMatrix.get(array.get(i)).get(0);
                    maxCurr += thingsMatrix.get(array.get(i)).get(1);
                }
                else break;
            }
            //System.out.println(maxCurr);
            if (maxCurr > max) {
                max = maxCurr;

                maxWeight=0;
                arrayMax = new ArrayList<>();
                arrayMax.add(0);
                for (int i=0; i<maxWeightKP;i++) {
                    if (maxWeight + thingsMatrix.get(array.get(i)).get(0) <= maxWeightKP) {
                        maxWeight += thingsMatrix.get(array.get(i)).get(0);
                        arrayMax.add(maxWeight);
                    }
                    else {
                        if( maxWeight != maxWeightKP) arrayMax.add(maxWeightKP);
                        break;
                    }
                }
                //System.out.println(array);
            }
        }

        System.out.println("Resulting vertices - "+arrayMax);
        System.out.println("Maximum cost: "+max);
        System.out.println("---");
    }

    private boolean NextSet(ArrayList<Integer> array, int maxWeightKP) {
        for (int i = maxWeightKP-1; i>=0; i--){
            if(i==0 && array.get(i) == kp.getNumberOfThings() - 1){
                return false;
            }
            if(array.get(i) < kp.getNumberOfThings() - 1) {
                array.set(i,array.get(i)+1);
                break;
            }
            else {
                array.set(i,0);
                continue;
            }
        }
        return true;
    }
}
