package reporter;

import static org.junit.Assert.assertEquals;
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

public class ReportResultHTMLTest {
    private ReportResultHTML reportResultHTML;
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
        reportResultHTML = (ReportResultHTML) reportResultFactory.createReporter("html");
    }

    @Test
    public void testReportResultHTML() {
        System.out.println("Testing the reportResultHTML method");
        try {
            int counter = reportResultHTML.reportToFile("src/test/resources/output/reportResultHTML.html",
                    singleMeasureRequestModel);
            assertEquals(counter, 145);
        } catch (Exception e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        File correctObj = new File("src/test/resources/output/GR-TOT.html");
        assertTrue(correctObj.exists());
        assertTrue(correctObj.length() > 0);

        File file = new File("src/test/resources/output/reportResultHTML.html");
        assertTrue(file.exists());
        assertTrue(file.length() > 0);
        try {
            Scanner myReader = new Scanner(file);
            Scanner correctReader = new Scanner(correctObj);
            while (myReader.hasNextLine() || correctReader.hasNextLine()) {
                String data = myReader.nextLine();
                String correctData = correctReader.nextLine();
                System.out.println(data);
                System.out.println(correctData);
                assertTrue(data.equals(correctData));
            }
            myReader.close();
            correctReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
