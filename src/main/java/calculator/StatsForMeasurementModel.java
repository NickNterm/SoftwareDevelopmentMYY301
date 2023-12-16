package calculator;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.math3.stat.StatUtils;
import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;

import dom2app.MeasurementVectorModel;

public class StatsForMeasurementModel implements IStatsForMeasurement {

    @Override
    public String getStats(MeasurementVectorModel vector) {
        DescriptiveStatistics stats = new DescriptiveStatistics();
        double[] values = new double[vector.getMeasurements().size()];
        for (int i = 0; i < vector.getMeasurements().size(); i++) {
            stats.addValue(vector.getMeasurements().get(i).getValue());
            values[i] = Double.valueOf(vector.getMeasurements().get(i).getValue());
        }
        DecimalFormat format = new DecimalFormat("0.#");
        return "[count=" + format.format(vector.getMeasurements().size()) + ", min=" + format.format(stats.getMin())
                + ", gMean="
                + stats.getGeometricMean()
                + ", mean=" + stats.getMean() + ", median="
                + format.format(StatUtils.percentile(values, 50)) + ", max="
                + format.format(stats.getMax()) + ", kurtosis="
                + stats.getKurtosis() + ", stdev=" + stats.getStandardDeviation()
                + ", sum="
                + format.format(stats.getSum()) + "]";
    }

}
