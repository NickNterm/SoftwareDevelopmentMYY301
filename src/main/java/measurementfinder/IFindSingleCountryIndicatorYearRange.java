package measurementfinder;

import dom2app.SingleMeasureRequestModel;

public interface IFindSingleCountryIndicatorYearRange {

	SingleMeasureRequestModel findSingleCountryIndicatorYearRange(String requestName, String countryName,
			String indicatorString, int startYear, int endYear) throws IllegalArgumentException;

}
