package measurementfinder;

import java.util.List;

import dom2app.IMeasurementVector;
import dom2app.ISingleMeasureRequest;

public class FindSingleCountryIndicatorUseCase implements FindSingleCountryIndicator {
	
	private List<IMeasurementVector> vectors;
	
	public FindSingleCountryIndicatorUseCase(List<IMeasurementVector> vectors) {
		this.vectors=vectors;
	}

	@Override
	public ISingleMeasureRequest findSingleCountryIndicator(String requestName, String countryName,
			String indicatorString) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		return null;
	}

	

}
