package Operations;
import org.apache.commons.math3.stat.StatUtils;
import java.util.ArrayList;

public class SampleWidth implements singleComputation {
    @Override
    public Double compute(ArrayList<Double> data) {
        double[] sample = data.stream().mapToDouble(Double::doubleValue).toArray();
        double width = StatUtils.max(sample) - StatUtils.min(sample);
        return width;
    }

    @Override
    public String operation_name() {
        return "width";
    }
}
