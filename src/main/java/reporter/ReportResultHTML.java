package reporter;

import java.io.IOException;

import dom2app.SingleMeasureRequestModel;

public class ReportResultHTML implements ReportResult{

	@Override
	public int reportToFile(String outputFilePath, SingleMeasureRequestModel request) throws IOException {
		return 0;
	}

}
