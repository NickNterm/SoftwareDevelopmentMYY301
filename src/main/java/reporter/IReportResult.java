package reporter;

import java.io.IOException;

import dom2app.SingleMeasureRequestModel;

public interface IReportResult {
	public int reportToFile(String outputFilePath, SingleMeasureRequestModel request) throws IOException ;
}
