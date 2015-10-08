<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>  
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<head>
<title>PM</title>

<s:url value="/resources/core/css/gpm.css" var="gpmCss" />
<s:url value="/resources/core/css/bootstrap.min.css" var="bootstrapCss" />
<s:url value="/resources/core/css/dataTables.bootstrap.css" var="dataTableBootstrapCss" />

<link href="${dataTableBootstrapCss}" rel="stylesheet" />
<link href="${bootstrapCss}" rel="stylesheet" />
<link href="${gpmCss}" rel="stylesheet" />

<s:url value="/resources/core/js/jquery-2.1.4.min.js" var="jqueryJs" />
<s:url value="/resources/core/js/bootstrap.min.js" var="bootstrapJs" />
<s:url value="/resources/core/js/bootstrap-hover-dropdown.min.js" var="bootstrapHoverJs" />
<s:url value="/resources/core/js/bootstrap-filestyle.min.js" var="bootstrapFileJs" />
<s:url value="/resources/core/js/gpm.js" var="gpmJs" />
<s:url value="/resources/core/js/jquery.dataTables.min.js" var="jqueryDatatableJs" />
<s:url value="/resources/core/js/dataTables.bootstrap.js" var="dataTablesBootstrapJs" />

<script src="${jqueryJs}"></script>
<script src="${bootstrapJs}"></script>
<script src="${bootstrapHoverJs}"></script>
<script src="${bootstrapFileJs}"></script>
<script src="${gpmJs}"></script>
<script src="${jqueryDatatableJs}"></script>
<script src="${dataTablesBootstrapJs}"></script>
<%-- <script src="${bootstrapModalJs}"></script> --%>


</head>

<header class="navbar navbar-fixed-top navbar-inverse">
   <div class="container">
     <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
       <span class="icon-bar"></span>
       <span class="icon-bar"></span>
       <span class="icon-bar"></span>
     </button>
     <a class="navbar-brand" href="/gpm">PM</a>
     <div class="navbar-collapse nav-collapse collapse navbar-header">
       <ul class="nav navbar-nav">
         <li class="dropdown">
           <a href="#" class="dropdown-toggle js-activated">Văn Bản<b class="caret"></b></a>
           <ul class="dropdown-menu">
             <li><a href="/gpm/document/list">Quản Lý</a></li>
             <li class="divider"></li>
             <li><a href="/gpm/document/import">Thêm</a></li>
           </ul>
         </li>
         <li class="dropdown">
           <a href="#" class="dropdown-toggle js-activated" data-toggle="dropdown">Tổng Hợp<b class="caret"></b></a>
           <ul class="dropdown-menu">
              <li><a href="#">Xuất</a></li>			  
           </ul>
         </li>
       
         <li class="dropdown">
           <a href="#" class="dropdown-toggle js-activated" data-toggle="dropdown">Tài Khoản <b class="caret"></b></a>
           <ul class="dropdown-menu">
             <li><a tabindex="-1" href="#">Tài Khoản</a></li>
             <li class="divider"></li>
             <li><a tabindex="-1" href="#">Đổi Mật Khẩu</a></li>
             <li class="divider"></li>
             <li><a tabindex="-1" href="#">Đăng Xuất</a></li>
           </ul>
         </li>
        
       </ul>
     </div> <!-- .nav-collapse -->
   </div> <!-- .container -->
 </header> <!-- .navbar -->
 
 
 	<!-- Popups -->
	
	<!-- Message -->
	<div class="modal fade" id="modalMessage" tabindex="-1" role="dialog" aria-labelledby="basicModal" aria-hidden="true">
	    <div class="modal-dialog">
	        <div class="modal-content">
	            <div class="modal-header">
	             <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	            
	            <h4 class="modal-title" id="myModalLabel">Warning</h4>
	            </div>
	            <div class="modal-body">
	                <p>Please select a employee</p>
	            </div>
	            <div class="modal-footer">
	                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>	                
	        </div>
	    </div>
	  </div>
	</div>