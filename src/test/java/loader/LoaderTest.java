package loader;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.math3.util.Pair;
import org.junit.Before;
import org.junit.Test;

import dom2app.MeasurementVectorModel;

public class LoaderTest {
    private Loader loader;
    private MeasurementVectorModel testModel;

    @Before
    public void setUp() {
        loader = new Loader();
        List<Pair<Integer, Integer>> pairs = new ArrayList<Pair<Integer, Integer>>();
        for (int i = 0; i < 25; i++) {
            if (i == 0 || i == 10 || i == 19 || i == 22 || i == 24) {
                pairs.add(new Pair<Integer, Integer>(1980 + i, 1));
            } else {
                pairs.add(new Pair<Integer, Integer>(1980 + i, 0));
            }
        }

        testModel = new MeasurementVectorModel(
                351,
                "Grenada",
                "GD",
                "GRD",
                "Storm",
                pairs);
        System.out.println("Testing Loader");
    }

    @Test
    public void testReading() {
        System.out.println("tessst");
        System.out.println("Testing Loader");
        loader = new Loader();
        List<MeasurementVectorModel> data = new ArrayList<MeasurementVectorModel>();

        try {
            data = loader.load("src/test/resources/input/gre.tsv", "\t");
        } catch (Exception e) {
            e.printStackTrace();
        }

        assertSame(9, data.size());

        System.out.println(data.get(0));

        assertEquals(344, data.get(0).getObjectId());
        assertEquals("Greece", data.get(0).getCountryName());
        assertEquals("GR", data.get(0).getIso2());
        assertEquals("GRC", data.get(0).getIso3());
        assertEquals("Drought", data.get(0).getIndicatorString());

        assertNotNull(data);

        assertTrue(data.get(data.size() - 2).checkEquals(testModel));
    }

    @Test
    public void testReadingErrorFile() {
        loader = new Loader();
        assertThrows(
                FileNotFoundException.class,
                () -> loader.load("src/test/resources/input/MPARMPADOULIS.tsv", "\t"));
    }
}
