package in.nareshit.aashish.view;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.servlet.view.document.AbstractXlsxView;

import in.nareshit.aashish.model.ShipmentType;

public class ShipmentTypeExcelView extends AbstractXlsxView {

	@Override
	protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, 
			HttpServletRequest request,	HttpServletResponse response) throws Exception {

		// to provide file name which will be downloaded
		response.addHeader("Content-Disposition", "attachment;filename=shipmenttype.xlsx");

		// read data from the Model memory using method get("")
		@SuppressWarnings("unchecked")
		List<ShipmentType> list = (List<ShipmentType>) model.get("list"); // down-cast

		// create sheet
		Sheet sheet = workbook.createSheet("SHIPMENTTYPE");

		setHeader(sheet);
		setBody(sheet, list);
	}

	private void setHeader(Sheet sheet) {
		// create the row 0 by using Sheet
		Row row = sheet.createRow(0);
		/*
		 * for the above row we are creating the cells and providing the values.
		 */
		row.createCell(0).setCellValue("ID");
		row.createCell(1).setCellValue("MODE");
		row.createCell(2).setCellValue("CODE");
		row.createCell(3).setCellValue("ENABLE SHIPMENT");
		row.createCell(4).setCellValue("GRADE");
		row.createCell(5).setCellValue("DESCRIPTION");

	}

	private void setBody(Sheet sheet, List<ShipmentType> list) {
		//create the rows by using Sheet
		int rowNum = 1;

		for(ShipmentType st:list) {

			Row row = sheet.createRow(rowNum++);
			
			row.createCell(0).setCellValue(st.getId());
			row.createCell(1).setCellValue(st.getShipmentMode());
			row.createCell(2).setCellValue(st.getShipmentCode());
			row.createCell(3).setCellValue(st.getEnableShipment());
			row.createCell(4).setCellValue(st.getShipmentGrade());
			row.createCell(5).setCellValue(st.getDescription());
			
		}

	}

}
