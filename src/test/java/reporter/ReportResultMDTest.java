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
    public void testReportResultMD() {
        System.out.println("Testing the reportResultMD method");
        try {
            int counter = reportResultMarkdown.reportToFile("src/test/resources/output/reportResultMD.md",
                    singleMeasureRequestModel);
            assertSame(counter, 54);
        } catch (Exception e) {
            e.printStackTrace();
        }
        File correctObj = new File("src/test/resources/output/GR-TOT.md");
        assertTrue(correctObj.exists());
        assertTrue(correctObj.length() > 0);

        File file = new File("src/test/resources/output/reportResultMD.md");
        assertTrue(file.exists());
        assertTrue(file.length() > 0);
        try {
            Scanner myReader = new Scanner(file);
            Scanner correctReader = new Scanner(correctObj);
            while (myReader.hasNextLine() && correctReader.hasNextLine()) {
                String data = myReader.nextLine();
                String correctData = correctReader.nextLine();
                assertTrue(data.equals(correctData));
            }
            myReader.close();
            correctReader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

}
