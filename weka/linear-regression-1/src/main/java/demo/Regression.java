package demo;

import weka.classifiers.functions.LinearRegression;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;

/**
 * Ref: https://medium.com/@rahulvaish/linear-regression-prediction-weka-way-3fdc1643e1b6
 *
 */
public class Regression {
    public static void main(String[] args) throws Exception {
        DataSource dataSource = new DataSource("data\\house.arff");
        Instances dataset = dataSource.getDataSet();
        dataset.setClassIndex(dataset.numAttributes()-1);

        LinearRegression model = new LinearRegression();
        model.buildClassifier(dataset);

        System.out.println("LR FORMULA: " + model);

        Instance myHouse = dataset.lastInstance();
        double price = model.classifyInstance(myHouse);
        System.out.println("--------------------------------");
        System.out.println("Predict Price: " + price);
    }
}
