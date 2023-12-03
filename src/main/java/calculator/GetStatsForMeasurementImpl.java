package calculator;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.math3.stat.StatUtils;
import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;


import dom2app.MeasurementVectorModel;

public class GetStatsForMeasurementImpl implements GetStatsForMeasurement {

	@Override
	public String getStats(MeasurementVectorModel vector) {
		DescriptiveStatistics stats = new DescriptiveStatistics();
		double[] values = new double[vector.getMeasurements().size()];
		for( int i = 0; i < vector.getMeasurements().size(); i++) {
	        stats.addValue(vector.getMeasurements().get(i).getValue());
	        values[i] = Double.valueOf(vector.getMeasurements().get(i).getValue());
		}
		
		return "[count=" + vector.getMeasurements().size() + ", min=" + stats.getMin()+ ", gMean=" + stats.getGeometricMean() 
		+ ", mean=" + stats.getMean() + ", median="  + StatUtils.percentile(values, 50) + ", max=" + stats.getMax() + ", kurtosis=" 
		+ stats.getKurtosis() + ", stdev=" + stats.getStandardDeviation() + ", sum=" + stats.getSum() + "]";
	}

}
