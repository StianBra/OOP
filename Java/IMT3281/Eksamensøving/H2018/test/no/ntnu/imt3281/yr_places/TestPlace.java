package no.ntnu.imt3281.yr_places;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

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

	/**
	 * Checks if fetching information from http://fil.nrk.no/yr/viktigestader/noreg.txt works correctly
	 */
	@Test
	public void testInformationFetching() {
		// First tries to get the titles (first line in the file)
		List<String> titles = getPlaceInformation().get(0);

		// Checks that the size of the list is correct
		assertEquals(14, titles.size());

		// Checks that all titles are correctly interpreted
		assertEquals("Kommunenummer", titles.get(0));
		assertEquals("Stadnamn", titles.get(1));
		assertEquals("Prioritet", titles.get(2));
		assertEquals("Stadtype nynorsk", titles.get(3));
		assertEquals("Stadtype bokmål", titles.get(4));
		assertEquals("Stadtype engelsk", titles.get(5));
		assertEquals("Kommune", titles.get(6));
		assertEquals("Fylke", titles.get(7));
		assertEquals("Lat", titles.get(8));
		assertEquals("Lon", titles.get(9));
		assertEquals("Høgd", titles.get(10));
		assertEquals("Nynorsk", titles.get(11));
		assertEquals("Bokmål", titles.get(12));
		assertEquals("Engelsk", titles.get(13));

		// Tries to get line 1026, with information about Hamar
		List<String> line1026 = getPlaceInformation().get(1025);

		// Checks that the size of the list is correct
		assertEquals(14, titles.size());

		// Creates a place to test with
		Place hamar = new Place(line1026);

		// 403	Hamar	6	By	By	City - large town	Hamar	Hedmark	60.79450	11.06798	12	http://www.yr.no/stad/Noreg/Hedmark/Hamar/Hamar/varsel.xml	http://www.yr.no/sted/Norge/Hedmark/Hamar/Hamar/varsel.xml	http://www.yr.no/place/Norway/Hedmark/Hamar/Hamar/forecast.xml
		assertEquals(403, hamar.getKommunenr());
		assertEquals("Hamar", hamar.getStedsnavn());
		assertEquals("By", hamar.getStedstype());
		assertEquals("Hamar", hamar.getKommune());
		assertEquals("Hedmark", hamar.getFylke());
		assertEquals(60.79450, hamar.getLat(), 0.00001);
		assertEquals(11.06798, hamar.getLng(), 0.00001);
		assertEquals("http://www.yr.no/sted/Norge/Hedmark/Hamar/Hamar/varsel.xml", hamar.getVarselURL());

		// Tries getting the whole list
		List<List<String>> noreg = getPlaceInformation();

		// Checks that the length of the list is correct
		assertEquals(10997, noreg.size());
	}
}
