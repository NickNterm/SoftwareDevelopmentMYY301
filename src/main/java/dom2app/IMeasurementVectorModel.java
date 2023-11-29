package dom2app;

import java.util.List;

import org.apache.commons.math3.util.Pair;

public class IMeasurementVectorModel implements IMeasurementVector {
	
	private int objectId;
	private String country;
	private String iso2;
	private String iso3;
	private String indicator;
	private List<Pair<Integer, Integer>> values;
	private String stats;
	
	public IMeasurementVectorModel (
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
		return "";
	}
	
	public void setDescriptiveStatsAsString(String stats) {
		this.stats = stats;
	}
}
