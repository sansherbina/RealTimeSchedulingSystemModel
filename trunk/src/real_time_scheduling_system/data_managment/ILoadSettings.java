package real_time_scheduling_system.data_managment;


import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import real_time_scheduling_system.model.MachineConfiguration;

public interface ILoadSettings {
	
	public MachineConfiguration[] loadCloudStucture(String filePath) throws IOException, SAXException, ParserConfigurationException, XmlException; 
	public ModelSettings loadModelSettings(String filePath) throws IOException, SAXException, ParserConfigurationException, XmlException; 
}
