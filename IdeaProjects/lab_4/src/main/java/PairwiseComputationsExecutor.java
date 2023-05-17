import Operations.PairWiseComputation;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public class PairwiseComputationsExecutor {
    public LinkedHashMap<String,Double[]> execute(PairWiseComputation operation, ArrayList<Double>[] samples, LinkedHashMap<String,Double[]> results) {
        for (int i = 0; i < samples.length; i++) {
            for (int j = i + 1; j < samples.length; j++) {
                ArrayList<Double> first = samples[i];
                ArrayList<Double> second = samples[j];
                Double[] result = operation.compute(first, second);
                System.out.println("(" + first + ", " + second + ")");
                results.put(operation.operation_name(i, j), result);
            }
        }
        return results;
    }
}
