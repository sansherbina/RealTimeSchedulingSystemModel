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
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Creator: Valery Palamarchuk
 * Date: 18.02.13
 * Time: 13:15
 */
public class LoadSettings implements ILoadSettings {

    public List<MachineConfiguration> loadCloudStucture(String path) throws IOException, SAXException, ParserConfigurationException, XmlException {
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
                id = Integer.parseInt(element.getElementsByTagName(ID).item(0).getChildNodes().item(0).getNodeValue());
                memmoryCapacity = Double.parseDouble(element.getElementsByTagName(MEMMORY_CAPACITY).item(0).getChildNodes().item(0).getNodeValue());
                ArrayList<Float> listOfProcessors = new ArrayList<Float>();
                XmlDocument doc = new XmlDocument();
                XmlNode root = doc.parse(path);
                XmlNode ratesLevelNode = root.getNodesByTagName(PROCESSORS).get(tmp);
                List<XmlNode> proc = ratesLevelNode.getNodesByTagName(PROC);
                for (XmlNode xmlNode : proc) {
                    listOfProcessors.add(Float.parseFloat(xmlNode.getNodeValue()));
                }
                processors = new double[listOfProcessors.size()];
                for (int i = 0; i < processors.length; i++) {
                    processors[i] = listOfProcessors.get(i);
                }
                accessLevel = Integer.parseInt(element.getElementsByTagName(ACCESS_LEVEL).item(0).getChildNodes().item(0).getNodeValue());
                configurations.add(new MachineConfiguration(id, memmoryCapacity, processors, accessLevel));
            }
        }

        return configurations;
    }

    public ModelSettings loadModelSettings(String path) throws IOException, SAXException, ParserConfigurationException, XmlException {
        int minimumTaskTime;
        int maximumTaskTime;
        int inputTaskFlowType;
        int taskCount;

        File xmlFile = new File(path);
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        Document document = documentBuilder.parse(xmlFile);
        document.getDocumentElement().normalize();

        minimumTaskTime = Integer.parseInt(document.getElementsByTagName(MINIMUM_TASK_TIME).item(0).getChildNodes().item(0).getNodeValue());
        maximumTaskTime = Integer.parseInt(document.getElementsByTagName(MAXIMUM_TASK_TIME).item(0).getChildNodes().item(0).getNodeValue());
        inputTaskFlowType = Integer.parseInt(document.getElementsByTagName(INPUT_TASK_FLOW_TYPE).item(0).getChildNodes().item(0).getNodeValue());
        taskCount = Integer.parseInt(document.getElementsByTagName(TASK_COUNT).item(0).getChildNodes().item(0).getNodeValue());
        //ModelSettings modelSettings = new ModelSettings(minimumTaskTime, maximumTaskTime, inputTaskFlowType, taskCount);
        //return modelSettings;
        return null;
    }
}































