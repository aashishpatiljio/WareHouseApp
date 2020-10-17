package in.nareshit.aashish.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.nareshit.aashish.model.Document;
import in.nareshit.aashish.repo.DocumentRepository;
import in.nareshit.aashish.service.IDocumentService;

@Service
public class DocumentServiceImpl implements IDocumentService {

	@Autowired
	private DocumentRepository repo;
	
	/**
	 * 1.
	 */
	@Override
	public void saveDocument(Document document) {
		repo.save(document);
	}
	/**
	 * 2.
	 */
	@Override
	public List<Object[]> getDocumentIdAndNames() {
		return repo.getDocumentIdAndNames();
	}
	/**
	 * 3.
	 */
	@Override
	public Optional<Document> getOneDocument(Integer id) {
		return repo.findById(id);
	}
}
