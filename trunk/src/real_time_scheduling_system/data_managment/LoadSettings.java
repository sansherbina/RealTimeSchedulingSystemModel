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
 * Created with IntelliJ IDEA. Creator: Valery Palamarchuk Date: 18.02.13 Time:
 * 13:15
 */
public class LoadSettings implements ILoadSettings {

	public List<MachineConfiguration> loadCloudStucture(String path)
			throws IOException, SAXException, ParserConfigurationException,
			XmlException {
		int id;
		double memmoryCapacity;
		double[] processors;
		int accessLevel;

		File xmlFile = new File(path);
		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory
				.newInstance();
		DocumentBuilder documentBuilder = documentBuilderFactory
				.newDocumentBuilder();
		Document document = documentBuilder.parse(xmlFile);
		document.getDocumentElement().normalize();

		ArrayList<MachineConfiguration> configurations = new ArrayList<MachineConfiguration>();
		NodeList nodeList = document.getElementsByTagName(document
				.getDocumentElement().getChildNodes().item(1).getNodeName());
		for (int tmp = 0; tmp < nodeList.getLength(); tmp++) {
			Node node = nodeList.item(tmp);
			if (node.getNodeType() == Node.ELEMENT_NODE) {
				Element element = (Element) node;
				id = Integer.parseInt(element.getElementsByTagName(ID).item(0)
						.getChildNodes().item(0).getNodeValue());
				memmoryCapacity = Double.parseDouble(element
						.getElementsByTagName(MEMMORY_CAPACITY).item(0)
						.getChildNodes().item(0).getNodeValue());
				ArrayList<Float> listOfProcessors = new ArrayList<Float>();
				XmlDocument doc = new XmlDocument();
				XmlNode root = doc.parse(path);
				XmlNode ratesLevelNode = root.getNodesByTagName(PROCESSORS)
						.get(tmp);
				List<XmlNode> proc = ratesLevelNode.getNodesByTagName(PROC);
				for (XmlNode xmlNode : proc) {
					listOfProcessors.add(Float.parseFloat(xmlNode
							.getNodeValue()));
				}
				processors = new double[listOfProcessors.size()];
				for (int i = 0; i < processors.length; i++) {
					processors[i] = listOfProcessors.get(i);
				}
				accessLevel = Integer.parseInt(element
						.getElementsByTagName(ACCESS_LEVEL).item(0)
						.getChildNodes().item(0).getNodeValue());
				configurations.add(new MachineConfiguration(id,
						memmoryCapacity, processors, accessLevel));
			}
		}

		return configurations;
	}

	public ModelSettings loadModelSettings(String path) throws IOException,
			SAXException, ParserConfigurationException, XmlException {
		int minimumTaskTime;
		int maximumTaskTime;
		int inputTaskFlowType;

		float taskLoadingTimeInterval;
		float taskLoadingCountBorder;
		int schedulingAlgorithmType;
		float modelingTime;
		double normalDistributionM;
		double normalDistributionSigma;
		double minProcessor;
		double maxProcessor;
		double minMemmoryCapacity;
		double maxMemmoryCapacity;
		float minWorkTimePercentage;
		float maxWorkTimePercentage;
		double minNewTaskSpeed;
		double maxNewTaskSpeed;
		int minAccessLevel;
		int maxAccessLevel;

		File xmlFile = new File(path);
		DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory
				.newInstance();
		DocumentBuilder documentBuilder = documentBuilderFactory
				.newDocumentBuilder();
		Document document = documentBuilder.parse(xmlFile);
		document.getDocumentElement().normalize();

		minimumTaskTime = Integer.parseInt(document
				.getElementsByTagName(MINIMUM_TASK_TIME).item(0)
				.getChildNodes().item(0).getNodeValue());
		maximumTaskTime = Integer.parseInt(document
				.getElementsByTagName(MAXIMUM_TASK_TIME).item(0)
				.getChildNodes().item(0).getNodeValue());
		inputTaskFlowType = Integer.parseInt(document
				.getElementsByTagName(INPUT_TASK_FLOW_TYPE).item(0)
				.getChildNodes().item(0).getNodeValue());

		taskLoadingTimeInterval = Integer.parseInt(document
				.getElementsByTagName(LoadSettings.taskLoadingTimeInterval)
				.item(0).getChildNodes().item(0).getNodeValue());
		taskLoadingCountBorder = Integer.parseInt(document
				.getElementsByTagName(LoadSettings.taskLoadingCountBorder)
				.item(0).getChildNodes().item(0).getNodeValue());
		schedulingAlgorithmType = Integer.parseInt(document
				.getElementsByTagName(LoadSettings.schedulingAlgorithmType)
				.item(0).getChildNodes().item(0).getNodeValue());
		modelingTime = Integer.parseInt(document
				.getElementsByTagName(LoadSettings.modelingTime).item(0)
				.getChildNodes().item(0).getNodeValue());
		normalDistributionM = Integer.parseInt(document
				.getElementsByTagName(LoadSettings.normalDistributionM).item(0)
				.getChildNodes().item(0).getNodeValue());
		normalDistributionSigma = Integer.parseInt(document
				.getElementsByTagName(LoadSettings.normalDistributionSigma)
				.item(0).getChildNodes().item(0).getNodeValue());
		minProcessor = Integer.parseInt(document
				.getElementsByTagName(LoadSettings.minProcessor).item(0)
				.getChildNodes().item(0).getNodeValue());
		maxProcessor = Integer.parseInt(document
				.getElementsByTagName(LoadSettings.maxProcessor).item(0)
				.getChildNodes().item(0).getNodeValue());
		minMemmoryCapacity = Integer.parseInt(document
				.getElementsByTagName(LoadSettings.minMemmoryCapacity).item(0)
				.getChildNodes().item(0).getNodeValue());
		maxMemmoryCapacity = Integer.parseInt(document
				.getElementsByTagName(LoadSettings.maxMemmoryCapacity).item(0)
				.getChildNodes().item(0).getNodeValue());
		minWorkTimePercentage = Integer.parseInt(document
				.getElementsByTagName(LoadSettings.minWorkTimePercentage)
				.item(0).getChildNodes().item(0).getNodeValue());
		maxWorkTimePercentage = Integer.parseInt(document
				.getElementsByTagName(LoadSettings.maxWorkTimePercentage)
				.item(0).getChildNodes().item(0).getNodeValue());
		minNewTaskSpeed = Integer.parseInt(document
				.getElementsByTagName(LoadSettings.minNewTaskSpeed).item(0)
				.getChildNodes().item(0).getNodeValue());
		maxNewTaskSpeed = Integer.parseInt(document
				.getElementsByTagName(LoadSettings.maxNewTaskSpeed).item(0)
				.getChildNodes().item(0).getNodeValue());
		minAccessLevel = Integer.parseInt(document
				.getElementsByTagName(LoadSettings.minAccessLevel).item(0)
				.getChildNodes().item(0).getNodeValue());
		maxAccessLevel = Integer.parseInt(document
				.getElementsByTagName(LoadSettings.maxAccessLevel).item(0)
				.getChildNodes().item(0).getNodeValue());
		ModelSettings modelSettings = new ModelSettings(minimumTaskTime,
				maximumTaskTime, inputTaskFlowType, taskLoadingTimeInterval,
				taskLoadingCountBorder, schedulingAlgorithmType, modelingTime,
				normalDistributionM, normalDistributionSigma, minProcessor,
				maxProcessor, minMemmoryCapacity, maxMemmoryCapacity,
				minWorkTimePercentage, maxWorkTimePercentage, minNewTaskSpeed,
				maxNewTaskSpeed, minAccessLevel, maxAccessLevel);
		return modelSettings;
		// return null;
	}
}
