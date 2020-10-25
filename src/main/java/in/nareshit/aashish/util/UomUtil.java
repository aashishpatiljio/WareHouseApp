package in.nareshit.aashish.util;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.List;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.PieSectionLabelGenerator;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.springframework.stereotype.Component;

@Component
public class UomUtil {

	// 1. create Pie Chart
	public void generatePieChart(String path, List<Object[]> data) {
		
		// a. create DataSet for Pie-Chart and add data to it.
		DefaultPieDataset dataset = new DefaultPieDataset();

		for (Object[] ob : data) {
			// setValue(key[String], val[double])
			dataset.setValue(ob[0].toString(), Double.valueOf(ob[1].toString()));
		}
		// b. create JfreeChart object using datasets and other details.
		JFreeChart chart = ChartFactory.createPieChart3D("UOM TYPE AND COUNT", dataset);
		
		// read chart area object
		PiePlot plot = (PiePlot)chart.getPlot();
		//{0} indicates label,{1} indicates value, {2} indicates percentage
		PieSectionLabelGenerator gen = new StandardPieSectionLabelGenerator("{0} : {1} ({2})",new DecimalFormat("0"),new DecimalFormat("0%"));
		plot.setLabelGenerator(gen); 
		
		// c. convert JfreeChart object as an image.
		try {
			ChartUtils.saveChartAsJPEG(new File(path+"/uomA.jpg"), chart, 500, 300);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 2. create Bar Chart
	public void generateBarChart(String path, List<Object[]> data) {
		// a. create DataSet for Bar-Chart and add data to it.
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		
		for(Object[] ob:data) {
			//val,ket,label to display
			dataset.setValue(
					Double.valueOf(ob[1].toString()), //value
					String.valueOf(ob[0]), //key
					"");  //label
		} //foreach loop ends
		
		// b. create JfreeChart object using datasets and other details.
		//(title, x-axis label, y-axis label, dataset)
		JFreeChart chart = ChartFactory.createBarChart("UOM TYPE COUNT", "UOM TYPE", "COUNT", dataset);
		// c. convert JfreeChart object as an image.
		try {
			ChartUtils.saveChartAsJPEG(new File(path+"/uomB.jpg"), chart, 500, 500);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
