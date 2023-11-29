package reporter;

import java.io.IOException;

import dom2app.ISingleMeasureRequestModel;

public class ReportResultHTML implements ReportResult{

	@Override
	public int reportToFile(String outputFilePath, ISingleMeasureRequestModel request) throws IOException {
		return 0;
	}

}
