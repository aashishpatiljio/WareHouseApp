package in.nareshit.aashish.util;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
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
		// c. convert JfreeChart object as an image.
		try {
			ChartUtils.saveChartAsJPEG(new File(path+"/uomA.jpg"), chart, 500, 400);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 2. create Bar Chart
	public void generateBarChart(String path, List<Object[]> data) {
		// a. create DataSet for Bar-Chart and add data to it.
		// b. create JfreeChart object using datasets and other details.
		// c. convert JfreeChart object as an image.

	}
}
