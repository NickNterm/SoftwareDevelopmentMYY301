package reporter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import dom2app.SingleMeasureRequestModel;

public class ReportResultTXT implements ReportResult{

	@Override
	public int reportToFile(String outputFilePath,SingleMeasureRequestModel request) throws IOException {
		File report = new File("Report.txt");
		FileWriter myWriter = new FileWriter(outputFilePath+"Report.txt");
		myWriter.write("");
		myWriter.close();
		return 0;
	}

}
