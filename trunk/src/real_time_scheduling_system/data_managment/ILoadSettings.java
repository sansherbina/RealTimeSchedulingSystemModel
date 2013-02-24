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
    public final String TASK_COUNT = "taskCount";

    public List<MachineConfiguration> loadCloudStucture(String filePath) throws IOException, SAXException, ParserConfigurationException, XmlException;

    public ModelSettings loadModelSettings(String filePath) throws IOException, SAXException, ParserConfigurationException, XmlException;
}
