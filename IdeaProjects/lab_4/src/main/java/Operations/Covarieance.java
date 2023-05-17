package Operations;
import org.apache.commons.math3.stat.correlation.Covariance;
import java.util.ArrayList;

public class Covarieance implements PairWiseComputation {
    @Override
    public Double[] compute(ArrayList<Double> column_1, ArrayList<Double> column_2) {
        double[] data_1 = column_1.stream().mapToDouble(Double::doubleValue).toArray();
        double[] data_2 = column_1.stream().mapToDouble(Double::doubleValue).toArray();

        Covariance covariance = new Covariance();
        Double cov[]= {covariance.covariance(data_1, data_2)};

        return cov;
    }


    @Override
    public String operation_name(int i, int j) {
        String text = "Коэффициент ковариации между"+String.valueOf(i) +" и " +String.valueOf(j) ;
        return text;
    }

}
