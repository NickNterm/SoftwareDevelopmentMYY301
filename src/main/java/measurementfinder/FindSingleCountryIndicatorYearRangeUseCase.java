package measurementfinder;

import java.util.List;

import dom2app.IMeasurementVector;
import dom2app.ISingleMeasureRequest;
import dom2app.ISingleMeasureRequestModel;

public class FindSingleCountryIndicatorYearRangeUseCase implements FindSingleCountryIndicatorYearRange{

	private List<IMeasurementVector> vectors;
	
	public FindSingleCountryIndicatorYearRangeUseCase(List<IMeasurementVector> vectors) {
		this.vectors = vectors;
	}
	
	@Override
	public ISingleMeasureRequest findSingleCountryIndicatorYearRange(String requestName, String countryName,
			String indicatorString, int startYear, int endYear) throws IllegalArgumentException {
		ISingleMeasureRequest request = new ISingleMeasureRequestModel(requestName,countryName,indicatorString);
		IMeasurementVector ans;
		for (int i = 0; i<vectors.size(); i++) {
			ans = vectors.get(i);
			if (countryName.equals(ans.getCountryName())) {
				if (indicatorString.equals(ans.getIndicatorString())) {
					request.setAnswer(ans);
				}
			}
		}
		return request;
	}

}
