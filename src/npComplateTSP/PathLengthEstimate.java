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
    private CommonFunction comFanc;
    private int interval;
    private ArrayList<ArrayList<Float>> varSer;

    public PathLengthEstimate (ArrayList<ArrayList<Float>> TSP) {
        paths = TSP;
        numRows = TSP.size();
        satisEdges = new ArrayList<>();
        comFanc = new CommonFunction();
        min = 100000;
        max = 0;
        N = 0;

        for (int i = 0; i <numRows; i++)
            for (int j = 0; j < numRows; j++) {
                float curr = paths.get(i).get(j);
                if (max < curr && curr!=100000) max = curr;
                if (min > curr && curr != 0) min = curr;
                if (curr != 100000 && curr != 0) N++;
            }
    }

    public boolean isItPossibleToEstimate () {
        System.out.println("isItPossibleToEstimate() - PathLengthEstimate.java");

        varSer = new ArrayList<>();
        interval = Math.round(1+(float)3.31*(float)Math.log10(N));
        varSer = variationSeries(varSer, interval);
        float b = firstDerivative(varSer, interval);
        if (b > 0) return true;
        else return false;
    }

    private ArrayList<ArrayList<Float>> variationSeries(ArrayList<ArrayList<Float>> varSer, int interval){
        float lenInterval = (float)1/interval;

        for (int i=0; i<interval; i++){
            ArrayList<Float> ser = new ArrayList<>();
            ser.add(lenInterval*(i+1)-lenInterval/2);
            ser.add((float)0);
            varSer.add(comFanc.add(ser));
        }

        for (int i = 0; i <numRows; i++)
            for (int j = 0; j < numRows; j++) {
                float curr = paths.get(i).get(j);
                if(curr != 100000 && curr != 0) {
                    curr = (curr - min)/(max - min);
                    for (int in=0; in<interval; in++)
                        if (curr < lenInterval*(in+1) && curr >= lenInterval*in)
                            varSer.get(in).set(1,varSer.get(in).get(1)+1);
                }
            }

        varSer.get(0).set(1,varSer.get(0).get(1)+1);
        System.out.println("Variation series: "+varSer);
        return varSer;
    }

    private float firstDerivative (ArrayList<ArrayList<Float>> varSer, int interval){
        float sx=0, sy=0, sxy=0, sx_pow=0, b;
        for (int i=0; i<interval; i++){
            sx += varSer.get(i).get(0);
            sy += varSer.get(i).get(1);
            sxy += varSer.get(i).get(0)*varSer.get(i).get(1);
            sx_pow += varSer.get(i).get(0)*varSer.get(i).get(0);
        }
        b = (N*sxy-sy*sx)/(sx_pow*N-sx*sx);
        System.out.println("b="+b);
        return b;
    }

    public void basicData(boolean b){
        System.out.println("basicData() - PathLengthEstimate.java");

        float MX = 0;
        MX = varSer.get(0).get(0)*varSer.get(0).get(0)*varSer.get(0).get(1)/N;
        for (int i=1; i< interval-1;i++){
            MX += varSer.get(i).get(0)*varSer.get(i).get(0)*varSer.get(i).get(1)/N - varSer.get(i-1).get(0)*varSer.get(i-1).get(0)*varSer.get(i-1).get(1)/N;
        }
        MX += varSer.get(interval-1).get(1)/N - varSer.get(interval-2).get(0)*varSer.get(interval-2).get(0)*varSer.get(interval-2).get(1)/N;

        //float Skn = (float)1.04571*(1-(float)Math.exp(-6.07467/N));
        //float sigma = (float)0.40854 * k;

        float DX;
        if (b){
            k = MX / (float)0.512;
            DX = (float)0.0716 * (float)Math.pow(k, 2);
        }
        else{
            k = MX / (float)0.39667;
            DX = (float)0.1045 * (float)Math.pow(k, 2);
        }
        float MSa = min*numRows+(max-min)*MX*numRows;
        float sqrtDSa = (max-min)*(float)Math.sqrt(numRows*DX);
        System.out.println(min+(max-min)*MX);

        System.out.println("N=" + N+"\nmax=" + max+"\nmin=" + min+"\nk=" + k+
                "\nMX=" +MX +"\nDX=" +DX +"\nMSa=" + MSa+"\nsqrtDSa=" + sqrtDSa +"\n---");
    }

    public void satisfyingEdges(){
        System.out.println("satisfyingEdges() - PathLengthEstimate.java");
        ArrayList<Integer> edge;

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
