package engine;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileNotFoundException;

import org.junit.Before;
import org.junit.Test;

public class MainControllerTest {
    private MainControllerImpl mainController;

    @Before
    public void setUp() throws Exception {
        mainController = new MainControllerImpl();
    }

    @Test
    public void testLoadingData() {
        try {
            // should have the data on the local data
            mainController.load("src/test/resources/input/gre.tsv", "\t");
        } catch (Exception e) {
            // if the data is not there, fail
            assertNull(e);
        }
    }

    @Test
    public void failLoadingData() {
        assertThrows(
                FileNotFoundException.class,
                () -> mainController.load("src/test/resources/input/mitsaras.tsv", "\t"));
    }

    @Test
    public void testFindSingleCountryIndicator() {
        try {
            // should have the data on the local data
            mainController.load("src/test/resources/input/gre.tsv", "\t");
        } catch (Exception e) {
            // if the data is not there, fail
            assertNull(e);
        }
        // should find the indicator
        var request = mainController.findSingleCountryIndicator("GrenadaTest", "Grenada", "Storm");

        assertTrue(mainController.getAllRequestNames().size() == 1);
        assertSame(mainController.getRequestByName("GrenadaTest"), request);
    }

    public void testFindSingleCountryIndicatorFail() {
        try {
            // should have the data on the local data
            mainController.load("src/test/resources/input/gre.tsv", "\t");
        } catch (Exception e) {
            // if the data is not there, fail
            assertNull(e);
        }
        // should find the indicator
        assertThrows(
                IllegalArgumentException.class,
                () -> mainController.findSingleCountryIndicator("GrenadaTest", "Grenada", "Kareklopodara"));
    }

    @Test
    public void testFindSingleCountryIndicatorYearRange() {
        try {
            // should have the data on the local data
            mainController.load("src/test/resources/input/gre.tsv", "\t");
        } catch (Exception e) {
            // if the data is not there, fail
            assertNull(e);
        }
        // should find the indicator
        var request = mainController.findSingleCountryIndicatorYearRange("GrenadaTest", "Grenada", "Storm", 1980, 2000);

        assertTrue(mainController.getAllRequestNames().size() == 1);
        assertSame(mainController.getRequestByName("GrenadaTest"), request);
    }

    @Test
    public void testFindSingleCountryIndicatorYearRangeFail() {
        try {
            // should have the data on the local data
            mainController.load("src/test/resources/input/gre.tsv", "\t");
        } catch (Exception e) {
            // if the data is not there, fail
            assertNull(e);
        }
        // should find the indicator
        assertThrows(
                IllegalArgumentException.class,
                () -> mainController.findSingleCountryIndicatorYearRange("GrenadaTest", "Grenada", "Kareklopodara",
                        1980, 2000));
    }

    @Test
    public void testGetRegression() {
        try {
            // should have the data on the local data
            mainController.load("src/test/resources/input/gre.tsv", "\t");
        } catch (Exception e) {
            // if the data is not there, fail
            assertNull(e);
        }

        mainController.findSingleCountryIndicator("GrenadaTest", "Grenada", "Storm");

        var regression = mainController.getRegression("GrenadaTest");
        String result = "[intercept=-22.784615384615382, slope=0.011538461538461537, slopeError=0.011313299180991871, Tendency stable]";

        assertNotNull(regression);

        assertEquals(regression.getRegressionResultString(), result);
    }

    @Test
    public void testGetStats() {
        try {
            // should have the data on the local data
            mainController.load("src/test/resources/input/gre.tsv", "\t");
        } catch (Exception e) {
            // if the data is not there, fail
            assertNull(e);
        }

        mainController.findSingleCountryIndicator("GrenadaTest", "Grenada", "Storm");

        var stats = mainController.getDescriptiveStats("GrenadaTest");
        String result = "[count=25, min=0, gMean=0.0, mean=0.20000000000000004, median=0, max=1, kurtosis=0.5928853754940722, stdev=0.408248290463863, sum=5]";

        assertNotNull(stats);

        assertEquals(stats.getDescriptiveStatsString(), result);
    }

    @Test
    public void testReportTXT() {
        try {
            // should have the data on the local data
            mainController.load("src/test/resources/input/gre.tsv", "\t");
        } catch (Exception e) {
            // if the data is not there, fail
            assertNull(e);
        }

        mainController.findSingleCountryIndicator("GrenadaTest", "Grenada", "Storm");

        try {
            mainController.reportToFile("src/test/resources/output/reportResultTXTMainController.txt", "GrenadaTest",
                    "text");

        } catch (Exception e) {
            // if the data is not there, fail
            assertNull(e);
        }
        File file = new File("src/test/resources/output/reportResultTXTMainController.txt");
        // checks if the file exists and if it has content
        assertTrue(file.exists());
        assertTrue(file.length() > 0);
    }

    @Test
    public void testReportMD() {
        try {
            // should have the data on the local data
            mainController.load("src/test/resources/input/gre.tsv", "\t");
        } catch (Exception e) {
            // if the data is not there, fail
            assertNull(e);
        }

        mainController.findSingleCountryIndicator("GrenadaTest", "Grenada", "Storm");

        try {
            mainController.reportToFile("src/test/resources/output/reportResultMDMainController.md", "GrenadaTest",
                    "md");

        } catch (Exception e) {
            // if the data is not there, fail
            assertNull(e);
        }

        File file = new File("src/test/resources/output/reportResultMDMainController.md");
        // checks if the file exists and if it has content
        assertTrue(file.exists());
        assertTrue(file.length() > 0);
    }

    @Test
    public void testReportHTML() {
        try {
            // should have the data on the local data
            mainController.load("src/test/resources/input/gre.tsv", "\t");
        } catch (Exception e) {
            // if the data is not there, fail
            assertNull(e);
        }

        mainController.findSingleCountryIndicator("GrenadaTest", "Grenada", "Storm");

        try {
            mainController.reportToFile("src/test/resources/output/reportResultHTMLMainController.html", "GrenadaTest",
                    "html");

        } catch (Exception e) {
            // if the data is not there, fail
            assertNull(e);
        }

        File file = new File("src/test/resources/output/reportResultHTMLMainController.html");

        // checks if the file exists and if it has content
        assertTrue(file.exists());
        assertTrue(file.length() > 0);
    }
}
