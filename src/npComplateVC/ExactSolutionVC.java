package npComplateVC;

import commonFunction.CommonFunction;

import java.util.ArrayList;

public class ExactSolutionVC {
    private VC vc;
    private ArrayList <ArrayList<Integer>> edges;
    private ArrayList <Integer> verticesDel;
    private ArrayList <ArrayList<Integer>> deletedVert;
    private ArrayList <Integer> vertices;
    private CommonFunction comFanc;

    public ExactSolutionVC (VC VC) {
        vc = VC;
        edges = new ArrayList <>();
        verticesDel = new ArrayList <>();
        vertices = new ArrayList <>();
        deletedVert = new ArrayList <>();
        comFanc = new CommonFunction();

        for (int i = 0; i < vc.getNumRows(); i++)
            edges.add(comFanc.add(vc.getPaths().get(i)));
        for (int i = 0; i < vc.getNumVertices(); i++)
            vertices.add(i);
    }
    public boolean coverages (){
        ArrayList <ArrayList<Integer>> currDeletedVert;
        boolean result = false;

        if (edges.size() == 0 && verticesDel.size() == vc.getNumCoverage()) result = true;

        for(int i = 0; i < vertices.size() && !result; i++) {
            currDeletedVert = addVert(i);
            result = coverages();
            if (!result) deleteVert(currDeletedVert, i);
        }
        return result;
    }

    private ArrayList <ArrayList<Integer>> addVert(int i){
        ArrayList <ArrayList<Integer>> currDeletedVert = new ArrayList <>();

        verticesDel.add(vertices.get(i));
        vertices.remove(i);

        for (int j = 0; j < edges.size(); j++)
            if (edges.get(j).get(0).equals(verticesDel.get(verticesDel.size() - 1)) ||
                    edges.get(j).get(1).equals(verticesDel.get(verticesDel.size() - 1))) {
                currDeletedVert.add(comFanc.add(edges.get(j)));
                edges.remove(j);
                j--;
            }
        for (ArrayList<Integer> integers : currDeletedVert) deletedVert.add(comFanc.add(integers));
        return currDeletedVert;
    }

    private void deleteVert(ArrayList <ArrayList<Integer>> currDeletedVert, int i){
        for (ArrayList<Integer> integers : currDeletedVert) {
            edges.add(comFanc.add(integers));
            deletedVert.remove(deletedVert.size() - 1);
        }
        vertices.add(i, verticesDel.get(verticesDel.size() - 1));
        verticesDel.remove(verticesDel.size() - 1);
    }

    public void printresult(){
        System.out.print("A graph cover: ");
        for (Integer integer : verticesDel) System.out.print(integer + " ");
        System.out.println();
    }
}
