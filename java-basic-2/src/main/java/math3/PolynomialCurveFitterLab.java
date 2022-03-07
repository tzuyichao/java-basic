package math3;

import org.apache.commons.math3.fitting.PolynomialCurveFitter;
import org.apache.commons.math3.fitting.WeightedObservedPoints;

public class PolynomialCurveFitterLab {
    public static void main(String[] args) {
        PolynomialCurveFitter polynomialCurveFitter = PolynomialCurveFitter.create(3);
        WeightedObservedPoints weightedObservedPoints = new WeightedObservedPoints();
        weightedObservedPoints.add(0, 0.664);
        weightedObservedPoints.add(1.603,0.555);
        weightedObservedPoints.add(2.501, 0.495);
        weightedObservedPoints.add(4.267, 0.382);
        weightedObservedPoints.add(5.224, 0.294);
        weightedObservedPoints.add(6.791,  0.165);
        weightedObservedPoints.add(8.533, 0.145);
        weightedObservedPoints.add(10.08, 0.153);
        weightedObservedPoints.add(11.42, 0.121);
        weightedObservedPoints.add(12.387, 0.068);
        weightedObservedPoints.add(14.239, 0);
        double[] fit = polynomialCurveFitter.fit(weightedObservedPoints.toList());
        for(int i=0; i<fit.length; i++) {
            System.out.println("Coefficient: " + fit[i]);
        }
        
    }
}
