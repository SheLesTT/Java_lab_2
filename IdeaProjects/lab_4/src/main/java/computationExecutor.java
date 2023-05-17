import Operations.singleComputation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class computationExecutor {

    public LinkedHashMap<String,Double[]> execute(singleComputation operation, ArrayList<Double>[] samples, LinkedHashMap<String,Double[]> results){
        Double[] place = new Double[samples.length];
        for(int i = 0; i< samples.length; i ++){
            place[i] = operation.compute(samples[i]);
        }
        results.put(operation.operation_name(), place);
        return results;
    }

}
