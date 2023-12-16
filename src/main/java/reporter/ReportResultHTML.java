package reporter;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.apache.commons.math3.util.Pair;

import dom2app.IMeasurementVector;
import dom2app.SingleMeasureRequestModel;

public class ReportResultHTML implements IReportResult {

    @Override
    public int reportToFile(String outputFilePath, SingleMeasureRequestModel request) throws IOException {
        FileWriter myWriter = new FileWriter(outputFilePath);
        int counter = 0;
        if (request.isAnsweredFlag()) {
            IMeasurementVector ans = request.getAnswer();
            myWriter.write(
                    "<!doctype html>\n<html>\n<head>\n<meta http-equiv=\"Content-Type\" content\"text/html; charset=windows-1253\">\n<title>Natural Disaster Data</title>\n</head>\n<body>\n\n");
            myWriter.write("<p><b>" + request.getRequestName() + "</b></p>\n<p><i>Country ~ " + ans.getCountryName()
                    + " Indicator: " + ans.getIndicatorString() + "</i></p>\n");
            myWriter.write("<table>\n<tr>\n<td>Year</td>\t<td>Value</td>\t</tr>\n\n");
            counter = 14;
            List<Pair<Integer, Integer>> values = ans.getMeasurements();
            for (Pair<Integer, Integer> pair : values) {
                myWriter.write("<tr>\n<td>" + pair.getFirst() + "</td>\t<td>" + pair.getSecond() + "</td>\n</tr>\n");
                counter += 3;
            }
            myWriter.write("</table><p>DescriptiveStatsResult " + ans.getDescriptiveStatsAsString()
                    + "<p>RegressionResult " + ans.getRegressionResultAsString() + "</body>\n</html>");
            counter += 2;
        }
        myWriter.close();
        return counter;
    }
}
