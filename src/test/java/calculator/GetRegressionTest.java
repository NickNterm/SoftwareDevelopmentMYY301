package calculator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.math3.util.Pair;
import org.junit.Before;
import org.junit.Test;

import dom2app.MeasurementVectorModel;

public class GetRegressionTest {
    private MeasurementVectorModel testModel;

    @Before
    public void setUp() {
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
    public void getRegression() {
        RegressionModel regression = new RegressionModel();
        String regressionresult = regression.getRegression(testModel);
        String result = "[intercept=-22.784615384615382, slope=0.011538461538461537, slopeError=0.011313299180991871, Tendency stable]";

        assertNotNull(regressionresult);

        assertEquals(regressionresult, result);
    }
}
