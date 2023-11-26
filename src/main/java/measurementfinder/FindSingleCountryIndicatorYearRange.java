package measurementfinder;

import dom2app.ISingleMeasureRequest;

public interface FindSingleCountryIndicatorYearRange {

	ISingleMeasureRequest findSingleCountryIndicatorYearRange(String requestName, String countryName,
			String indicatorString, int startYear, int endYear) throws IllegalArgumentException;

}
