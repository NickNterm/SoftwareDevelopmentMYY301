package dom2app;

public class SingleMeasureRequestModel implements ISingleMeasureRequest{
	
	private String requestName;
	private String country;
	private String indicator;
	private MeasurementVectorModel measurementVector;

	public SingleMeasureRequestModel(String requestName, String country, String indicator) {
		this.requestName = requestName;
		this.country = country;
		this.indicator = indicator;
	}
	
	@Override
	public String getRequestName() {
		return requestName;
	}

	@Override
	public String getRequestFilter() {
		return country + indicator;
	}

	@Override
	public boolean isAnsweredFlag() {
		return measurementVector == null;
	}

	@Override
	public IMeasurementVector getAnswer() {
		
		return measurementVector;
	}

	@Override
	public String getDescriptiveStatsString() {
		return measurementVector.getDescriptiveStatsAsString();
	}

	@Override
	public String getRegressionResultString() {
		return measurementVector.getRegressionResultAsString();
	}
	
	public void setAnswer(MeasurementVectorModel vector) {
		measurementVector = vector;
	}

}
