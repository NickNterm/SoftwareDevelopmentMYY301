package loader;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.math3.util.Pair;
import org.junit.Test;

import dom2app.MeasurementVectorModel;

public class TestLoadFileUseCase {
	@Test
	public void testReading(){  
		LoadFileUseCase loader = new LoadFileUseCase();
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

		List<MeasurementVectorModel> data = new ArrayList<MeasurementVectorModel>();

		try {
			data = loader.load("src/test/resources/input/gre.tsv", "\t");
		} catch (Exception e) {
			e.printStackTrace();
		}
        assertSame(9, data.size());  
        assertTrue(data.get(data.size() - 2).equals(testModel));
    } 
	
	@Test
	public void testReadingErrorFile(){
		LoadFileUseCase loader = new LoadFileUseCase();
        assertThrows(
        		FileNotFoundException.class,
        		() -> loader.load("src/test/resources/input/MPARMPADOULIS.tsv", "\t")
        		);  
	}
}
