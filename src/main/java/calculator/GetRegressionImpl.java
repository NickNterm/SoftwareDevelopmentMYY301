package calculator;

import org.apache.commons.math3.stat.regression.SimpleRegression;

import dom2app.IMeasurementVector;

public class GetRegressionImpl implements GetRegression{

	@Override
	public String getRegression(IMeasurementVector vector) {
		// [intercept=-30.68166717003926, slope=0.0160072485653881, slopeError=0.016065033273452895, Tendency stable]
		SimpleRegression regression = new SimpleRegression();
		double[][] data = new double [vector.getMeasurements().size()] [2];
		for (int i=0; i<vector.getMeasurements().size(); i++) {
			data[i][0] = vector.getMeasurements().get(i).getFirst();
			data[i][1] =vector.getMeasurements().get(i).getSecond();	
		}
		regression.addData(data);
		String tendency = getLabel(regression.getSlope());
		return "[intercept=" + regression.getIntercept() + ", slope=" + regression.getSlope() + ", slopeError=" + regression.getSlopeStdErr() + ", " + tendency + "]";
	}
	
	public String getLabel(double slope) { 
		if (Double.isNaN(slope))  
			return "Tendency Undefined";  
		else if(slope > 0.1)   
			return "Increased Tendency";  
		else if(slope < -0.1)   
			return "Decreased Tendency";  
		return "Tendency stable"; 
	} 

}
