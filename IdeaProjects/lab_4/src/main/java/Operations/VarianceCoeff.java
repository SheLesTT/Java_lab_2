package Operations;

import org.apache.commons.math3.stat.StatUtils;

import java.util.ArrayList;

public class VarianceCoeff implements singleComputation{
    @Override
    public Double compute(ArrayList<Double> data) {
        double[] sample = data.stream().mapToDouble(Double::doubleValue).toArray();
        double var = Math.sqrt(StatUtils.variance(sample))/ StatUtils.mean(sample);
        return var;
    }

    @Override
    public String operation_name() {
        return "variance coefficient";
    }

}
