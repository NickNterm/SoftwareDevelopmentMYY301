package engine;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import calculator.GetRegressionImpl;
import calculator.GetStatsForMeasurementImpl;
import dom2app.IMeasurementVector;
import dom2app.IMeasurementVectorModel;
import dom2app.ISingleMeasureRequest;
import dom2app.ISingleMeasureRequestModel;
import engine.IMainController;
import measurementfinder.FindSingleCountryIndicatorUseCase;
import measurementfinder.FindSingleCountryIndicatorYearRangeUseCase;

public class MainControllerImpl implements IMainController {
	
	private List<IMeasurementVectorModel> values;
	private List<ISingleMeasureRequestModel> requests;
	
	public MainControllerImpl() {
		requests = new ArrayList<ISingleMeasureRequestModel>();
		values = new ArrayList<IMeasurementVectorModel>();
	}

	@Override
	public List<IMeasurementVector> load(String fileName, String delimiter) throws FileNotFoundException, IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ISingleMeasureRequest findSingleCountryIndicator(String requestName, String countryName,
			String indicatorString) throws IllegalArgumentException {
		FindSingleCountryIndicatorUseCase useCase = new FindSingleCountryIndicatorUseCase(values);
		ISingleMeasureRequestModel request =  useCase.findSingleCountryIndicator(requestName, countryName, indicatorString);
		requests.add(request);
		return request;
	}

	@Override
	public ISingleMeasureRequest findSingleCountryIndicatorYearRange(String requestName, String countryName,
			String indicatorString, int startYear, int endYear) throws IllegalArgumentException {
		FindSingleCountryIndicatorYearRangeUseCase useCase = new FindSingleCountryIndicatorYearRangeUseCase(values);
		ISingleMeasureRequestModel request =  useCase.findSingleCountryIndicatorYearRange(requestName, countryName, indicatorString, startYear, endYear);
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
		for(ISingleMeasureRequestModel request: requests) {
			if(request.getRequestName() == requestName) {
				return request;
			}
		}
		return null;
	}

	@Override
	public ISingleMeasureRequest getRegression(String requestName) {
		for(int i = 0; i < requests.size(); i++) {
			ISingleMeasureRequestModel request = requests.get(i);
			if(request.getRequestName() == requestName) {
				IMeasurementVectorModel measurement = (IMeasurementVectorModel) request.getAnswer();
				String regression = new GetRegressionImpl().getRegression(measurement);
				measurement.setDescriptiveStatsAsString(regression);
				request.setAnswer(measurement);
				return request;
			}
		}
		return null;
	}

	@Override
	public ISingleMeasureRequest getDescriptiveStats(String requestName) {
		for(int i = 0; i < requests.size(); i++) {
			ISingleMeasureRequestModel request = requests.get(i);
			if(request.getRequestName() == requestName) {
				IMeasurementVectorModel measurement = (IMeasurementVectorModel) request.getAnswer();
				String stats = new GetStatsForMeasurementImpl().getStats(measurement);
				measurement.setDescriptiveStatsAsString(stats);
				request.setAnswer(measurement);
				return request;
			}
		}
		return null;
	}

	@Override
	public int reportToFile(String outputFilePath, String requestName, String reportType) throws IOException {
		return 0;
	}

}
