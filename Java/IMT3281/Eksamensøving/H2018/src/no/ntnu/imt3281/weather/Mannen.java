package no.ntnu.imt3281.weather;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * Label-class displaying the status of Mannen
 */
public class Mannen extends javafx.scene.control.Label {
    /**
     * Constructs a label based on the content of https://www.harmannenfalt.no/ indicating whether Mannen has fallen or not
     */
    public Mannen() {
        URL url = null;

        try {
            url = new URL("https://www.harmannenfalt.no/");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        assert url != null;

        // Code from: https://stackoverflow.com/a/5868033
        try {
            URLConnection con = url.openConnection();
            InputStream in = con.getInputStream();

            // Gets the encoding of the website, defaults to UTF-8
            String encoding = con.getContentEncoding();
            encoding = encoding == null ? "UTF-8" : encoding;

            // Reads the webpage content as a large byte array output stream
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            byte[] buf = new byte[8192];
            int len = 0;

            while ((len = in.read(buf)) != -1) {
                baos.write(buf, 0, len);
            }

            // Converts the content to a string
            String content = new String(baos.toByteArray(), encoding);

            // Checks the status of mannen based on a div in the html source
            if (content.contains("  <div id=\"yesnomaybe\">\n" + "    TJA")) {
                this.setText("TJA, Veslemannen har falt i det minste...");
            } else if (content.contains("  <div id=\"yesnomaybe\">\n" + "    JA")) {
                this.setText("JA, Mannen har falt!");
            } else if (content.contains("  <div id=\"yesnomaybe\">\n" + "    NEI")) {
                this.setText("NEI, Mannen har ikke falt!");
            } else {
                this.setText("VET IKKE, noe gikk galt ved lasting av nettsida eller parsing av innholdet");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
