package Operations;

import org.apache.commons.math3.distribution.NormalDistribution;
import org.apache.commons.math3.stat.StatUtils;
import org.apache.commons.math3.stat.descriptive.moment.StandardDeviation;

import java.util.ArrayList;

public class ConfidenceIntervalUpper implements singleComputation{
    @Override
    public Double compute(ArrayList<Double> data) {
        double[] sample = data.stream().mapToDouble(Double::doubleValue).toArray();
        double sampleMean = StatUtils.mean(sample);
        double confidenceLevel = 0.95;
        int sampleSize = sample.length;
        double sampleStandardDeviation = new StandardDeviation().evaluate(sample);
        double standardError = sampleStandardDeviation / Math.sqrt(sampleSize);


        NormalDistribution standardNormal = new NormalDistribution(sampleMean, sampleStandardDeviation);
        double zScore = standardNormal.inverseCumulativeProbability((1 + confidenceLevel) / 2);
        double upperBound = sampleMean + (zScore * standardError);
        return upperBound;

    }

    @Override
    public String operation_name() {
        return "Confidence interval upper bound" ;
    }
}
