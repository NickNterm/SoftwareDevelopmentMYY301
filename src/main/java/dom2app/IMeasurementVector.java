package dom2app;

import java.util.List;

import org.apache.commons.math3.util.Pair;

/**
 * Contains the data of a measurement vector, i.e., the data for a (country, disaster type) combination
 * 
 * @author pvassil
 *
 */
public interface IMeasurementVector {

	/**
	 * Returns the name of the country
	 * @return a String with the name of the country
	 */
	String getCountryName();

	/**
	 * Returns the name of the disaster type
	 * @return a String with the name of the disaster type
	 */
	String getIndicatorString();

	/**
	 * Returns the data of the combination <country, indicator> 
	 * @return a List of Pairs of <year, value> recording the number of incidents of each year for the combination <country, indicator>
	 */
	List<Pair<Integer, Integer>> getMeasurements();

	/**
	 * Returns a string with the descriptive stats for a measurement vector
	 * @return a String with the descriptive stats for a measurement vector
	 */
	String getDescriptiveStatsAsString();

	/**
	 * a string with the regression results for a measurement vector
	 * @return a String with the regression results for a measurement vector
	 */
	String getRegressionResultAsString();

}