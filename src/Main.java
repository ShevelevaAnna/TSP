import commonFunction.CommonFunction;
import npComplate2CAP.*;
import npComplateVRP.*;
import npComplateHC.*;
import npComplateVC.*;
import npComplateTSP.*;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        CAP();
        //VRP();
        //HC();
        //VC();
    }

    private static void CAP() {
        System.out.println("CAP() - Main.java");
        CAP cap = new CAP ();
        cap.initialization();
        System.out.println("---");

        // решение 2-ЗОН
        ExactSolution2CAP CAP=new ExactSolution2CAP(cap);
        ArrayList<Integer> result_2CAP = CAP.solution();
        StringBuilder str_2CAP= new StringBuilder();
        for (Integer elem : result_2CAP) str_2CAP.append(elem).append(" ");
        System.out.println("Solution 2CAP (Main.java): " + str_2CAP + "\n ---");

        // перевод в TSP
        Reduction2CAPToTSP toTSP = new Reduction2CAPToTSP(cap);
        ArrayList<ArrayList<Float>> result_toTSP = toTSP.toTSP();
        System.out.println("Array toTSP (Main.java): ");
        printArray(result_toTSP);

        // Решение TSP
        CommonFunction comFanc = new CommonFunction();
        ArrayList<ArrayList<Float>> resultFloat_toTSP = result_toTSP;
        TSP tsp = new TSP(resultFloat_toTSP);
        ExactSolutionTSP solutionTSP = new ExactSolutionTSP(tsp);
        if(solutionTSP.resultTSP()) System.out.println("Result: "+solutionTSP.getMinPath() );
        else System.out.println("Result: TSP does not exist.");
        System.out.println("---");

        // Оценка длины пути
        PathLengthEstimate tspEst = new PathLengthEstimate(resultFloat_toTSP);
        tspEst.basicData();
        tspEst.satisfyingEdges();
        tspEst.hitRate(solutionTSP.getMinPath());
    }

    private static void VRP() {
        System.out.println("VRP() - Main.java");
        VRP vrp = new VRP ();
        vrp.initialization();
        System.out.println("---");

        // решение VRP


        // перевод в TSP
        ReductionVRPToTSP toTSP = new ReductionVRPToTSP(vrp);
        ArrayList<ArrayList<Integer>> result_toTSP = toTSP.toTSP();
        System.out.println("Array toTSP (Main.java): ");
        printArray(result_toTSP);

        // Решение TSP
    }

    private static void HC() {
        System.out.println("HC() - Main.java");
        HC hc = new HC ();
        hc.initialization();
        System.out.println("---");

        // решение HC
        ExactSolutionHC solutionHC = new ExactSolutionHC(hc);
        System.out.println("hamilton() - ExactSolutionHC.java");
        if (solutionHC.hamilton(0)) solutionHC.printresult();
        else System.out.println("Result: Hamilton path does not exist.");

        // перевод в TSP
        ReductionHCToTSP toTSP = new ReductionHCToTSP(hc);
        ArrayList<ArrayList<Integer>> result_toTSP = toTSP.toTSP();
        System.out.println("Array toTSP (Main.java): ");
        printArray(result_toTSP);

        // Решение TSP
        CommonFunction comFanc = new CommonFunction();
        ArrayList<ArrayList<Float>> resultFloat_toTSP = comFanc.intToFloat(result_toTSP);
        TSP tsp = new TSP(resultFloat_toTSP);
        ExactSolutionTSP solutionTSP = new ExactSolutionTSP(tsp);
        if(solutionTSP.resultTSP() && (solutionTSP.getMinWeight() < solutionTSP.getMinPath().size()+ 1)) System.out.println("Result: "+solutionTSP.getMinPath() );
        else System.out.println("Result: TSP does not exist.");
        System.out.println("---");
    }

    private static void VC() { //Не работает!
        System.out.println("VC() - Main.java");
        VC vc = new VC ();
        vc.initialization();
        System.out.println("---");

        // решение VC
        ExactSolutionVC solutionVC = new ExactSolutionVC(vc);
        System.out.println("coverages() - ExactSolutionVC.java");
        if (solutionVC.coverages()) {
            System.out.println("Result: A graph cover of size k exists.");
            solutionVC.printresult();;
        }
        else System.out.println("Result: A graph cover of size k  does not exist.");
        System.out.println(" --- ");

        // перевод в TSP
        ReductionVCToTSP toTSP = new ReductionVCToTSP(vc);
        ArrayList<ArrayList<Integer>> result_toTSP = toTSP.toTSP();
        System.out.println("Array toTSP (Main.java): ");
        printArray(result_toTSP);

        // Решение TSP
        CommonFunction comFanc = new CommonFunction();
        ArrayList<ArrayList<Float>> resultFloat_toTSP = comFanc.intToFloat(result_toTSP);
        TSP tsp = new TSP(resultFloat_toTSP);
        ExactSolutionTSP solutionTSP = new ExactSolutionTSP(tsp);
        if(solutionTSP.resultTSP()) System.out.println("Result: TSP is exist");
        else System.out.println("Result: TSP does not exist.");
        System.out.println("---");
    }

    private static <T> void printArray(ArrayList<ArrayList<T>> result_array){
        for(int i = 0; i<result_array.size();i++)
            System.out.println(result_array.get(i));
        System.out.println(" --- ");
    }
}
