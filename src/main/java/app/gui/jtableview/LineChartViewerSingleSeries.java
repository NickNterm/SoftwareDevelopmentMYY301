package app.gui.jtableview;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.math3.util.Pair;
import org.knowm.xchart.XYChart;
import org.knowm.xchart.XYChartBuilder;
import org.knowm.xchart.style.Styler.LegendPosition;

public class LineChartViewerSingleSeries {
	private String title;
	private List<Pair<Integer,Integer>> series;
	private String xAxisName;
	private String yAxisName;
	
	public LineChartViewerSingleSeries(String title, List<Pair<Integer,Integer>> series, 
			String pXAxisName, String pYAxisName) {
		this.title = title;
		this.xAxisName = pXAxisName;
		this.yAxisName = pYAxisName;	
		this.series = series;
	}
	
	public XYChart getChart() {

		// Create Chart
		XYChart chart =
				new XYChartBuilder()
				.width(800)
				.height(600)
				//.title(getClass().getSimpleName())
				.title(title)
				.xAxisTitle(xAxisName)
				.yAxisTitle(yAxisName)
				.build();

		// Customize Chart
		chart.getStyler().setLegendPosition(LegendPosition.InsideNW);
		chart.getStyler().setPlotGridLinesVisible(false);
		chart.getStyler().setXAxisDecimalPattern("#0");//no decimals, if e.g., 4 decimals, the pattern is #0.0000

		//KILLED BY DEATH
		// Series
		//chart.addSeries("test 1", Arrays.asList(0, 1, 2, 3, 4), Arrays.asList(4, 5, 9, 6, 5));

		// Series
		List<Integer> xAxisValues = new ArrayList<Integer>();
		List<Integer> yAxisValues = new ArrayList<Integer>();
		for(Pair<Integer,Integer> row: series) {
			xAxisValues.add(row.getFirst());
			yAxisValues.add(row.getSecond());
		}
		chart.addSeries(title, xAxisValues, yAxisValues);

		return chart;
	}

	public String getExampleChartName() {
		return this.title;
	}
}
