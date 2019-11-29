package no.ntnu.imt3281.yr_places;

import static org.junit.Assert.*;

import java.util.Arrays;
import org.junit.Test;

/**
 * Test the Place class, objects of this class represents places in Norway with name, type  of place, county, municipality lat, lng 
 * and a string containing the URL for the weather forecast for that place.
 * Information about "places" will be downloaded from : http://fil.nrk.no/yr/viktigestader/noreg.txt
 * @author oeivindk
 *
 */
public class TestPlace {

	/**
	 * Test creating the place object from information as read by DownloadPlaces and the different getter methods in the class.
	 */
	@Test
	public void testConstructorAndGetters() {
		String data = "101	Asak kirke	55	Kyrkje	Kirke	Church	Halden	Østfold	59.14465	11.45458		http://www.yr.no/stad/Noreg/Østfold/Halden/Asak_kirke/varsel.xml	http://www.yr.no/sted/Norge/	";
		Place p = new Place(Arrays.asList(data.split("\t")));	// Constructor expects a List of String as parameter
		assertEquals(101, p.getKommunenr());
		assertEquals("Asak kirke", p.getStedsnavn());
		assertEquals("Kirke", p.getStedstype());
		assertEquals("Halden", p.getKommune());
		assertEquals("Østfold", p.getFylke());
		assertEquals(59.14465, p.getLat(), 0.00001);
		assertEquals(11.45458, p.getLng(), 0.00001);
		assertEquals("http://www.yr.no/stad/Noreg/Østfold/Halden/Asak_kirke/varsel.xml", p.getVarselURL());		// Varsel finnes ikke på bokmål, returner på nynorsk
		
		data = "101	Demma	41	Grend	Grend	Village	Halden	Østfold	59.16335	11.42916		http://www.yr.no/stad/Noreg/Østfold/Halden/Demma/varsel.xml	http://www.yr.no/sted/Norge/Østfold/Halden/Demma/varsel.xml	http://www.yr.no/place/Norway/Østfold/Halden/Demma/forecast.xml";
		p = new Place(Arrays.asList(data.split("\t")));
		assertEquals("Demma", p.getStedsnavn());
		assertEquals("http://www.yr.no/sted/Norge/Østfold/Halden/Demma/varsel.xml", p.getVarselURL());			// Varsel finnes på bokmål, returner dette
	}
}
