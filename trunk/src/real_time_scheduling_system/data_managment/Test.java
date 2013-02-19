package real_time_scheduling_system.data_managment;

import javafx.scene.chart.Chart;
import org.jfree.ui.RefineryUtilities;
import org.xml.sax.SAXException;
import real_time_scheduling_system.model.MachineConfiguration;

import javax.swing.*;
import javax.xml.parsers.ParserConfigurationException;
import java.awt.*;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * Creator: Valery Palamarchuk
 * Date: 17.02.13
 * Time: 19:39
 */
public class Test {
    public static void main(String[] args) throws SAXException, XmlException, ParserConfigurationException, IOException {
        String path = "MachineConf.settings.xml";
        ILoadSettings loadSettings = new LoadSettings();
        MachineConfiguration[] machineConfigurations = loadSettings.loadCloudStucture(path);
        for (MachineConfiguration machine : machineConfigurations) {
            System.out.println(machine.toString());
        }

        String path1 = "ModelConf.settings.xml";
        ModelSettings modelSettings = loadSettings.loadModelSettings(path1);
        System.out.println(modelSettings.toString());

        IChart c = new Charts();
        double[] massX = {0.1, 0.2, 0.3, 0.4, 0.5, 0.6, 0.7, 0.8, 0.9};
        double[] massY = {1, 2, 3, 2, 5, 6, 1, 8, 5};

        JFrame f = new JFrame();
        f.setLayout(new BorderLayout());
        f.add(c.getSplineChart(new DataMass(massX, massY)));
        f.setSize(new Dimension(900, 500));
        RefineryUtilities.centerFrameOnScreen(f);
        f.setVisible(true);

//        JFrame f1 = new JFrame();
//        f1.setLayout(new BorderLayout());
//        f1.add(c.getDualAxisChart(new DataMass(massX, massY)));
//        f1.setBounds(f.getX() + 100, f.getY() + 200, 900, 500);
//        f1.setVisible(true);
    }
}
