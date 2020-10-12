package in.nareshit.aashish.view;

import java.awt.Color;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import in.nareshit.aashish.model.Uom;

public class UomPdfView extends AbstractPdfView {

	@Override
	protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		//this is for to specify the downloading file name and to allow the
		//file to be download in the system
		response.addHeader("content-disposition", "attachment;filename=uom.pdf");
		
		//read data from model
		List<Uom> list = (List<Uom>)model.get("list"); //down-cast
		
		//create the element
		Font font = new Font(Font.TIMES_ROMAN, 22, Font.BOLD, Color.BLUE);
		Paragraph p = new Paragraph("HELLO USER", font);
		p.setSpacingAfter(5.5f);
		//add element to document
		document.add(p);
		
		//create table(element) in which one row should contains  - n columns
		PdfPTable table = new PdfPTable(4);
		table.addCell("ID");
		table.addCell("TYPE");
		table.addCell("MODEL");
		table.addCell("DESCRIPTION");
		
		for(Uom uom:list) {
			//table.addCell(uom.getId().toString());//converting int to String
			table.addCell(String.valueOf(uom.getId())); //good approach
			table.addCell(uom.getUomType());
			table.addCell(uom.getUomModel());
			table.addCell(uom.getDescription());
		}
		
		//add element(table) to the document
		document.add(table); 
		
	}

}
