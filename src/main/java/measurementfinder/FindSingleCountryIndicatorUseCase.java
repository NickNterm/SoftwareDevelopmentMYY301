package measurementfinder;

import java.util.List;

import dom2app.MeasurementVectorModel;
import dom2app.SingleMeasureRequestModel;

public class FindSingleCountryIndicatorUseCase implements FindSingleCountryIndicator {
	
	private List<MeasurementVectorModel> vectors;
	
	public FindSingleCountryIndicatorUseCase(List<MeasurementVectorModel> vectors) {
		this.vectors=vectors;
	}

	@Override
	public SingleMeasureRequestModel findSingleCountryIndicator(String requestName, String countryName,
			String indicatorString) throws IllegalArgumentException {
		SingleMeasureRequestModel request = new SingleMeasureRequestModel(requestName,countryName,indicatorString);
		MeasurementVectorModel ans;
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
