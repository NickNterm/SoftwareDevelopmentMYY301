package app.examples;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;
import org.apache.commons.math3.stat.regression.SimpleRegression;
import org.apache.commons.math3.util.Pair;

public class SimpleUsageApacheMath {
	public static void main(String[] args) {
		List<Pair<Integer, Integer>> measurements = new ArrayList<Pair<Integer, Integer>>(); 
		measurements.add(new Pair<Integer, Integer>(1980, 10));
		measurements.add(new Pair<Integer, Integer>(1981, 20));
		measurements.add(new Pair<Integer, Integer>(1982, 30));
		measurements.add(new Pair<Integer, Integer>(1983, 48));
		measurements.add(new Pair<Integer, Integer>(1984, 50));
		
		DescriptiveStatistics stats = new DescriptiveStatistics();
		SimpleRegression regression = new SimpleRegression();
		for(Pair<Integer, Integer> xyPair: measurements) {
			int year = xyPair.getFirst();
			int value = xyPair.getSecond();
			stats.addValue(value);
			regression.addData(year, value);
		}

		long count = stats.getN();
		double minD = stats.getMin();
		double gMean = stats.getGeometricMean();
		double mean = stats.getMean();
		double medianD = stats.getPercentile(50);
		double maxD = stats.getMax();
		double kurtosis = stats.getKurtosis();
		double stdev = stats.getStandardDeviation();
		double sumD = stats.getSum();

		System.out.println("Descriptive Stats\n------------------------");
		System.out.println("Count:\t" + count + "\nMin:\t" + minD +
				"\nMedian:\t" + medianD +
				"\nMax:\t" + maxD);
		System.out.printf("Mean:\t%.2f", mean);
		//the rest can be done similarly
		
		double intercept = regression.getIntercept();
		double slope = regression.getSlope();
		double slopeError = regression.getSlopeStdErr();
		System.out.println("\n\nRegression\n------------------------");
		System.out.printf("Intercept:\t%.2f \n", intercept);
		System.out.printf("Slope:\t%.2f \n", slope);
		System.out.printf("Slope Error:\t%.2f \n", slopeError);
	}
}
