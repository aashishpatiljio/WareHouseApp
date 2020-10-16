package in.nareshit.aashish.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.nareshit.aashish.model.Document;
import in.nareshit.aashish.repo.DocumentRepository;
import in.nareshit.aashish.service.IDocumentService;

@Service
public class DocumentServiceImpl implements IDocumentService {

	@Autowired
	private DocumentRepository repo;
	
	@Override
	public void saveDocument(Document document) {
		repo.save(document);
	}

}
