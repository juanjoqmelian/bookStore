<#assign form=JspTaglibs["/WEB-INF/views/spring-form.tld"] />

<html>
<head>
    <title>Book Store - Add new book</title>
</head>
<body>
<h1>BeShowcased signup page</h1>
<@form.form commandName="bookForm" method="post" action="create">
<span>Name*</span>
    <@form.input type="text" id="name" path="name"  size="20px" maxlength="50"/>
    <@form.errors path="name" cssClass="error"/>
<br/>
<span>Category </span>
    <@form.input type="category" id="category" path="category" size="20px" maxlength="10"/>
    <@form.errors path="category" cssClass="error"/>
<br/>
<span>Year</span>
    <@form.input type="year" id="year" path="year" size="20px" maxlength="10"/>
    <@form.errors path="year" cssClass="error"/>
<br/>
<br/>
<span>Price*</span>
    <@form.input type="price" id="price" path="price" size="20px" maxlength="10"/>
    <@form.errors path="price" cssClass="error"/>
<br/>
<br/>
<input type="submit" id="saveButton" value="Save" size="12px"/>
<input type="button" id="cancelButton" value="Cancel" size="12px"/>
</@form.form>
</body>
</html>