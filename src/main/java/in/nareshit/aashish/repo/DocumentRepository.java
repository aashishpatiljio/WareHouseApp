package in.nareshit.aashish.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import in.nareshit.aashish.model.Document;

public interface DocumentRepository extends JpaRepository<Document, Integer> {

}
