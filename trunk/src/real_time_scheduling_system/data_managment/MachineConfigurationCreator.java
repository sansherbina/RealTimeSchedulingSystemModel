package real_time_scheduling_system.data_managment;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import real_time_scheduling_system.model.MachineConfiguration;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Creator: Valery Palamarchuk
 * Date: 18.02.13
 * Time: 13:15
 */
public class MachineConfigurationCreator {
    private final String path;
    private MachineConfiguration[] machineConfiguration;

    public MachineConfigurationCreator(String path) throws XmlException, SAXException, ParserConfigurationException, IOException {
        this.path = path;
        machineConfiguration = getMachineConfigurations();
    }

    private MachineConfiguration[] getMachineConfigurations() throws IOException, SAXException, ParserConfigurationException, XmlException {
        int id;
        double memmoryCapacity;
        double[] processors;
        int accessLevel;

        File xmlFile = new File(path);
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        Document document = documentBuilder.parse(xmlFile);
        document.getDocumentElement().normalize();

        ArrayList<MachineConfiguration> configurations = new ArrayList<MachineConfiguration>();
        NodeList nodeList = document.getElementsByTagName(document.getDocumentElement().getChildNodes().item(1).getNodeName());
        for (int tmp = 0; tmp < nodeList.getLength(); tmp++) {
            Node node = nodeList.item(tmp);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) node;
                id = Integer.parseInt(element.getElementsByTagName("id").item(0).getChildNodes().item(0).getNodeValue());
                memmoryCapacity = Double.parseDouble(element.getElementsByTagName("memmoryCapacity").item(0).getChildNodes().item(0).getNodeValue());
                ArrayList<Float> listOfProcessors = new ArrayList<Float>();
                XmlDocument doc = new XmlDocument();
                XmlNode root = doc.parse(path);
                XmlNode ratesLevelNode = root.getNodesByTagName("processors").get(tmp);
                List<XmlNode> proc = ratesLevelNode.getNodesByTagName("proc");
                for (XmlNode xmlNode : proc) {
                    listOfProcessors.add(Float.parseFloat(xmlNode.getNodeValue()));
                }
                processors = new double[listOfProcessors.size()];
                for (int i = 0; i < processors.length; i++) {
                    processors[i] = listOfProcessors.get(i);
                }
                accessLevel = Integer.parseInt(element.getElementsByTagName("accessLevel").item(0).getChildNodes().item(0).getNodeValue());
                configurations.add(new MachineConfiguration(id, memmoryCapacity, processors, accessLevel));
            }
        }
        machineConfiguration = new MachineConfiguration[configurations.size()];
        for (int i = 0; i < configurations.size(); i++) {
            machineConfiguration[i] = configurations.get(i);
        }
        return machineConfiguration;
    }

    public String toString() {
        String result = "\n--- Machine Configurations --- ";
        for (int i = 0; i < machineConfiguration.length; i++) {
            result += "\nId: " + machineConfiguration[i].getId();
            result += "\nMemmoryCapacity: " + machineConfiguration[i].getMemmoryCapacity();
            result += "\nProcessors: " + Arrays.toString(machineConfiguration[i].getProcessors());
            result += "\nAccessLevel: " + machineConfiguration[i].getAccessLevel();
        }
        return result;
    }
}































