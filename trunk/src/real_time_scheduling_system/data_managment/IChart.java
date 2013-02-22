package real_time_scheduling_system.data_managment;

import org.jfree.chart.ChartPanel;

/**
 * Created with IntelliJ IDEA.
 * Creator: Valery Palamarchuk
 * Date: 19.02.13
 * Time: 19:32
 */
public interface IChart {
    public final String NAME_1 = "Graph #100500";
    public final String X_HERE = "X";
    public final String Y_HERE = "Y";
    public final String CHART_NAME = "Chart of bla bla bla";
    public final String FONT = "SansSerif";

    public ChartPanel getSplineChart(DataMass mass);

    public ChartPanel getDualAxisChart(DataMass mass);
}
