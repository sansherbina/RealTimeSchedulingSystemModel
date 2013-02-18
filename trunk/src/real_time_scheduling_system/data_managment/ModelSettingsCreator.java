package real_time_scheduling_system.data_managment;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * Creator: Valery Palamarchuk
 * Date: 18.02.13
 * Time: 14:02
 */
public class ModelSettingsCreator {
    private final String path;
    private ModelSettings modelSettings;

    public ModelSettingsCreator(String path) throws XmlException, SAXException, ParserConfigurationException, IOException {
        this.path = path;
        modelSettings = getModelSettings();
    }

    private ModelSettings getModelSettings() throws IOException, SAXException, ParserConfigurationException, XmlException {
        int minimumTaskTime;
        int maximumTaskTime;
        int inputTaskFlowType;
        int taskCount;

        File xmlFile = new File(path);
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        Document document = documentBuilder.parse(xmlFile);
        document.getDocumentElement().normalize();

        minimumTaskTime = Integer.parseInt(document.getElementsByTagName("minimumTaskTime").item(0).getChildNodes().item(0).getNodeValue());
        maximumTaskTime = Integer.parseInt(document.getElementsByTagName("maximumTaskTime").item(0).getChildNodes().item(0).getNodeValue());
        inputTaskFlowType = Integer.parseInt(document.getElementsByTagName("inputTaskFlowType").item(0).getChildNodes().item(0).getNodeValue());
        taskCount = Integer.parseInt(document.getElementsByTagName("taskCount").item(0).getChildNodes().item(0).getNodeValue());
        modelSettings = new ModelSettings(minimumTaskTime, maximumTaskTime, inputTaskFlowType, taskCount);
        return modelSettings;
    }

    public String toString() {
        String result = "\n--- Model Settings ---";
        result += "\nminimumTaskTime: " + modelSettings.getMinimumTaskTime();
        result += "\nmaximumTaskTime: " + modelSettings.getMaximumTaskTime();
        result += "\ninputTaskFlowType: " + modelSettings.getInputTaskFlowType();
        result += "\ntaskCount: " + modelSettings.getTaskCount();
        return result;
    }
}
