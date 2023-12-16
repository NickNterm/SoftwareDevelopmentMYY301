package measurementfinder;

import java.util.List;

import dom2app.MeasurementVectorModel;
import dom2app.SingleMeasureRequestModel;

public class FindSingleCountryIndicatorYearRangeImpl implements IFindSingleCountryIndicatorYearRange{

	private List<MeasurementVectorModel> vectors;
	
	public FindSingleCountryIndicatorYearRangeImpl(List<MeasurementVectorModel> vectors) {
		this.vectors = vectors;
	}
	
	@Override
	public SingleMeasureRequestModel findSingleCountryIndicatorYearRange(String requestName, String countryName,
			String indicatorString, int startYear, int endYear) throws IllegalArgumentException {
		SingleMeasureRequestModel request = new SingleMeasureRequestModel(requestName,countryName,indicatorString);
		MeasurementVectorModel ans;
		for (int i = 0; i<vectors.size(); i++) {
			ans = vectors.get(i);
			if (countryName.equals(ans.getCountryName())) {
				if (indicatorString.equals(ans.getIndicatorString())) {
					// ( *≗*)
					MeasurementVectorModel clown = ans.clone();
					clown.setRange(startYear, endYear);
					request.setAnswer(clown);
					return request;
					
				}
			}
		}
		throw new IllegalArgumentException();
	}

}
