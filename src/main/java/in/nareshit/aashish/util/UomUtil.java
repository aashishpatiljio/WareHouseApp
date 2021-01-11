package in.nareshit.aashish.util;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.List;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.CategorySeriesLabelGenerator;
import org.jfree.chart.labels.PieSectionLabelGenerator;
import org.jfree.chart.labels.StandardCategorySeriesLabelGenerator;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.springframework.stereotype.Component;

import in.nareshit.aashish.model.Uom;

@Component
public class UomUtil {

	// 1. create Pie Chart
	/**
	 * This we have taken to generate pie chart
	 * @param path path from the controller
	 * @param data reads the data in the form of List<Object[]> from
	 * the controller.
	 */
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
	/**
	 * This we have taken to generate Bar Chart
	 * @param path path from the controller
	 * @param data reads the data in the form of List<Object[]> from
	 * the controller.
	 */
	public void generateBarChart(String path, List<Object[]> data) {
		// a. create DataSet for Bar-Chart and add data to it.
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		
		for(Object[] ob:data) {
			//val,key,label to display
			dataset.setValue(
					Double.valueOf(ob[1].toString()), //value
					String.valueOf(ob[0]), //key
					String.valueOf(ob[0]));  //label
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
	
	/**
	 * This method will copy the data received from 
	 * client i.e @param uom to the object which is to be
	 * saved into the database i.e. @param dbUom
	 */
	public void copyNonNullValues(Uom dbUom, Uom uom) {
		if(uom.getUomType()!=null)
			dbUom.setUomType(uom.getUomType());
		if(uom.getUomModel()!=null)
			dbUom.setUomModel(uom.getUomModel());
		if(uom.getDescription()!=null)
			dbUom.setDescription(uom.getDescription());
	}
	
	
	
}
