<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>  
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<html>
<head>
<link href="<c:url value="/resources/css/bootstrap.min.css"/>" rel="stylesheet">
<title>도서 등록</title>
</head>
<body>
    <nav class="navbar navbar-expand  navbar-dark bg-dark">
        <div class="container">
            <div class="navbar-header">
                <a class="navbar-brand" href="./home">Home</a>
            </div>
        </div>
    </nav>
    <div class="jumbotron">
        <div class="container">
            <h1 class="display-3">
            	<spring:message code="addBook.form.title.label" /> 
            </h1>
        </div>
    </div>

    <div class="container">
      <div class="float-right">
        <form:form action="${pageContext.request.contextPath}/logout" method="POST"> 
            <input type="submit" class="btn btn-sm btn-success" value="Logout" />
        </form:form>  
    </div>
    <div class="float-right" style="padding-right:30px">  
            <a href="?language=ko" >Korean</a>|<a href="?language=en">English</a>
    </div>  
        
    <br><br>
       <form:form modelAttribute = "NewBook" 
       action="./add?${_csrf.parameterName}=${_csrf.token}"
       class="form-horizontal" 
       enctype="multipart/form-data">
        <fieldset>
        <legend><spring:message code="addBook.form.subtitle.label" /></legend>
        <div class="form-group row">
            <label class="col-sm-2 control-label">
            	<spring:message code="addBook.form.bookId.label" />
            </label>
            <div class="col-sm-3">
                <form:input  path="bookId"  class="form-control"/>  
            </div>
            <div class="col-sm-6">
                <form:errors path="bookId" cssClass="text-danger"/>  
            </div>
            
        </div>
        <div class="form-group row">
            <label class="col-sm-2 control-label" >
            	<spring:message code="addBook.form.name.label" />
            </label>
            <div class="col-sm-3">
                <form:input  path="name"  class="form-control"/>  
            </div>
              <div class="col-sm-6">
                <form:errors path="name" cssClass="text-danger"/>  
            </div>
        
        </div>
        <div class="form-group row">
           <label class="col-sm-2 control-label" >
           		<spring:message code="addBook.form.unitPrice.label" /> 
           </label>
           <div class="col-sm-3">
               <form:input  path="unitPrice" class="form-control"/>  
           </div>
            <div class="col-sm-6">
                <form:errors path="unitPrice" cssClass="text-danger"/>  
            </div>
        </div>
        <div class="form-group row">
           <label class="col-sm-2 control-label" >
				<spring:message code="addBook.form.author.label" />
			</label>
           <div class="col-sm-3">
               <form:input  path="author" class="form-control"/>  
           </div>
        </div>
        <div class="form-group row">
            <label class="col-sm-2 control-label" >
            	<spring:message code="addBook.form.description.label" />
            </label>
            <div class="col-sm-5">    
                <form:textarea  path="description" cols="50" rows="2" class="form-control"/>  
            </div>
        </div>
        <div class="form-group row">
            <label class="col-sm-2 control-label" >
            	<spring:message code="addBook.form.publisher.label" />
            </label>
            <div class="col-sm-3">    
                <form:input  path="publisher" class="form-control"/>  
            </div>
        </div>
        <div class="form-group row">
            <label class="col-sm-2 control-label" >
            	<spring:message code="addBook.form.category.label" />
            </label>
            <div class="col-sm-3">     
                <form:input path="category" class="form-control"/>  
            </div>
        </div>
        <div class="form-group row">
            <label class="col-sm-2 control-label" >
            	<spring:message code="addBook.form.unitsInStock.label" />
            </label>
            <div class="col-sm-3">    
                <form:input path="unitsInStock" class="form-control"/>  
            </div>
            <div class="col-sm-6">
                <form:errors path="unitsInStock" cssClass="text-danger"/>
            </div>
        </div>
        <div class="form-group row">
            <label class="col-sm-2 control-label" >
            	<spring:message code="addBook.form.releaseDate.label" /> 
            </label>
            <div class="col-sm-3">
                <form:input path="releaseDate" class="form-control"/>  
            </div>
        </div>
        <div class="form-group row">
            <label class="col-sm-2 control-label" >
            	<spring:message code="addBook.form.condition.label" />
            </label>
            <div class="col-sm-3">    
                <form:radiobutton path="condition" value="New" /> New  
                <form:radiobutton path="condition" value="Old" /> Old 
                <form:radiobutton path="condition" value="E-Book" />E-Book  
            </div>
        </div>
        
        <div class="form-group row"> 
        	<label class="col-sm-2 control-label" >
        		<spring:message code="addBook.form.bookImage.label" /> 
        	</label>
        	<div class="col-sm-7">
            	<form:input path="bookImage" type="file" class="form-control" />
        	</div>
     	</div> 
     
        <div class="form-group row">
            <div class="col-sm-offset-2 col-sm-10" >
            <input type="submit" class="btn btn-primary" value ="<spring:message code="addBook.form.button.label" /> "/>
            </div>
        </div>
        </fieldset>
        </form:form>  
        <hr>
    <footer>
        <p>&copy; BookMarket</p>
    </footer>
    </div>
</body>
</html>