package Operations;

import org.apache.commons.math3.stat.StatUtils;

import java.util.ArrayList;

public class SampleMin implements singleComputation{
    @Override
    public Double compute(ArrayList<Double> data) {
        double[] sample = data.stream().mapToDouble(Double::doubleValue).toArray();
        double min = StatUtils.min(sample) ;
        return min;
    }

    @Override
    public String operation_name() {
        return "min";
    }
}
