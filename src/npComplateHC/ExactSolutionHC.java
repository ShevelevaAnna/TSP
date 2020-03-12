package npComplateHC;

import java.util.ArrayList;

public class ExactSolutionHC {
    private HC hc;
    private ArrayList <Integer> path;
    private ArrayList <Integer> current;

    public ExactSolutionHC (HC HC) {
        hc = HC;
        path = new ArrayList <>();
        current = new ArrayList <>();
        for(int j = 0; j < hc.getNumRows(); j++)
            current.add(-1);
        path.add(0);
        current.set(0,0);
    }
    public boolean hamilton (int currVert){
        boolean result = false;
        for(int v = 0; v < hc.getNumRows() && !result; v++)
        {
            if(hc.getPaths().get(path.get(currVert)).get(v) == 1)
            {
                if ((currVert + 1) == hc.getNumRows() &&  v == 0 ) result = true;
                else if (current.get(v) == -1)
                {
                    current.set(v, currVert + 1);
                    path.add(v);
                    result = hamilton (currVert + 1) ;
                    if (!result) {
                        current.set(v, -1);
                        path.remove(currVert + 1);
                    }
                }
            }
        }   return result;
    }

    public void printresult(){
        System.out.print("Hamilton path: ");
        for (int p = 0 ; p < hc.getNumRows(); p++)
            System.out.print(path.get(p)+ " ");
        System.out.println(path.get(0));
        System.out.println(" --- ");
    }
}
