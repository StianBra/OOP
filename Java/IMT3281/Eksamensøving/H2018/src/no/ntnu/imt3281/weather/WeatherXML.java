package no.ntnu.imt3281.weather;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.*;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Class to parse XML forecasts and fetch some strings from them
 */
public class WeatherXML {

    /**
     * Gets the day of week + forecast for each date in a given XML-document based on the parameter URL
     * @param url The URL to a XML forecast, for example http://www.yr.no/sted/Norge/%C3%98stfold/Halden/Demma/varsel.xml
     * @return A list of strings containing dates and forecasts
     */
    public static List<String> getForecastFromXML(URL url) {
        XPathFactory xpathFactory = XPathFactory.newInstance();
        XPath xpath = xpathFactory.newXPath();

        DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = null;
        Document doc = null;

        ArrayList<String> forecast = new ArrayList<>();

        // Sets up the XML document builder
        try {
            builder = documentFactory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            System.err.println("Could not create XML document parser! " + e.getMessage());
        }

        assert builder != null;


        // Configures the XML document based on the URL given as parameter, converted to URI
        try {
            doc = builder.parse(String.valueOf(url.toURI()));
        } catch (URISyntaxException | SAXException | IOException e) {
            System.err.println("Could not convert " + url + " to URI and/or load it in the XML document parser! " + e.getMessage());
        }

        assert doc != null;


        // Uses XPath to find title (day of week) and body (forecast) from the XML document
        try {
            XPathExpression expression = xpath.compile("/weatherdata/forecast/text/location/time/*/text()");
            NodeList nodes = (NodeList) expression.evaluate(doc, XPathConstants.NODESET);

            // Processes each XPath Node and adds their strings to the forecast list
            for (int i = 0; i < nodes.getLength(); i++) {
                forecast.add(nodes.item(i).getNodeValue());
                System.out.println(forecast.get(i));
            }

        } catch (XPathExpressionException e) {
            System.err.println("Encountered an error in the XPath-expression to find day of week + forecast! " + e.getMessage());
        }

        return forecast;
    }
}
