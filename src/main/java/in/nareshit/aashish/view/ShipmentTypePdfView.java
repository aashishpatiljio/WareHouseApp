package in.nareshit.aashish.view;

import java.awt.Color;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import in.nareshit.aashish.model.ShipmentType;

public class ShipmentTypePdfView extends AbstractPdfView {

	@Override
	protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		//set file name
		response.addHeader("Content-Disposition", "attachment;filename=SHIPMENTTYPE.pdf");

		//read data from Controller using Model
		@SuppressWarnings("unchecked")
		List<ShipmentType> list = (List<ShipmentType>) model.get("list"); //down-cast

		Font titleFont = new Font(Font.HELVETICA, 22, Font.BOLD, Color.BLUE);	
		Paragraph title = new Paragraph("SHIPMENT TYPES", titleFont); //to set title of doc
		title.setAlignment(Element.ALIGN_CENTER);
		Paragraph date = new Paragraph(new Date().toString()); //to set doc gen date

		PdfPTable table = new PdfPTable(6);
		table.addCell("ID");
		table.addCell("MODE");
		table.addCell("CODE");
		table.addCell("ENABLE SHIPMENT");
		table.addCell("GRADE");
		table.addCell("DESCRIPTION");

		for(ShipmentType st:list) {
			table.addCell(String.valueOf(st.getId()));
			table.addCell(st.getShipmentMode());
			table.addCell(st.getShipmentCode());
			table.addCell(st.getEnableShipment());
			table.addCell(st.getShipmentGrade());
			table.addCell(st.getDescription());			
		}

		//add the elements to the document
		document.add(title);
		document.add(date);	
		document.add(table);

	}

}