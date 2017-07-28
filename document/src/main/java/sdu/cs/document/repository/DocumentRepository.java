package sdu.cs.document.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sdu.cs.document.entity.Document;

public interface DocumentRepository extends JpaRepository<Document, Long>{

}
