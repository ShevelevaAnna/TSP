package npComplateTSP;

import commonFunction.CommonFunction;

import java.util.ArrayList;

public class PathLengthEstimate {
    private Integer numRows;
    private ArrayList<ArrayList<Float>> paths;
    private float k;
    private float min;
    private float max;
    private int N;
    private ArrayList<ArrayList<Integer>> satisEdges;

    public PathLengthEstimate (ArrayList<ArrayList<Float>> TSP) {
        paths = TSP;
        numRows = TSP.size();
        satisEdges = new ArrayList<>();
        min = 100000;
        max = 0;
        N = 0;
    }

    public void basicData(){
        System.out.println("basicData() - PathLengthEstimate.java");
        for (int i = 0; i <numRows; i++)
            for (int j = 0; j < numRows; j++) {
                float curr = paths.get(i).get(j);
                if (max < curr && curr!=100000) max = curr;
                if (min > curr && curr != 0) min = curr;
                if (curr != 100000 && curr != 0) N++;
            }

        float MX = 0;
        for (int i = 0; i <numRows; i++)
            for (int j = 0; j < numRows; j++) {
                float curr = paths.get(i).get(j);
                if(curr != 100000 && curr != 0) MX += (curr - min)/(max - min);
            }
        MX = MX / (float)Math.pow(N, 2);

        k = MX / (float)0.512;
        float Skn = (float)1.04571*(1-(float)Math.exp(-6.07467/N));
        float sigma = (float)0.40854 * k;
        float DX = (float)0.0716 * (float)Math.pow(k, 2);
        float MSa = min*N+(max-min)*N*MX;
        float sqrtDSa = (max-min)*(float)Math.sqrt(N*DX);

        System.out.println("N=" + N+"\nmax=" + max+"\nmin=" + min+"\nSkn=" + Skn+"\nk=" + k+
                "\nMX=" +MX +"\nDX=" +DX +"\nsigma=" +sigma +"\nMSa=" + MSa+"\nsqrtDSa=" + sqrtDSa +"\n---");
    }

    public void satisfyingEdges(){
        System.out.println("satisfyingEdges() - PathLengthEstimate.java");
        ArrayList<Integer> edge;
        CommonFunction comFanc = new CommonFunction();

        for (int i = 0; i <numRows; i++)
            for (int j = 0; j < numRows; j++) {
                float curr = paths.get(i).get(j);
                if (curr != 100000){
                    float currB = (curr - min)/(max - min);
                    if (currB <= k){
                        edge = new ArrayList<>();
                        edge.add(i);
                        edge.add(j);
                        satisEdges.add(comFanc.add(edge));
                    }
                }
            }

        System.out.println(satisEdges);
    }
    public void hitRate (ArrayList<Integer> pathTSP) {
        System.out.println("hitRate() - PathLengthEstimate.java");
        int hitCount = 0;
        for (int i = 0; i < numRows; i++)
            for (ArrayList<Integer> satisEdge : satisEdges)
                if (i == numRows - 1) {
                    if (satisEdge.get(0).equals(pathTSP.get(i)) && satisEdge.get(1).equals(pathTSP.get(0)))
                        hitCount++;
                }
                else if (satisEdge.get(0).equals(pathTSP.get(i)) && satisEdge.get(1).equals(pathTSP.get(i + 1)))
                        hitCount++;

        System.out.println("Hit rate: "+((100*hitCount)/numRows)+"%\n---");
    }
}
