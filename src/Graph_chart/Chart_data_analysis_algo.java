package Graph_chart;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.WindowEvent;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.StringTokenizer;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.labels.ItemLabelPosition;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.CategoryItemRenderer;
import org.jfree.chart.renderer.category.StackedBarRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RectangleEdge;
import org.jfree.ui.RefineryUtilities;

public class Chart_data_analysis_algo extends ApplicationFrame {

    private List<dataStructure> x_axis;
    private final String window_title;


    public Chart_data_analysis_algo(String title) {
        super(title);
        this.window_title = title;
    }

    public void setChart(List<dataStructure> x_axis) {
        this.x_axis = x_axis;
        Chart_data_analysis_algo demo = this;
        demo.pack();
        RefineryUtilities.centerFrameOnScreen(demo);
        demo.setVisible(true);

        CategoryDataset dataset = createDataset();
        JFreeChart chart = createChart(dataset);
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(500, 250));
        setContentPane(chartPanel);

        setSize(500, 500);
        setLocationRelativeTo(null);
    }


    private CategoryDataset createDataset() {
        String series1 = "First";
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        x_axis.forEach((entry) -> {  
            String key = entry.getTitle();
            int value = entry.getValue();
            dataset.addValue(value, series1, key);
        });
        return dataset;
    }

    private JFreeChart createChart(CategoryDataset dataset) {
        StackedBarRenderer renderer = new StackedBarRenderer();
        renderer.setItemLabelsVisible(true);
        JFreeChart chart = ChartFactory.createStackedBarChart("", "", "", dataset, PlotOrientation.VERTICAL, false, false, false);
        chart.setBackgroundPaint(Color.white);
        CategoryPlot plot = chart.getCategoryPlot();
        CategoryAxis axis = plot.getDomainAxis();
        axis.setLowerMargin(0.01);
        axis.setCategoryMargin(0);
        
        if (x_axis.size() < 5)
            axis.setUpperMargin(0.6);
        else
            axis.setUpperMargin(0.01);
        
        axis.setCategoryLabelPositions(CategoryLabelPositions.UP_45);
        axis.setCategoryLabelPositionOffset(1);
        TextTitle eventTitle = new TextTitle(window_title, new Font("Arial", Font.BOLD, 13));
        eventTitle.setPosition(RectangleEdge.TOP);
        chart.setTitle(eventTitle);
        return chart;
    }
    
    @Override
    public void windowClosing(WindowEvent e){
      this.dispose();
    }
}