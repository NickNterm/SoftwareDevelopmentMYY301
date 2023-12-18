package measuremenetfinder;

import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.math3.util.Pair;
import org.junit.Before;
import org.junit.Test;

import dom2app.MeasurementVectorModel;
import dom2app.SingleMeasureRequestModel;
import loader.Loader;
import measurementfinder.FindSingleCountryIndicatorImpl;

public class FindSingleCountryIndicatorTest {
    private MeasurementVectorModel testModel;
    private FindSingleCountryIndicatorImpl findSingleCountryIndicator;

    @Before
    public void setUp() throws Exception {
        // create a loader to get all the datas
        Loader loader = new Loader();
        List<MeasurementVectorModel> meas = loader.load("src/test/resources/input/gre.tsv",
                "\t");

        // push the data to the indicator
        findSingleCountryIndicator = new FindSingleCountryIndicatorImpl(
                meas);
        // create a test model
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
    // test if the finder can find the correct indicator
    public void testFindSingleCountryIndicator() {
        SingleMeasureRequestModel request = findSingleCountryIndicator
                .findSingleCountryIndicator("GrenadaTest", "Grenada", "Storm");

        // test that the objects are the same
        assertTrue(testModel.getCountryName().equals(request.getAnswer().getCountryName()));
        assertTrue(testModel.getIndicatorString().equals(request.getAnswer().getIndicatorString()));
        assertTrue(testModel.getMeasurements().equals(request.getAnswer().getMeasurements()));
        assertTrue(testModel.getDescriptiveStatsAsString().equals(request.getAnswer().getDescriptiveStatsAsString()));
        assertTrue(testModel.getRegressionResultAsString().equals(request.getAnswer().getRegressionResultAsString()));
    }

    @Test
    public void testFailingIndicator() {
        assertThrows(
                IllegalArgumentException.class,
                () -> findSingleCountryIndicator
                        .findSingleCountryIndicator("GrenadaTest", "Grenada", "Kareklopodara"));
    }
}
