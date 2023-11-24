package app;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

import org.apache.commons.math3.util.Pair;

import app.gui.jtableview.SimpleTableModel;
import dom2app.IMeasurementVector;
import dom2app.ISingleMeasureRequest;
import engine.IMainController;
import engine.IMainControllerFactory;

public class AppController {

	private static IMainController mainController;
	private List<ISingleMeasureRequest> requests;
	
	public AppController() {
		IMainControllerFactory factory = new IMainControllerFactory();
		mainController = factory.createMainController(IMainControllerFactory.ControllerTypeEnum.DEFAULT);
		this.requests = new ArrayList<ISingleMeasureRequest>();
	}
	
	public boolean load(String pathString, String delimiter) throws FileNotFoundException, IOException{
			List<IMeasurementVector> res = mainController.load(pathString, delimiter);
			if (res.size()>0)
				return true;
			return false;
	}
	
	public SimpleTableModel filterSingleCountryIndicator(String requestName, String countryName, String disasterName) throws IllegalArgumentException{
		ISingleMeasureRequest req = mainController.findSingleCountryIndicator(requestName, countryName, disasterName);
		if(req != null)
			this.requests.add(req);
		
		String reqName = req.getRequestName();
		List<Pair<Integer, Integer>> data = req.getAnswer().getMeasurements();
		SimpleTableModel stm = new SimpleTableModel(reqName, data);
		return stm;
	}
	
	public SimpleTableModel filterSingleCountryIndicatorYearRange(String requestName, String countryName, String disasterID,
			int startingYear, int endingYear) {
		ISingleMeasureRequest req =  mainController.findSingleCountryIndicatorYearRange(requestName, countryName, disasterID, startingYear, endingYear);
		if(req != null)
			this.requests.add(req);
		
		String reqName = req.getRequestName();
		List<Pair<Integer, Integer>> data = req.getAnswer().getMeasurements();
		SimpleTableModel stm = new SimpleTableModel(reqName, data);
		return stm;
		
	}
	
	public String getDescriptiveStats(String requestName) {
		ISingleMeasureRequest res = mainController.getDescriptiveStats(requestName);
		if(null==res)
			return "No result available";
		return res.getDescriptiveStatsString();
	}
	
	public String getRegression(String requestName) {
		ISingleMeasureRequest res = mainController.getRegression(requestName);
		if(null==res)
			return "No result available";
		return res.getRegressionResultString();
		
	}
	
	public int createReportText(String filename, String requestName) throws IOException {
		return mainController.reportToFile(filename, requestName, "text");
	}

	public int createReportMd(String filename, String requestName) throws IOException {
		return mainController.reportToFile(filename, requestName, "md");
	}

	public int createReportHtml(String filename, String requestName) throws IOException {
		return mainController.reportToFile(filename, requestName, "html");
	}

	public List<String> getRequestNames() {
		List<String> result = new ArrayList<String>();
		if(this.requests == null)
			return result;
		
		for(ISingleMeasureRequest s: this.requests) {
			result.add(s.getRequestName());
		}
		return result;
	}



}
