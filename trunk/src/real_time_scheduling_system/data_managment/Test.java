package real_time_scheduling_system.data_managment;

import org.xml.sax.SAXException;

import real_time_scheduling_system.model.MachineConfiguration;

import javax.xml.parsers.ParserConfigurationException;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * Creator: Valery Palamarchuk
 * Date: 17.02.13
 * Time: 19:39
 */
public class Test {
    // TODO write class to create machineSettings
    // TODO Put results in streem
    public static void main(String[] args) throws SAXException, XmlException, ParserConfigurationException, IOException {
    	String path = "MachineConf.settings.xml";
    	ILoadSettings loadSettings=new LoadSettings();
    	MachineConfiguration[] machineConfigurations=loadSettings.loadCloudStucture(path);
    	for(MachineConfiguration machine:machineConfigurations){
    		System.out.println(machine.toString());
    	}
    }
}
