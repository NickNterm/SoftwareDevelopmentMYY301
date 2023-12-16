package calculator;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.math3.util.Pair;
import org.junit.Test;

import dom2app.MeasurementVectorModel;

public class TestGetRegression {
	@Test
	public void getRegression() {
		GetRegressionImpl regression = new GetRegressionImpl();
		List<Pair<Integer, Integer>> pairs = new ArrayList<Pair<Integer, Integer>>();
		for(int i = 0; i < 25; i++) {
			if(i == 0 || i == 10 || i==19 || i==22 || i==24) {
				pairs.add(new Pair<Integer, Integer>(1980 + i, 1));
			}else {
				pairs.add(new Pair<Integer, Integer>(1980 + i, 0));
			}
			System.out.println(pairs.get(i));
		}
		
		                     			
		
		MeasurementVectorModel testModel = new MeasurementVectorModel(
				351,
				"Grenada",
				"GD",
				"GRD",
				"Storm",
				pairs
				);
		String regressionresult = regression.getRegression(testModel);
		String result = "[intercept=-22.784615384615382, slope=0.011538461538461537, slopeError=0.011313299180991871, Tendency stable]";
		assertEquals(regressionresult, result);
	}
}
