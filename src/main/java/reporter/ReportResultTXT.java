package reporter;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.apache.commons.math3.util.Pair;

import dom2app.IMeasurementVector;
import dom2app.SingleMeasureRequestModel;

public class ReportResultTXT implements IReportResult {

    @Override
    public int reportToFile(String outputFilePath, SingleMeasureRequestModel request) throws IOException {
        FileWriter myWriter = new FileWriter(outputFilePath);
        int counter = 0;
        if (request.isAnsweredFlag()) {
            IMeasurementVector ans = request.getAnswer();
            myWriter.write(request.getRequestName() + "\nCountry ~ " + ans.getCountryName() + " Indicator: "
                    + ans.getIndicatorString());
            myWriter.write("\nYear\tValue\t\n");
            counter = 3;
            List<Pair<Integer, Integer>> values = ans.getMeasurements();
            for (Pair<Integer, Integer> pair : values) {
                myWriter.write(pair.getFirst() + "\t" + pair.getSecond() + "\n");
                counter += 1;
            }
            myWriter.write("DescriptiveStatsResult " + ans.getDescriptiveStatsAsString() + "\nRegressionResult "
                    + ans.getRegressionResultAsString() + "\n");
            counter += 2;
        }
        myWriter.close();
        return counter;
    }
}
