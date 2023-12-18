package calculator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.math3.util.Pair;
import org.junit.Before;
import org.junit.Test;

import dom2app.MeasurementVectorModel;

public class StatsForMeasurementModelTest {
    private StatsForMeasurementModel statsForMeasurementModel;
    private MeasurementVectorModel testModel;

    @Before
    public void setUp() throws Exception {
        statsForMeasurementModel = new StatsForMeasurementModel();
        List<Pair<Integer, Integer>> pairs = new ArrayList<Pair<Integer, Integer>>();
        for (int i = 0; i < 25; i++) {
            if (i == 0 || i == 10 || i == 19 || i == 22 || i == 24) {
                pairs.add(new Pair<Integer, Integer>(1980 + i, 1));
            } else {
                pairs.add(new Pair<Integer, Integer>(1980 + i, 0));
            }
        }

        testModel = new MeasurementVectorModel(
                351,
                "Grenada",
                "GD",
                "GRD",
                "Storm",
                pairs);
    }

    @Test
    public void testStatsForMeasurementModel() {
        String statsResult = statsForMeasurementModel.getStats(testModel);
        String result = "[count=25, min=0, gMean=0.0, mean=0.20000000000000004, median=0, max=1, kurtosis=0.5928853754940722, stdev=0.408248290463863, sum=5]";

        assertNotNull(statsResult);

        assertEquals(statsResult, result);
    }
}
