package real_time_scheduling_system.data_managment;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGEncodeParam;
import com.sun.image.codec.jpeg.JPEGImageEncoder;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYSplineRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.RectangleInsets;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * Creator: Valery Palamarchuk
 * Date: 19.02.13
 * Time: 12:30
 */
public class Charts implements IChart {
    public static void saveToFile(JFreeChart chart, String aFileName) throws IOException {
        BufferedImage img = draw(chart, SAVED_FILE_WEIGHT, SAVED_FILE_HEIGHT);
        FileOutputStream fos = new FileOutputStream(aFileName);
        JPEGImageEncoder encoder2 = JPEGCodec.createJPEGEncoder(fos);
        JPEGEncodeParam param2 = encoder2.getDefaultJPEGEncodeParam(img);
        param2.setQuality((float) SAVED_FILE_QUALITY, true);
        encoder2.encode(img, param2);
        fos.close();
    }

    protected static BufferedImage draw(JFreeChart chart, int width, int height) {
        BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2 = img.createGraphics();
        chart.draw(g2, new Rectangle2D.Double(0, 0, width, height));
        g2.dispose();
        return img;
    }

    @Override
    public void getSplineChart(DataMass mass, String chartName, String xName, String yName,String filePath) throws IOException {
        // create plot
        NumberAxis xAxis = new NumberAxis(xName);
        xAxis.setAutoRangeIncludesZero(false);
        NumberAxis yAxis = new NumberAxis(yName);
        yAxis.setAutoRangeIncludesZero(false);

        XYSplineRenderer renderer1 = new XYSplineRenderer();
        XYPlot plot = new XYPlot(createSplineData(mass, chartName), xAxis, yAxis, renderer1);
        plot.setBackgroundPaint(Color.lightGray);
        plot.setDomainGridlinePaint(Color.white);
        plot.setRangeGridlinePaint(Color.white);
        plot.setAxisOffset(new RectangleInsets(4, 4, 4, 4));

        JFreeChart chart = new JFreeChart(chartName, new Font(FONT, Font.BOLD, 12), plot, true);
        ChartUtilities.applyCurrentTheme(chart);

        saveToFile(chart, filePath);
    }

    @Override
    public void getDualBarChart(DataMass mass, String chartName, String xName, String yName,String filePath) throws IOException {
        DefaultCategoryDataset series = createBarData(mass, chartName, yName);
        JFreeChart chart = ChartFactory.createBarChart(chartName, xName, yName, series, PlotOrientation.VERTICAL, false, true, false);
        chart.setBackgroundPaint(Color.yellow);
        chart.getTitle().setPaint(Color.blue);
        CategoryPlot p = chart.getCategoryPlot();
        p.setRangeGridlinePaint(Color.red);

        chart.setAntiAlias(false);
        ChartUtilities.applyCurrentTheme(chart);

        saveToFile(chart, filePath);
    }

    private DefaultCategoryDataset createBarData(DataMass mass, String chartName, String yName) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (int i = 0; i < mass.getSize(); i++) {
            dataset.setValue(mass.getYmass()[i], yName, mass.getXmass()[i] + "");
        }
        return dataset;
    }

    private XYSeriesCollection createSplineData(DataMass mass, String chartName) {
        XYSeries series = new XYSeries(chartName);
        for (int i = 0; i < mass.getSize(); i++) {
            series.add(mass.getXmass()[i], mass.getYmass()[i]);
        }
        XYSeriesCollection result = new XYSeriesCollection(series);
        return result;
    }
}

