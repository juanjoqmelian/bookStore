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
        <div id="row">
            <span>Effective Java</span>
            <span>Java</span>
            <span>2000</span>
            <span>20.90$</span>
            <span><a class="link" href="book/showEdit/11111/">Edit</a></span>
        </div>
    </table>
    <form id="add" action="book/showAdd">
        <input type="submit" id="addButton" value="Add"/>
    </form>
</div>
</body>
</html>