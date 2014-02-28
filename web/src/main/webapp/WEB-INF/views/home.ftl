<html>
<head>
    <title>Book Store Home</title>
</head>
<body>
<h1>Book Store</h1>

<form id="search" action="/search">
    <input type="text" id="searchText" name="searchText" placeholder="Input text..."/>
    <input type="button" id="searchButton" value="Search"/>
</form>
<div>
    <div id="books">
        <div id="head">
            <span>
                <span>Name</span>
                <span>Category</span>
                <span>Year</span>
                <span>Price</span>
                <span>Edit</span>
            </span>
        </div>
        <div>
            <ul>
            <#list books as book>

                <li id="${book.id}">
                    <span>${book.name!''}</span>
                    <span>${book.category!''}</span>
                    <span>${book.year!''}</span>
                    <span>${book.price!"0.0"}</span>
                    <span><a class="link" href="book/showEdit/${book.id}/">Edit</a></span>
                </li>
            </#list>
            </ul>
        </div>
    </div>
    <form id="add" action="book/showAdd">
        <input type="submit" id="addButton" value="Add"/>
    </form>
</div>
</body>
</html>