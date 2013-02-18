package real_time_scheduling_system.data_managment;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * Creator: Valery Palamarchuk
 * Date: 17.02.13
 * Time: 19:39
 */
public class Test {
    // TODO Put results in streem
    public static void main(String[] args) throws SAXException, XmlException, ParserConfigurationException, IOException {
        String path = "MachineConf.settings.xml";
        MachineConfigurationCreator modelCreator = new MachineConfigurationCreator(path);
        System.out.println(modelCreator.toString());

        String path2 = "ModelConf.settings.xml";
        ModelSettingsCreator modelSettingsCreator = new ModelSettingsCreator(path2);
        System.out.println(modelSettingsCreator.toString());
    }
}
