package Operations;

import java.util.ArrayList;

public class SampleSize implements singleComputation{
    @Override
    public Double compute(ArrayList<Double> data) {
        return (double) data.size();
    }

    @Override
    public String operation_name() {
        return "size";
    }
}
