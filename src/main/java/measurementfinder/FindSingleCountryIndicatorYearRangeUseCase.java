package measurementfinder;

import java.util.List;

import dom2app.IMeasurementVector;
import dom2app.ISingleMeasureRequest;

public class FindSingleCountryIndicatorYearRangeUseCase implements FindSingleCountryIndicatorYearRange{

	private List<IMeasurementVector> vectors;
	
	public FindSingleCountryIndicatorYearRangeUseCase(List<IMeasurementVector> vectors) {
		this.vectors = vectors;
	}
	
	@Override
	public ISingleMeasureRequest findSingleCountryIndicatorYearRange(String requestName, String countryName,
			String indicatorString, int startYear, int endYear) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

}
