package loader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.apache.commons.math3.util.Pair;

import dom2app.MeasurementVectorModel;



// a use case for loading a data from a file
public class Loader {
	public List<MeasurementVectorModel> load(String fileName, String delimiter) throws FileNotFoundException, IOException{
		List<MeasurementVectorModel> list = new ArrayList<MeasurementVectorModel>();
		
		 File file = new File(fileName);
		 Scanner in = new Scanner(file);

		 in.nextLine();
		 while(in.hasNextLine()) {
			String line = in.nextLine();
			String[] cells = line.split(delimiter);
			
			// PROBLEMS
			// 1 last empty measures are missing
			// 2 comma isn't going to work
			 
			int objectId = Integer.parseInt(cells[0]);
			String country = cells[1];
			String iso2 = cells[2];
			String iso3 = cells[3];
			String indicator = cells[4];
			
			List<Pair<Integer, Integer>> pairs = new ArrayList<Pair<Integer, Integer>>(); 
			for(int i = 5; i<cells.length; i++) { // Anti gia cells.length >>> 20
				if(cells[i].equals("")) {
					cells[i] = "0";
				}
				int currentvalue = Integer.parseInt(cells[i]);
				Pair<Integer, Integer> pair = new Pair<Integer, Integer>(1975+i, currentvalue);
				pairs.add(pair);
			}
			MeasurementVectorModel model = new MeasurementVectorModel(objectId, country, iso2, iso3, indicator, pairs);
			System.out.println(country + " --- " + indicator + " --- " + pairs.size() + pairs.get(pairs.size()-1).getFirst());
			list.add(model);
		}
		return list;
	}

	
}
