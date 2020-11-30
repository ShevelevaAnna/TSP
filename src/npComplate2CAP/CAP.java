package npComplate2CAP;

import commonFunction.CommonFunction;
import java.util.ArrayList;
import java.util.Random;

public class CAP {
    private Integer numRows;
    private ArrayList <ArrayList <Integer>> costs;
    private ArrayList <ArrayList <Integer>> salary;

    public CAP() { }

    Integer getNumRows() {
        return numRows;
    }

    ArrayList <ArrayList <Integer>> getSalary() {
        return salary;
    }

    ArrayList <ArrayList <Integer>> getCosts() {
        return costs;
    }

    public void initialization () {
        System.out.println("initialization() - CAP.java");

        int numberOfTasks = 10;
        ArrayList <ArrayList <Integer>> costMatrix = new ArrayList <>();
        ArrayList <ArrayList <Integer>> salaryMatrix = new ArrayList <>();
        CommonFunction comFanc = new CommonFunction();
        final Random random = new Random();

        for(int i=0; i<numberOfTasks;i++){
            ArrayList <Integer>  cost = new ArrayList<>();
            ArrayList <Integer>  salary = new ArrayList<>();
            for (int j=0; j<numberOfTasks;j++){
                cost.add(random.nextInt(100) + 1);
                salary.add(random.nextInt(100) + 1);
            }
            costMatrix.add(comFanc.add(cost));
            salaryMatrix.add(comFanc.add(salary));
        }

        System.out.println("Cost array: ");
        for(int i = 0; i<numberOfTasks; i++)
            System.out.println(costMatrix.get(i));
        System.out.println("Salary array: ");
        for(int i = 0; i<numberOfTasks; i++)
            System.out.println(salaryMatrix.get(i));

        this.costs = costMatrix;
        this.salary = salaryMatrix;
        numRows = numberOfTasks;
    }
}
