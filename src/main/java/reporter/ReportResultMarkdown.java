package reporter;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.apache.commons.math3.util.Pair;

import dom2app.IMeasurementVector;
import dom2app.SingleMeasureRequestModel;

public class ReportResultMarkdown implements IReportResult {

    @Override
    public int reportToFile(String outputFilePath, SingleMeasureRequestModel request) throws IOException {
        FileWriter myWriter = new FileWriter(outputFilePath);
        int counter = 0;
        if (request.isAnsweredFlag()) {
            IMeasurementVector ans = request.getAnswer();
            myWriter.write("**" + request.getRequestName() + "**\n\n_Country ~ " + ans.getCountryName() + " Indicator: "
                    + ans.getIndicatorString());
            myWriter.write("_\n\n|*Year*|*Value*|\n|----|----|\n");
            counter = 6;
            List<Pair<Integer, Integer>> values = ans.getMeasurements();
            for (Pair<Integer, Integer> pair : values) {
                myWriter.write("|" + pair.getFirst() + " |" + pair.getSecond() + "|\n");
                counter += 1;
            }
            myWriter.write("\nDescriptiveStatsResult " + ans.getDescriptiveStatsAsString() + "\n\nRegressionResult "
                    + ans.getRegressionResultAsString() + "\n\n");
            counter += 5;
        }
        myWriter.close();
        return counter;
    }
}
