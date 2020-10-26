package in.nareshit.aashish.util;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.springframework.stereotype.Component;

@Component
public class ShipmentTypeUtil {
	
	//1. generate Pie Chart
	public void generatePieChart(String path, List<Object[]> data) {
		//a. create dataset
		DefaultPieDataset dataset = new DefaultPieDataset();
		//add data to dataset
		//key[String], value[Double]
		for(Object[] ob:data) {
			dataset.setValue(String.valueOf(ob[0]), Double.valueOf(ob[1].toString()));			
		}
		//b. create JFreeChart object using ChartFactory class
		JFreeChart chart = ChartFactory.createPieChart("SHIPMENT TYPE MODE COUNT", dataset);
		
		//c. convert JFreeChart object into Image
		try {
			ChartUtils.saveChartAsJPEG(new File(path+"/shipmentA.jpg"), chart, 500, 300);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//2. generate Bar Chart
	public void generateBarChart(String path, List<Object[]> data) {
		//a. create dataset
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		//add data to dataset
		//value[Double],key[String],label[String],,,, label is optional
		for(Object[] ob:data) {
			dataset.setValue(Double.valueOf(ob[1].toString()), String.valueOf(ob[0]), "");
		}
		//b. create JFreeChart object using ChartFactory class
		JFreeChart chart = ChartFactory.createBarChart("SHIPMENT TYPE MODE COUNT", "TYPE", "COUNT", dataset);		
		
		//c. convert JFreeChart object into Image
			try {
				ChartUtils.saveChartAsJPEG(new File(path+"/shipmentB.jpg"), chart, 500, 500);
			} catch (IOException e) {
				e.printStackTrace();
			}
	}
}
