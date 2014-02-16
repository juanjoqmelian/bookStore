<#assign form=JspTaglibs["/WEB-INF/views/spring-form.tld"] />

<html>
<head>
    <title>Success Page</title>
</head>
<body>
<h1>Book Store</h1>

<div>
    A new book has been created successfully!
    <@form.form  method="post" action="/bookstore">
        <input type="submit" id="continueButton" value="Continue"/>
    </@form.form>
</div>
</body>
</html>