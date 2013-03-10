package real_time_scheduling_system.data_managment;



import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * Creator: Valery Palamarchuk
 * Date: 19.02.13
 * Time: 19:32
 */
public interface IChart {
	public static final int SPLINE_CHART_TYPE=1;
	public static final int DUAL_BAR_CHART_TYPE=2;
    public final String FONT = "SansSerif";
    public final int SAVED_FILE_HEIGHT = 500;
    public final int SAVED_FILE_WEIGHT = 900;
    public final int SAVED_FILE_QUALITY = 100;

    public void getSplineChart(DataMass mass, String chartName, String xName, String yName, String filePath) throws IOException;

    public void getDualBarChart(DataMass mass, String chartName, String xName, String yName, String filePath) throws IOException;
}
