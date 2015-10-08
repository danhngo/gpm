package cs.gpm.service.document;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import cs.gpm.dao.document.DocumentDAO;
import cs.gpm.entity.document.DocumentEntity;
import cs.gpm.model.document.DocumentModel;

@Service
public class DocumentService {

	private static final Logger logger = LoggerFactory.getLogger(DocumentService.class);

	
	public List<DocumentModel> getDocumentList() {
		List<DocumentEntity> entities = DocumentDAO.getDocumentList();
		
		return toDocumentModel(entities);
	}
	
	public DocumentModel getDocument(String id) {
		DocumentEntity doc = DocumentDAO.getDocument(id);
		
		return toDocumentModel(doc);
	}
	
	public int deleteDocument(String id) {
		return DocumentDAO.deleteDocument(id);
	}
	
	public int updateDocument(DocumentModel model) {
		int result = 0;
		DocumentEntity entity = toDocumentEntity(model);
		if (entity != null) {
			DocumentDAO.updateDocument(entity);
		}
		return result;
	}

	private List<DocumentModel> toDocumentModel(List<DocumentEntity> lstEntity) {
		List<DocumentModel> lstModel = new ArrayList<DocumentModel>();
		
		for (DocumentEntity entity : lstEntity) {
			DocumentModel model = toDocumentModel(entity);
			if (model != null) {
				lstModel.add(model);
			}
		}
		
		return lstModel;
	}
	
	private DocumentModel toDocumentModel(DocumentEntity entity) {
		DocumentModel model = null;
		if (entity != null) {
			model =  new DocumentModel();
			
			model.setId(entity.getId());
			model.setName(entity.getName());
			model.setPath(entity.getPath());
			model.setCreatedBy(entity.getCreatedBy());
			model.setCreatedDate(entity.getCreatedDate());
			model.setType(entity.getType());
			model.setSignedDate(entity.getSignedDate());
			model.setSignedName(entity.getSignedName());
			model.setExecuteName(entity.getExecuteName());
			model.setCompletedDate(entity.getCompletedDate());
			
		}
		return model;
	}
	
	private DocumentEntity toDocumentEntity(DocumentModel model) {
		DocumentEntity entity = null;
		
		if (model != null) {
			entity =  new DocumentEntity();
			
			entity.setId(model.getId());
			entity.setName(model.getName());
			entity.setPath(model.getPath());
			entity.setCreatedBy(model.getCreatedBy());
			entity.setCreatedDate(model.getCreatedDate());
			entity.setType(model.getType());
			entity.setSignedDate(model.getSignedDate());
			entity.setSignedName(model.getSignedName());
			entity.setExecuteName(model.getExecuteName());
			entity.setCompletedDate(model.getCompletedDate());
			
		}
		return entity;
	}
	
	/*public int importEmployee(List<EmployeeModel> lstEmployee) {
		return EmployeeDAO.importEmployee(toEmployeeEntity(lstEmployee));
	}
	
	private List<EmployeeEntity> toEmployeeEntity(List<EmployeeModel> lstEmployee) {
		List<EmployeeEntity> lstEntity = new ArrayList<EmployeeEntity>();
		
		for (EmployeeModel model : lstEmployee) {
			EmployeeEntity entity = toEmployeeEntity(model);
			if (entity != null) {
				lstEntity.add(entity);
			}
		}
		
		return lstEntity;
	}*/

}