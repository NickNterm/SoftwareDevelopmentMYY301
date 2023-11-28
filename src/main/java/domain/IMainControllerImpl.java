package domain;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import dom2app.IMeasurementVector;
import dom2app.ISingleMeasureRequest;
import engine.IMainController;
import loader.LoadFileUseCase;
import measurementfinder.FindSingleCountryIndicatorUseCase;
import measurementfinder.FindSingleCountryIndicatorYearRangeUseCase;

public class IMainControllerImpl implements IMainController {
	
	private List<IMeasurementVector> values;
	private List<ISingleMeasureRequest> requests = new ArrayList<ISingleMeasureRequest>();

	@Override
	public List<IMeasurementVector> load(String fileName, String delimiter) throws FileNotFoundException, IOException {
		LoadFileUseCase loader = new LoadFileUseCase();
		List<IMeasurementVector> result = loader.load( fileName, delimiter);
		return result;
	}

	@Override
	public ISingleMeasureRequest findSingleCountryIndicator(String requestName, String countryName,
			String indicatorString) throws IllegalArgumentException {
		FindSingleCountryIndicatorUseCase useCase = new FindSingleCountryIndicatorUseCase(values);
		ISingleMeasureRequest request =  useCase.findSingleCountryIndicator(requestName, countryName, indicatorString);
		requests.add(request);
		return request;
	}

	@Override
	public ISingleMeasureRequest findSingleCountryIndicatorYearRange(String requestName, String countryName,
			String indicatorString, int startYear, int endYear) throws IllegalArgumentException {
		FindSingleCountryIndicatorYearRangeUseCase useCase = new FindSingleCountryIndicatorYearRangeUseCase(values);
		ISingleMeasureRequest request =  useCase.findSingleCountryIndicatorYearRange(requestName, countryName, indicatorString, startYear, endYear);
		requests.add(request);
		return request;
	}

	@Override
	public Set<String> getAllRequestNames() {
		Set<String> set = new HashSet<String> (); 
		for(ISingleMeasureRequest request: requests) {
			set.add(request.getRequestName());
		}
		return null;
	}

	@Override
	public ISingleMeasureRequest getRequestByName(String requestName) {
		for(ISingleMeasureRequest request: requests) {
			if(request.getRequestName().equals(requestName)) return request;
		}
		return null;
	}

	@Override
	public ISingleMeasureRequest getRegression(String requestName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ISingleMeasureRequest getDescriptiveStats(String requestName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int reportToFile(String outputFilePath, String requestName, String reportType) throws IOException {
		// TODO Auto-generated method stub
		return 0;
	}

}
