package commonFunction;

import java.util.ArrayList;

public class CommonFunction<T> {
    public CommonFunction () {}

    public static <T> ArrayList<T> add(ArrayList<T> a) {
        ArrayList <T>  array = new ArrayList<>();
        for (T elem : a) array.add(elem);
        return array;
    }

    public  ArrayList<ArrayList<Float>> intToFloat(ArrayList<ArrayList<Integer>> a) {
        ArrayList <Float>  currArray;
        ArrayList<ArrayList<Float>> result = new ArrayList<>();
        for (int i = 0; i < a.size(); i++){
            currArray = new ArrayList<>();
            for (int j = 0; j < a.size(); j++){
                if(a.get(i).get(j) == 100000) currArray.add((float)100000);
                else currArray.add((float)a.get(i).get(j));// НУЖНО в промежуток [0,1]
            }
            result.add(add(currArray));
        }
        return result;
    }
}
