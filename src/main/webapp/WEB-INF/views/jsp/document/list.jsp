<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Import Document</title>

</head>
<%@ include file="/WEB-INF/views/jsp/partials/header.jsp" %>

<body>
<div class="container">
	
	<div id="paddingTop100">
	
		<table id="tbdoclist" class="table table-striped table-bordered" cellspacing="0" width="100%">
	        <thead>
	            <tr>
	                <th>Mã</th>
	                <th>Tên</th>
	                <th>Người Tạo</th>
	                <th>Ngày Tạo</th>
	                <th>Loại</th>
	                <th>Người Ký</th>
	                <th>Ngày Ký</th>
	                <th>Thực Hiện</th>
	                <th>Ngày Hoàn Thành</th>
	                <th>Ngày Thay Đổi</th>
	            </tr>
	        </thead>
	 
	        <tbody>
	             <c:forEach var="document" items="${documentList}" >
		              <tr>
		                <td>${document.id}</td>
		                <td>${document.name}</td>
		                <td>${document.createdBy}</td>
		                <td>${document.createdDate}</td>
		                <td>${document.type}</td>
		                <td>${document.signedName}</td>
		                <td>${document.signedDate}</td>
		                
		                <td>${document.executeName}</td>
		                <td>${document.completedDate}</td>
		                <td>${document.updatedDate}</td>
		             </tr>
	            </c:forEach>
	           
           </tbody>
          </table>
	</div>
	
		<div style="margin-top:10px" class="form-group">
	         <!-- Button -->
	        <div class="col-sm-12 controls">
	           	  <a id="btnAddDoc" class="btn btn btn-primary">Add</a>
	           	  <a id="btnModifyDoc" class="btn btn btn-primary">Modify</a>
	           	  <a id="btnDeleteDoc" class="btn btn btn-primary">Delete</a>
	           	  |	
	           	  <a id="btnexportcontract" class="btn btn btn-primary">Export Contract</a>  
	          </div>	          
	     </div>
		
	</div>
	<hr>
	
	<!-- Delete Confirmation -->
	<div class="modal fade" id="modalConfirmDelete" tabindex="-1" role="dialog" aria-labelledby="basicModal" aria-hidden="true">
	    <div class="modal-dialog">
	        <div class="modal-content">
	            <div class="modal-header">
	             <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	            
	            <h4 class="modal-title" id="myModalLabel">Confirmation</h4>
	            </div>
	            <div class="modal-body">
	                <p>Are you sure to delete the selected document?</p>
	            </div>
	            <div class="modal-footer">
	                <button type="button" class="btn btn-default" data-dismiss="modal">No</button>
	                <button type="button" class="btn btn-primary" id="btnConfirmDelete">Yes</button>
	        </div>
	    </div>
	  </div>
	</div>
	
	<!-- Add Document -->
	<div class="modal fade" id="modalAddDocument" tabindex="-1" role="dialog" aria-labelledby="basicModal" aria-hidden="true">
	    <div class="modal-dialog">
	        <div class="modal-content">
	            <div class="modal-header">
	             <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	            
	            <h4 class="modal-title" id="myModalLabel">Modify Document</h4>
	            </div>
	            <div class="modal-body" >
	                 <sf:form id="formAddDocument" class="form-horizontal" role="form" action="/gpm/document/update" commandName="documentForm" method="POST">
	                      <div class="row">
				            <div class="col-md-4"><label for="recipient-name" class="control-label">Mã</label><sf:input type="text" class="form-control" id="textEmpId" path="id"/></div>
				            <div class="col-md-4"><label for="recipient-name" class="control-label">Tên</label><sf:input type="text" class="form-control" id="textName" path="name"/></div>
				          </div>
				          <div class="row">
				            <div class="col-md-4"><label for="message-text" class="control-label">Startdate:</label><sf:input type="text" class="form-control" id="textStartdate" path="name"/></div>
				          </div>
			        </sf:form>
	                
	                <th>Mã</th>
	                <th>Tên</th>
	                <th>Người Tạo</th>
	                <th>Ngày Tạo</th>
	                <th>Loại</th>
	                <th>Người Ký</th>
	                <th>Ngày Ký</th>
	                <th>Thực Hiện</th>
	                <th>Ngày Hoàn Thành</th>
	                <th>Ngày Thay Đổi</th>
	                
	            </div>
	            <div class="modal-footer">
	                <button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
	                <button type="button" class="btn btn-primary" id="btnSaveChanges">Save Changes</button>
	        	</div>
	    	</div>
	  </div>
	</div>
	
	<%@ include file="/WEB-INF/views/jsp/partials/footer.jsp" %>
	
</div>

<script type="text/javascript">
	$(document).ready(function() {
	    $('#tbemplist').dataTable();
	} );
</script>	

</body>
</html>