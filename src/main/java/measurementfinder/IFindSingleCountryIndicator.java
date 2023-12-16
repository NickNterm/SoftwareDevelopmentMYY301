package measurementfinder;

import dom2app.SingleMeasureRequestModel;

public interface IFindSingleCountryIndicator {

	SingleMeasureRequestModel findSingleCountryIndicator(String requestName, String countryName, String indicatorString)
			throws IllegalArgumentException;

}
