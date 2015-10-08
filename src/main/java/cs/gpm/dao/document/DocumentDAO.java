package cs.gpm.dao.document;

import java.util.List;

import org.hibernate.Session;

import cs.gpm.HibernateUtil;
import cs.gpm.entity.document.DocumentEntity;

public class DocumentDAO {
	
	public static List<DocumentEntity> getDocumentList() {
		 Session session = HibernateUtil.getSessionFactory().openSession();
		 session.beginTransaction();
		
		 @SuppressWarnings("unchecked")
		 List<DocumentEntity> docList =  session.createCriteria(DocumentEntity.class).list();
		
		 session.getTransaction().commit();
		 session.close();
		 
		 return docList;
	}
	
	public static DocumentEntity getDocument(String id) {
		 Session session = HibernateUtil.getSessionFactory().openSession();
		 session.beginTransaction();
		
		 DocumentEntity tempDoc =   (DocumentEntity)session.get(DocumentEntity.class, id);
		 
		 session.close();
		 
		 return tempDoc;
	}
	
	public static int deleteDocument(String id) {
		 Session session = HibernateUtil.getSessionFactory().openSession();
		 session.beginTransaction();
		
		 DocumentEntity doc = (DocumentEntity) session.get(DocumentEntity.class, id);
		 session.delete(doc);
		
		 session.getTransaction().commit();
		 session.close();
		 
		 return 1;
	}
	
	
	public static int updateDocument(DocumentEntity entity) {
		 Session session = HibernateUtil.getSessionFactory().openSession();
		 session.beginTransaction();
		
		 session.saveOrUpdate(entity);
		 
		 session.getTransaction().commit();
		 session.close();
		 
		 return 1;
	}
	
	/*public static int importEmployee(List<EmployeeEntity> lstEmp) {
	 Session session = HibernateUtil.getSessionFactory().openSession();
	 session.beginTransaction();
	 
	 for (EmployeeEntity item : lstEmp) {
		 Object tempEmp =   session.get(EmployeeEntity.class, item.getId());
		 if (tempEmp == null) {
			 session.save(item);
		 }
	 }
	 
	 session.getTransaction().commit();
	 session.close();
	
	return 0;
}*/
	
	
	
}
