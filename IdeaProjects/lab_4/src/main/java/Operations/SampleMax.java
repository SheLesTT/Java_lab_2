package Operations;

import org.apache.commons.math3.stat.StatUtils;

import java.util.ArrayList;

public class SampleMax implements singleComputation{
    @Override
    public Double compute(ArrayList<Double> data) {
        double[] sample = data.stream().mapToDouble(Double::doubleValue).toArray();
        double max = StatUtils.max(sample) ;
        return max;
    }

    @Override
    public String operation_name() {
        return "max";
    }
}
