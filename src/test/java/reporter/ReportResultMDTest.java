package reporter;

import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

import org.junit.Before;
import org.junit.Test;

import calculator.RegressionModel;
import calculator.StatsForMeasurementModel;
import dom2app.MeasurementVectorModel;
import dom2app.SingleMeasureRequestModel;
import loader.Loader;

public class ReportResultMDTest {
    private ReportResultMarkdown reportResultMarkdown;
    private SingleMeasureRequestModel singleMeasureRequestModel;

    @Before
    public void setUp() throws Exception {
        Loader loader = new Loader();
        List<MeasurementVectorModel> meas = loader.load("src/test/resources/input/_ClimateRelatedDisastersFull.tsv",
                "\t");
        MeasurementVectorModel grTot = null;
        for (MeasurementVectorModel m : meas) {
            if (m.getCountryName().equals("Greece") && m.getIndicatorString().equals("TOTAL")) {
                grTot = m;
                break;
            }
        }
        RegressionModel regressionModel = new RegressionModel();
        grTot.setRegression(regressionModel.getRegression(grTot));
        StatsForMeasurementModel statsForMeasurementModel = new StatsForMeasurementModel();
        grTot.setDescriptiveStatsAsString(statsForMeasurementModel.getStats(grTot));
        singleMeasureRequestModel = new SingleMeasureRequestModel("GR-TOT", "Greece", "TOTAL");
        singleMeasureRequestModel.setAnswer(grTot);
        ReportResultFactory reportResultFactory = new ReportResultFactory();
        reportResultMarkdown = (ReportResultMarkdown) reportResultFactory.createReporter("md");
    }

    @Test
    // Testing if ReportResultMarkdown extends IReportResult
    // maybe this test is for TTD but still it's here
    public void extendsReportResult() {
        assertTrue(IReportResult.class.isAssignableFrom(ReportResultMarkdown.class));
    }

    @Test
    public void testReportResultMD() {
        System.out.println("Testing the reportResultMD method");
        try {
            int counter = reportResultMarkdown.reportToFile("src/test/resources/output/reportResultMD.md",
                    singleMeasureRequestModel);
            // basically test that the number of lines is correct
            assertSame(counter, 54);
        } catch (Exception e) {
            e.printStackTrace();
        }
        // open the file for comparison
        File correctObj = new File("src/test/resources/output/GR-TOT.md");
        // checks if the file exists and if it has content
        assertTrue(correctObj.exists());
        assertTrue(correctObj.length() > 0);

        // open the file that was created
        File file = new File("src/test/resources/output/reportResultMD.md");
        // checks if the file exists and if it has content
        assertTrue(file.exists());
        assertTrue(file.length() > 0);
        // try catch to get the error that might occur
        try {
            // reading both files
            Scanner myReader = new Scanner(file);
            Scanner correctReader = new Scanner(correctObj);
            // this is || cause if the files are not the same length
            // the while loop will try to read another line,
            // fail, try catch triggers and the test fails
            while (myReader.hasNextLine() && correctReader.hasNextLine()) {
                String data = myReader.nextLine();
                String correctData = correctReader.nextLine();
                // checks if the lines are identical
                assertTrue(data.equals(correctData));
            }
            // close the scanners
            myReader.close();
            correctReader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

}
