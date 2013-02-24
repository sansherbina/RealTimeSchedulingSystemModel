package real_time_scheduling_system.data_managment;

import org.jfree.chart.ChartPanel;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * Creator: Valery Palamarchuk
 * Date: 19.02.13
 * Time: 19:32
 */
public interface IChart {
    public final String FONT = "SansSerif";
    public final int SAVED_FILE_HEIGHT = 500;
    public final int SAVED_FILE_WEIGHT = 900;
    public final int SAVED_FILE_QUALITY = 100;
    public final String PATH_TO_SAVE_IMG = "e:\\";

    public ChartPanel getSplineChart(DataMass mass, String chartName, String xName, String yName) throws IOException;

    public ChartPanel getDualBarChart(DataMass mass, String chartName, String xName, String yName) throws IOException;
}
