package real_time_scheduling_system.data_managment;


import org.xml.sax.SAXException;
import real_time_scheduling_system.model.MachineConfiguration;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.List;

public interface ILoadSettings {
    public final String ID = "id";
    public final String MEMMORY_CAPACITY = "memmoryCapacity";
    public final String PROCESSORS = "processors";
    public final String PROC = "proc";
    public final String ACCESS_LEVEL = "accessLevel";
    public final String MINIMUM_TASK_TIME = "minimumTaskTime";
    public final String MAXIMUM_TASK_TIME = "maximumTaskTime";
    public final String INPUT_TASK_FLOW_TYPE = "inputTaskFlowType";
    public final String taskLoadingTimeInterval = "taskLoadingTimeInterval";
    public final String taskLoadingCountBorder = "taskLoadingCountBorder";
    public final String schedulingAlgorithmType = "schedulingAlgorithmType";
    public final String modelingTime = "modelingTime";
    public final String normalDistributionM = "normalDistributionM";
    public final String normalDistributionSigma = "normalDistributionSigma";
    public final String minProcessor = "minProcessor";
    public final String maxProcessor = "maxProcessor";
    public final String minMemmoryCapacity = "minMemmoryCapacity";
    public final String maxMemmoryCapacity = "maxMemmoryCapacity";
    public final String minWorkTimePercentage = "minWorkTimePercentage";
    public final String maxWorkTimePercentage = "maxWorkTimePercentage";
    public final String minNewTaskSpeed = "minNewTaskSpeed";
    public final String maxNewTaskSpeed = "maxNewTaskSpeed";
    public final String minAccessLevel = "minAccessLevel";
    public final String maxAccessLevel = "maxAccessLevel";
    

    public List<MachineConfiguration> loadCloudStucture(String filePath) throws IOException, SAXException, ParserConfigurationException, XmlException;

    public ModelSettings loadModelSettings(String filePath) throws IOException, SAXException, ParserConfigurationException, XmlException;
}
