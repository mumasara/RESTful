/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
package cs9322_coffee_util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/**
 *
 * @author charles
 */
public class CoffeeParser {

  private String resourceURL;

  public CoffeeParser() {
  }

  public CoffeeParser(String resourceURL) {
    this.resourceURL = resourceURL;
  }

  public Document getParsedDocument() {
    {
      BufferedReader br = null;
      try {
        URL resource = new URL(resourceURL);
        br = new BufferedReader(new InputStreamReader(resource.openStream()));
        String orders = br.readLine();
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        InputSource inStream = new InputSource();
        inStream.setCharacterStream(new StringReader(orders));
        Document document = builder.parse(inStream);
        return document;
      } catch (SAXException ex) {
        Logger.getLogger(CoffeeParser.class.getName()).log(Level.SEVERE, null, ex);
      } catch (ParserConfigurationException ex) {
        Logger.getLogger(CoffeeParser.class.getName()).log(Level.SEVERE, null, ex);
      } catch (IOException ex) {
        Logger.getLogger(CoffeeParser.class.getName()).log(Level.SEVERE, null, ex);
      } finally {
        try {
          br.close();
        } catch (IOException ex) {
          Logger.getLogger(CoffeeParser.class.getName()).log(Level.SEVERE, null, ex);
        }
      }
      return null;
    }
  }
}
