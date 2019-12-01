package no.ntnu.imt3281.weather;

import org.w3c.dom.Document;
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

public class WeatherXML {
    public static List<String> getForecastFromXML(URL url) {
        XPathFactory xpathFactory = XPathFactory.newInstance();
        XPath xpath = xpathFactory.newXPath();
        Object result = null;

        DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = null;
        Document doc = null;

        ArrayList<String> forecast = new ArrayList<>();

        // Sets up the XML document builder
        try {
            builder = documentFactory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }

        assert builder != null;


        // Configures the XML document based on the URL given as parameter, converted to URI
        try {
            doc = builder.parse(String.valueOf(url.toURI()));
        } catch (URISyntaxException | SAXException | IOException e) {
            e.printStackTrace();
        }

        assert doc != null;


        // Uses XPath to find title (day of week) and body (forecast) from the XML document
        try {
            XPathExpression expression = xpath.compile("/weatherdata/forecast/text/location/time/*/text()");
            result = expression.evaluate(doc, XPathConstants.STRING);
            forecast.add((String) result);
            System.out.println(result.toString());
        } catch (XPathExpressionException e) {
            e.printStackTrace();
        }

        assert result != null;




        return forecast;
    }
}
