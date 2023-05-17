package Operations;

import org.apache.commons.math3.stat.StatUtils;

import java.util.ArrayList;

public class Mean implements singleComputation {

    @Override
    public Double compute(ArrayList<Double> data) {
        double[] sample = data.stream().mapToDouble(Double::doubleValue).toArray();
        double mean = StatUtils.mean(sample);
        return mean;
    }

    @Override
    public String operation_name() {
        return "mean";
    }
}
