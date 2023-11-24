package app.examples;

import java.io.FileNotFoundException;
import java.io.IOException;

import app.AppController;
import app.gui.jtableview.SimpleTableModel;


public class SimpleClientApp {

	public static void main(String[] args) {
		String filename = "src/main/resources/InputData/ClimateRelatedDisasters.tsv";
		AppController controller = new AppController();
		
		boolean loadedOK = false;
		try {
			loadedOK = controller.load(filename, "\t");
		} catch (FileNotFoundException e) {
			System.err.println("File not found for: " + filename);
			return;
		} catch (IOException e) {
			System.err.println("Buffered reader readline() failed for: " + filename);
			return;
		}
		System.out.println("Loading status: " + loadedOK);

		System.out.println("\n----------- QUERY 1 -----------------\n");
		SimpleTableModel query1 = controller.filterSingleCountryIndicator("Total-of-Australia", "Austra", "TOTAL"); 
		System.out.println(query1.toString());
		
		System.out.println("\n----------- QUERY 2 -----------------\n");
		SimpleTableModel query2 = controller.filterSingleCountryIndicatorYearRange("Total-of-Australia_10-18", "Austra", "TOTAL", 2010, 2018); 
		System.out.println(query2.toString());
		
		System.out.println("\n----------- QUERY LIST -----------------\n");
		for(String r: controller.getRequestNames()) {
			System.out.println(r);
		}

		System.out.println("\n----------- DESCR -----------------\n");
		String descrStatsResultString = controller.getDescriptiveStats("Total-of-Australia").toString();
		System.out.println(descrStatsResultString);

		System.out.println("\n----------- REGR -----------------\n");
		String regressionResultString = controller.getRegression("Total-of-Australia").toString();
		System.out.println(regressionResultString);
		
	}//end main

}//end class
