package npComplate2CAP;

import commonFunction.CommonFunction;
import java.util.*;
import java.lang.*;

public class ExactSolution2CAP {
    private CAP cap;
    private int INF = 100000;
    private CommonFunction comFunc;

    public ExactSolution2CAP(CAP CAP) {
        cap = CAP;
        comFunc = new CommonFunction();
    }

    public ArrayList <Integer> solution() {
        System.out.println("solution() - ExactSolution.java");
        ArrayList <Integer>  a = new ArrayList<>(cap.getNumRows());
        ArrayList <ArrayList <Integer>> minSalary = new ArrayList<>();
        ArrayList <Integer> minCost;
        int min, count = 0;

        for (int i = 0; i < cap.getNumRows(); i++) // начальное значение
            a.add(i);
        for(int j=0; j<cap.getNumRows(); j++)
            count += cap.getSalary().get(j).get(a.get(j));
        min = count;
        minSalary.add(comFunc.add(a));

        while (NextSet(a, cap.getNumRows())) // минимальные зарплаты
        {
            count = 0;
            for(int j=0; j<cap.getNumRows(); j++)
                count += cap.getSalary().get(j).get(a.get(j));
            if (count == min)
                minSalary.add(comFunc.add(a));
            if(count < min) {
                min=count;
                minSalary = new ArrayList<>();
                minSalary.add(comFunc.add(a));
            }
        }

        if(minSalary.size()==1) minCost = minSalary.get(0); // минимальные затраты
        else minCost = minCost(minSalary);
        return  minCost;
    }

    // Нахождение минимальной стоимости работы по найденным значениям зарплаты
    private ArrayList <Integer> minCost(ArrayList<ArrayList<Integer>> minSalary) {
        System.out.println("minCost() - ExactSolution.java");
        ArrayList <Integer> min_cost = new ArrayList<>();
        int min = INF;

        for (ArrayList<Integer> elem : minSalary) {
            int count = 0;
            for (int j = 0; j < minSalary.get(0).size(); j++)
                count += cap.getCosts().get(j).get(elem.get(j));
            if (count < min) {
                min_cost = comFunc.add(elem);
                min = count;
            }
        }
        return min_cost;
    }

    private void swap(ArrayList<Integer> a, int left, int right) {
        int s = a.get(left);
        a.set(left, a.get(right));
        a.set(right,s);
    }

    private boolean NextSet(ArrayList<Integer> array, int n) {
        int curr = n - 2;
        while (curr != -1 && array.get(curr) >= array.get(curr + 1)) curr--;
        if (curr == -1) return false;

        int k = n - 1;
        while (array.get(curr) >= array.get(k)) k--;
        swap(array, curr, k);

        int left = curr + 1, right = n - 1;
        while (left < right) swap(array, left++, right--);
        return true;
    }
}