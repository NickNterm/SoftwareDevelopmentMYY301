package measurementfinder;

import dom2app.SingleMeasureRequestModel;

public interface FindSingleCountryIndicator {

	SingleMeasureRequestModel findSingleCountryIndicator(String requestName, String countryName, String indicatorString)
			throws IllegalArgumentException;

}
