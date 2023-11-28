package loader;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.apache.commons.math3.util.Pair;

import dom2app.IMeasurementVector;

public class LoadFileUseCase {
	public List<IMeasurementVector> load(String fileName, String delimiter) throws FileNotFoundException, IOException{
		List<IMeasurementVector> list = new ArrayList<IMeasurementVector>();
		Scanner in = new Scanner(System.in);
		while(in.hasNext()) {
			String line = in.nextLine();
			String[] cells = line.split(delimiter);
			/*
	private int objectId;
	private String country;
	private String iso2;
	private String iso3;
	private String indicator;
	private List<Pair<Integer, Integer>> values;
			 * */
			 
			int objectId =Integer.parseInt(cells[0]);
			String country = cells[1];
			String iso2 = cells[2];
			String iso3 = cells[3];
			String indicator = cells[4];
			
			List<Pair<Integer, Integer>> pairs = new ArrayList<Pair<Integer, Integer>>(); 
			for(int i = 5; i<cells.length; i++) {
				int currentvalue = Integer.parseInt(cells[i]);
				Pair<Integer, Integer> pair = new Pair<Integer, Integer>(1975+i, currentvalue);
				pairs.add(pair);
			}
			
			// thes for loop, na jekinaei apo to 5, os telos
			
			
		}
		return list ;
	}

	
}
