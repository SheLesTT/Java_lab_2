package Operations;
import org.apache.commons.math3.stat.StatUtils;
import java.util.ArrayList;
import  org.apache.commons.math3.stat.descriptive.moment.Variance;
public class StandartDeviation implements singleComputation{
    @Override
    public Double compute(ArrayList<Double> data) {
        double[] sample = data.stream().mapToDouble(Double::doubleValue).toArray();
        double sd = Math.sqrt(StatUtils.variance(sample)) ;
        return sd;
    }

    @Override
    public String operation_name() {
        return "standard deviation";
    }
}
