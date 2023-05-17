package Operations;

import org.apache.commons.math3.stat.StatUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class geometricMean implements singleComputation {

    @Override
    public Double compute(ArrayList<Double> data) {
        double[] primitiveArray = data.stream().mapToDouble(Double::doubleValue).toArray();
        try {

            for (double num : primitiveArray) {
                if (num < 0) {
                    throw new IllegalArgumentException("Data contains negative values");
                }

            }
        } catch (IllegalArgumentException e) {
            System.err.println("Error: " + e.getMessage());
            return Double.NaN;
        }
        double geometricMean = StatUtils.geometricMean(primitiveArray);
        System.out.println(geometricMean);
        return geometricMean;
    }


    @Override
    public String operation_name() {
        return "geometric mean";
    }
}
