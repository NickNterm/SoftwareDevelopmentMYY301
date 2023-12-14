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
import dom2app.MeasurementVectorModel;
import dom2app.ISingleMeasureRequest;
import dom2app.SingleMeasureRequestModel;
import engine.IMainController;
import loader.LoadFileUseCase;
import measurementfinder.FindSingleCountryIndicatorUseCase;
import measurementfinder.FindSingleCountryIndicatorYearRangeUseCase;
import reporter.ReportResult;
import reporter.ReportResultFactory;

public class MainControllerImpl implements IMainController {
	
	private List<MeasurementVectorModel> values;
	private List<SingleMeasureRequestModel> requests;
	
	public MainControllerImpl() {
		requests = new ArrayList<SingleMeasureRequestModel>();
		values = new ArrayList<MeasurementVectorModel>();
	}

	@Override
	public List<IMeasurementVector> load(String fileName, String delimiter) throws FileNotFoundException, IOException {
		LoadFileUseCase loader = new LoadFileUseCase();
		List<MeasurementVectorModel> result = loader.load(fileName, delimiter);
		List<IMeasurementVector> temp = new ArrayList<IMeasurementVector>();
		values.clear();
		for (MeasurementVectorModel x : result) {
			temp.add(x);
			values.add(x);
		}
		
		return temp;
	}

	@Override
	public ISingleMeasureRequest findSingleCountryIndicator(String requestName, String countryName,
			String indicatorString) throws IllegalArgumentException {
		FindSingleCountryIndicatorUseCase useCase = new FindSingleCountryIndicatorUseCase(values);
		SingleMeasureRequestModel request =  useCase.findSingleCountryIndicator(requestName, countryName, indicatorString);
		requests.add(request);
		return request;
	}

	@Override
	public ISingleMeasureRequest findSingleCountryIndicatorYearRange(String requestName, String countryName,
			String indicatorString, int startYear, int endYear) throws IllegalArgumentException {
		FindSingleCountryIndicatorYearRangeUseCase useCase = new FindSingleCountryIndicatorYearRangeUseCase(values);
		SingleMeasureRequestModel request =  useCase.findSingleCountryIndicatorYearRange(requestName, countryName, indicatorString, startYear, endYear);
		requests.add(request);
		return request;
	}

	@Override
	public Set<String> getAllRequestNames() {
		Set<String> set = new HashSet<String> (); 
		for(ISingleMeasureRequest request: requests) {
			set.add(request.getRequestName());
		}
		return set;
	}

	@Override
	public ISingleMeasureRequest getRequestByName(String requestName) {
		for(SingleMeasureRequestModel request: requests) {
			if(request.getRequestName() == requestName) {
				return request;
			}
		}
		return null;
	}

	@Override
	public ISingleMeasureRequest getRegression(String requestName) {
		for(int i = 0; i < requests.size(); i++) {
			SingleMeasureRequestModel request = requests.get(i);
			if(request.getRequestName().equals(requestName)) {
				MeasurementVectorModel measurement = (MeasurementVectorModel) request.getAnswer();
				String regression = new GetRegressionImpl().getRegression(measurement);
				measurement.setRegression(regression);
				request.setAnswer(measurement);
				return request;
			}
		}
		return null;
	}

	@Override
	public ISingleMeasureRequest getDescriptiveStats(String requestName) {
		for(int i = 0; i < requests.size(); i++) {
			SingleMeasureRequestModel request = requests.get(i);
			if(request.getRequestName().equals(requestName)) {
				MeasurementVectorModel measurement = (MeasurementVectorModel) request.getAnswer();
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
		ReportResultFactory factory = new ReportResultFactory();
		ReportResult reporter = factory.createMainController(reportType);
		SingleMeasureRequestModel model = null;
		for (SingleMeasureRequestModel request: requests) {
			if (request.getRequestName() == requestName) {
				model = request;
			}
		}
		return reporter.reportToFile(outputFilePath, model);
	}
}
