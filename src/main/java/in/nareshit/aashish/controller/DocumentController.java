package in.nareshit.aashish.controller;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import in.nareshit.aashish.model.Document;
import in.nareshit.aashish.service.IDocumentService;

@Controller
@RequestMapping("/document")
public class DocumentController {

	@Autowired
	private IDocumentService service;

	/**
	 * 1. Show Documents upload page and
	 * 
	 * @return Document.html page
	 */
	@GetMapping("all")
	public String showDocs(Model model) {
		List<Object[]> list = service.getDocumentIdAndNames();
		model.addAttribute("list", list);
		return "Documents";
	}

	/**
	 * 
	 * @param fid
	 * @param fob
	 * @return
	 */
	@PostMapping("/save")
	public String saveDoc(@RequestParam Integer fid, @RequestParam MultipartFile fob) {

		try {
			// create model class object
			Document doc = new Document();
			doc.setDocId(fid);
			doc.setDocName(fob.getOriginalFilename());
			doc.setDocData(fob.getBytes());  //getBytes() throws IOException
			// call service layer method to save the data
			service.saveDocument(doc);
		} catch (IOException e) {
			e.printStackTrace();
		} // catch block ends

		return "redirect:all";
	}

	@GetMapping("/download")
	public void downloadDoc(@RequestParam Integer id, HttpServletResponse resp) {
		// a. Get data based on id
		Optional<Document> opt = service.getOneDocument(id);

		if (opt.isPresent()) {
			// b. read the object
			Document doc = opt.get();
			// c. add header param
			resp.addHeader("Content-Disposition", "attachment;filename=" + doc.getDocName());
			// d. copy the data (from-->to)
			try {
				FileCopyUtils.copy(doc.getDocData(), resp.getOutputStream());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
