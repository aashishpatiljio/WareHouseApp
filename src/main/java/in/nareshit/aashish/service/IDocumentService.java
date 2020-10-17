package in.nareshit.aashish.service;

import java.util.List;
import java.util.Optional;

import in.nareshit.aashish.model.Document;

public interface IDocumentService {
	
	void saveDocument(Document document);
	List<Object[]> getDocumentIdAndNames();
	Optional<Document> getOneDocument(Integer id);
}
