package dom2app;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.math3.util.Pair;

public class MeasurementVectorModel implements IMeasurementVector, Cloneable {
	
	private int objectId;
	private String country;
	private String iso2;
	private String iso3;
	private String indicator;
	private List<Pair<Integer, Integer>> values;
	private String stats;
	private String reg;
	
	public MeasurementVectorModel (
			int objectId, 
			String country,
			String iso2, 
			String iso3, 
			String indicator, 
			List<Pair<Integer, Integer>> values
			) {
		this.objectId = objectId;
		this.country = country;
		this.indicator = indicator;
		this.iso2 = iso2;
		this.iso3 = iso3;
		this.values = values;		
		stats = "";
		reg = "";
	}

	@Override
	public String getCountryName() {
		return country;
	}

	@Override
	public String getIndicatorString() {
		return indicator;
	}

	@Override
	public List<Pair<Integer, Integer>> getMeasurements() {
		return values;
	}

	@Override
	public String getDescriptiveStatsAsString() {
		return stats;
	}

	@Override
	public String getRegressionResultAsString() {
		return reg;
	}
	
	public void setDescriptiveStatsAsString(String stats) {
		this.stats = stats;
	}
	
	public void setRegression(String reg) {
		this.reg = reg;
	}
	
	public void setRange(int startYear, int endYear) {
		List<Pair<Integer, Integer>> range = new ArrayList<Pair<Integer,Integer>>();
		for(Pair<Integer, Integer> pair: values) {
			if(pair.getFirst() >= startYear && pair.getFirst() <= endYear) {
				range.add(pair);
			}
		}
		values.clear();
		values.addAll(range);
	}
	
	public MeasurementVectorModel clone() {
		List<Pair<Integer, Integer>> tempValues = new ArrayList<Pair<Integer,Integer>>();
		
		for(Pair<Integer, Integer> pair: values) {	
			tempValues.add(pair);
		}
		
		return new MeasurementVectorModel(
				objectId,
				country,
				iso2,
				iso3,
				indicator,
				tempValues
				);
	}
}
