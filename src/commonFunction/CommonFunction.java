package commonFunction;

import java.util.ArrayList;

public class CommonFunction {
    public CommonFunction () {}

    public  ArrayList<Integer> add(ArrayList<Integer> a) {
        ArrayList <Integer>  array = new ArrayList<>();
        for (Integer elem : a) array.add(elem);
        return array;
    }
}
