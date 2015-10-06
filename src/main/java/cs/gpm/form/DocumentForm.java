package cs.gpm.form;

import java.util.Date;

public class DocumentForm {
	
	private String id;
	private String name;
	private String path;
	
	private String type;
	private Date signedDate;
	private String signedName;
	private String executeName;
	private Date completedDate;
	
	private String createdBy;
	private Date createdDate;
	private Date updatedDate;
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}

	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}

	public Date getSignedDate() {
		return signedDate;
	}
	public void setSignedDate(Date signedDate) {
		this.signedDate = signedDate;
	}


	public String getSignedName() {
		return signedName;
	}
	public void setSignedName(String signedName) {
		this.signedName = signedName;
	}

	public String getExecuteName() {
		return executeName;
	}



	public void setExecuteName(String executeName) {
		this.executeName = executeName;
	}



	public Date getCompletedDate() {
		return completedDate;
	}



	public void setCompletedDate(Date completedDate) {
		this.completedDate = completedDate;
	}



	public Date getCreatedDate() {
		return createdDate;
	}



	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}



	public Date getUpdatedDate() {
		return updatedDate;
	}



	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}



	public String getCreatedBy() {
		return createdBy;
	}



	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	
}
