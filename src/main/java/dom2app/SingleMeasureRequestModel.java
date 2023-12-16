package dom2app;

public class SingleMeasureRequestModel implements ISingleMeasureRequest {

    private String requestName;
    private String country;
    private String indicator;
    private boolean isAnsweredFlag;
    private MeasurementVectorModel measurementVector;

    public SingleMeasureRequestModel(String requestName, String country, String indicator) {
        this.requestName = requestName;
        this.country = country;
        this.indicator = indicator;
        isAnsweredFlag = false;
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
        return isAnsweredFlag;
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
        isAnsweredFlag = true;
    }

}
