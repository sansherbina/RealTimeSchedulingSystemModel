package real_time_scheduling_system.data_managment;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.labels.StandardCategoryToolTipGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.CombinedDomainCategoryPlot;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.CategoryItemRenderer;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.chart.renderer.xy.XYSplineRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.RectangleInsets;

import java.awt.*;

/**
 * Created with IntelliJ IDEA.
 * Creator: Valery Palamarchuk
 * Date: 19.02.13
 * Time: 12:30
 */
public class Charts implements IChart {
    @Override
    public ChartPanel getSplineChart(DataMass mass) {
        // create plot
        NumberAxis xAxis = new NumberAxis(X_HERE);
        xAxis.setAutoRangeIncludesZero(false);
        NumberAxis yAxis = new NumberAxis(Y_HERE);
        yAxis.setAutoRangeIncludesZero(false);

        XYSplineRenderer renderer1 = new XYSplineRenderer();
        XYPlot plot = new XYPlot(createSplineData(mass), xAxis, yAxis, renderer1);
        plot.setBackgroundPaint(Color.lightGray);
        plot.setDomainGridlinePaint(Color.white);
        plot.setRangeGridlinePaint(Color.white);
        plot.setAxisOffset(new RectangleInsets(4, 4, 4, 4));

        // create and return the chart panel
        JFreeChart chart = new JFreeChart(CHART_NAME, new Font(FONT, Font.BOLD, 12), plot, true);
        //addChart(chart);
        ChartUtilities.applyCurrentTheme(chart);
        ChartPanel chartPanel = new ChartPanel(chart);
        return chartPanel;
    }

    @Override
    public ChartPanel getDualAxisChart(DataMass mass) {
        final NumberAxis rangeAxis1 = new NumberAxis("Run");
        rangeAxis1.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        final BarRenderer renderer1 = new BarRenderer();
        renderer1.setSeriesPaint(0, Color.red);
        renderer1.setBaseToolTipGenerator(new StandardCategoryToolTipGenerator());
        final CategoryPlot subplot1 = new CategoryPlot(createAxisData(mass), null, rangeAxis1, renderer1);
        subplot1.setDomainGridlinesVisible(true);

        final ValueAxis axis2 = new NumberAxis("Run Rate");
        subplot1.setRangeAxis(1, axis2);
        subplot1.setDataset(1, createAxisData(mass));
        subplot1.mapDatasetToRangeAxis(1, 1);
        final CategoryItemRenderer runrateRenderer1 = new LineAndShapeRenderer();
        runrateRenderer1.setSeriesPaint(0, Color.red);

        subplot1.setForegroundAlpha(0.7f);
        subplot1.setRenderer(0, renderer1);
        subplot1.setRenderer(1, runrateRenderer1);

        final CategoryAxis domainAxis = new CategoryAxis("Over");
        final CombinedDomainCategoryPlot plot =
                new CombinedDomainCategoryPlot(domainAxis);

        plot.add(subplot1, 1);

        final JFreeChart chart = new JFreeChart(
                "Score Bord", new Font("SansSerif", Font.BOLD, 12),
                plot, true);
        ChartUtilities.applyCurrentTheme(chart);
        ChartPanel chartPanel = new ChartPanel(chart);
        return chartPanel;
    }

    private CategoryDataset createAxisData(DataMass mass) {
        final DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        double[] run = mass.getXmass();
        float num = 0;

        for (int i = 0; i < run.length; i++) {
            num += run[i];
            dataset.addValue(num / (i + 1), "Xmass " + (i + 1) + " = " + mass.getXmass()[i], "Period " + (i + 1));
        }
        return dataset;
    }

    private XYSeriesCollection createSplineData(DataMass mass) {
        XYSeries series = new XYSeries(NAME_1);
        for (int i = 0; i < mass.getSize(); i++) {
            series.add(mass.getXmass()[i], mass.getYmass()[i]);
        }
        XYSeriesCollection result = new XYSeriesCollection(series);
        //XYSeries series2 = new XYSeries("Series 2");
        //result.addSeries(series2);
        return result;
    }
}

