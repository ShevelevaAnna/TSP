import npComplate2CAP.*;
import npComplateVRP.*;
import npComplateHC.*;
import npComplateVC.*;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        //CAP();
        //VRP();
        //HC();
        VC();
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
        System.out.println("Array toTSP (Main.java): " + result_toTSP + "\n --- \n");
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
        System.out.println("Array toTSP (Main.java): " + result_toTSP + "\n --- \n");
    }

    private static void HC() {
        System.out.println("HC() - Main.java");
        HC hc = new HC ();
        hc.initialization();
        System.out.println("---");

        // решение HC


        // перевод в TSP
        ReductionHCToTSP toTSP = new ReductionHCToTSP(hc);
        ArrayList<ArrayList<Integer>> result_toTSP = toTSP.toTSP();
        System.out.println("Array toTSP (Main.java): " + result_toTSP + "\n --- \n");
    }

    private static void VC() {
        System.out.println("VC() - Main.java");
        VC vc = new VC ();
        vc.initialization();
        System.out.println("---");

        // решение HC


        // перевод в TSP

    }
}
