package sdu.cs.document.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arjuna.ats.internal.jdbc.drivers.modifiers.list;

import sdu.cs.document.entity.Document;
import sdu.cs.document.repository.DocumentRepository;

/**
 * 
 * @author ljh_2015 CreatedDate 2017-07-20
 * 发帖服务
 *
 */
@Service
public class NoteService {

    @Autowired private DocumentRepository documentRepository;
    
    public List<Document> getList() {
	return documentRepository.findAll();
    }
}
