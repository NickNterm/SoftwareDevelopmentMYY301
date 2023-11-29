package reporter;

import java.io.IOException;

import dom2app.ISingleMeasureRequestModel;

public interface ReportResult {
	public int reportToFile(String outputFilePath, ISingleMeasureRequestModel request) throws IOException ;
}
