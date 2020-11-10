package in.nareshit.aashish.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.nareshit.aashish.exception.PartNotFoundException;
import in.nareshit.aashish.model.Part;
import in.nareshit.aashish.repo.PartRepository;
import in.nareshit.aashish.service.IPartService;

@Service
public class PartServiceImpl implements IPartService {

	@Autowired
	private PartRepository repo;
	
	@Override
	public Integer savePart(Part part) {
		return repo.save(part).getId();
	}

	@Override
	public List<Part> getAllParts() {
		return repo.findAll();
	}

	@Override
	public void deletePart(Integer id) {
		Part part = repo.findById(id).orElseThrow(
				()->new PartNotFoundException("Part '"+id+"' Not Exist")
				);	
		repo.delete(part);
	}

	@Override
	public Part getOnePart(Integer id) {
		Part part = repo.findById(id).orElseThrow(
				()->new PartNotFoundException("Part '"+id+"' Not Exist")
				);	
		return part;
	}

	@Override
	public void updatePart(Part part) {
		repo.save(part);
	}

}
