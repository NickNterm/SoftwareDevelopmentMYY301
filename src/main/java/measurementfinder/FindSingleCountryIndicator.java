package measurementfinder;

import dom2app.ISingleMeasureRequest;

public interface FindSingleCountryIndicator {

	ISingleMeasureRequest findSingleCountryIndicator(String requestName, String countryName, String indicatorString)
			throws IllegalArgumentException;

}
