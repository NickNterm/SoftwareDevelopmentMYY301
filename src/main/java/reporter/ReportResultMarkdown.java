package reporter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import dom2app.SingleMeasureRequestModel;

public class ReportResultMarkdown implements ReportResult{
	
	@Override
	public int reportToFile(String outputFilePath, SingleMeasureRequestModel request) throws IOException {
		File report = new File(outputFilePath);
		FileWriter myWriter = new FileWriter(outputFilePath);
		myWriter.write("**" + request.getRequestName() + "**");
		myWriter.close();
		return 0;
	}
}
/*
 * **GR-TOT**

_Country ~ Greece Indicator: TOTAL_

|*Year*|*Value*|
|----|----|
|1980 |0|
|1981 |0|
|1982 |0|
|1983 |1|
|1984 |2|
|1985 |2|
|1986 |0|
|1987 |3|
|1988 |1|
|1989 |0|
|1990 |2|
|1991 |1|
|1992 |0|
|1993 |0|
|1994 |1|
|1995 |1|
|1996 |0|
|1997 |1|
|1998 |2|
|1999 |0|
|2000 |5|
|2001 |2|
|2002 |5|
|2003 |3|
|2004 |2|
|2005 |0|
|2006 |3|
|2007 |4|
|2008 |0|
|2009 |2|
|2010 |1|
|2011 |0|
|2012 |2|
|2013 |0|
|2014 |1|
|2015 |2|
|2016 |1|
|2017 |2|
|2018 |1|
|2019 |1|
|2020 |2|
|2021 |1|
|2022 |1|

DescriptiveStatsResult [count=43, min=0, gMean=0.0, mean=1.3488372093023255, median=1, max=5, kurtosis=1.2166134525756198, stdev=1.3071911167558325, sum=58]

RegressionResult [intercept=-30.68166717003926, slope=0.0160072485653881, slopeError=0.016065033273452895, Tendency stable]

*/
