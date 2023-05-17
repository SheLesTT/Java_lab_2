package Operations;

import java.util.ArrayList;

public interface PairWiseComputation {
    public Double[] compute(ArrayList<Double> column_1, ArrayList<Double> column_2);


    public String operation_name(int i, int j);
}
