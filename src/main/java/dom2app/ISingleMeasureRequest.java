package dom2app;
// akis3
/**
 * An interface to be used as a means to communicate request results between front- and back-end
 * 
 * @author pvassil
 */
public interface ISingleMeasureRequest {

	/**
	 * A string with the request name
	 * 
	 * @return a string with the request name
	 */
	String getRequestName();

	/**
	 * A string with the request's characteristics
	 * 
	 * @return a string with the concatenation of the country name and the requested indicator
	 */
	String getRequestFilter();

	/**
	 * A flag on whether there is an IMeasurementVector result computed for the object
	 * 
	 * @return true if there is a an IMeasurementVector result for the object; false otherwise 
	 */
	boolean isAnsweredFlag();

	/**
	 * The measurement vector for the request
	 * @return an IMeasurementVector with the <year,value> pairs of the request
	 */
	IMeasurementVector getAnswer();

	/**
	 * A String with the details of the descriptive stats for the request
	 * 
	 * A possible result
	 * DescriptiveStatsResult [count=43, min=0, gMean=0.0, mean=1.3488372093023255, median=1, max=5, kurtosis=1.2166134525756198, stdev=1.3071911167558325, sum=58]
	 * @return A String with the details of the descriptive stats for the request
	 */
	String getDescriptiveStatsString();
	
	/**
	 * A String with the details of the regression details for the request
	 * 
	 * A possible result:
	 * RegressionResult [intercept=-30.68166717003926, slope=0.0160072485653881, slopeError=0.016065033273452895, Tendency stable]
	 * @return A String with the details of the regression details for the request
	 */
	String getRegressionResultString();

}