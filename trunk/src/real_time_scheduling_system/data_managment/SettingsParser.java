package real_time_scheduling_system.data_managment;

import com.sun.org.apache.xerces.internal.xni.parser.XMLDocumentFilter;
import org.w3c.dom.*;
import org.xml.sax.SAXException;
import real_time_scheduling_system.model.MachineConfiguration;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Creator: Valery Palamarchuk
 * Date: 17.02.13
 * Time: 19:39
 */
public class SettingsParser {
    // TODO write class to create machineSettings
    // TODO Put results in streem
    public static void main(String[] args) throws SAXException, XmlException, ParserConfigurationException, IOException {
        String path = "settings.xml";
        File xmlFile = new File(path);
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        Document document = documentBuilder.parse(xmlFile);
        document.getDocumentElement().normalize();

        System.out.println("\nRoot element: " + document.getDocumentElement().getNodeName() + "\n");
        NodeList nodeList = document.getElementsByTagName(document.getDocumentElement().getChildNodes().item(1).getNodeName());
        for (int tmp = 0; tmp < nodeList.getLength(); tmp++) {
            Node node = nodeList.item(tmp);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) node;
                // Get Conf #:
                System.out.println("Conf #" + tmp + ":");
                // Get id:
                System.out.println("id: " + element.getElementsByTagName("id").item(0).getChildNodes().item(0).getNodeValue());
                System.out.println("memmoryCapacity: " + element.getElementsByTagName("memmoryCapacity").item(0).getChildNodes().item(0).getNodeValue());
                // Get Proc:
                ArrayList<Float> processors = new ArrayList<Float>();
                XmlDocument doc = new XmlDocument();
                XmlNode root = doc.parse(path);
                XmlNode ratesLevelNode = root.getNodesByTagName("processors").get(tmp);
                List<XmlNode> proc = ratesLevelNode.getNodesByTagName("proc");
                for (XmlNode xmlNode : proc) {
                    processors.add(Float.parseFloat(xmlNode.getNodeValue()));
                }
                double procs[] = new double[processors.size()];
                for (int i = 0; i < procs.length; i++) {
                    procs[i] = processors.get(i);
                    System.out.println("Proc[" + i + "]: " + procs[i]);
                }
                // Get accessLevel:
                System.out.println("accessLevel: " + element.getElementsByTagName("accessLevel").item(0).getChildNodes().item(0).getNodeValue());
                System.out.println();
            }
        }
    }
}
