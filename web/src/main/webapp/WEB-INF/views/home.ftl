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
    <table id="books">
        <thead>
            <tr>
                <td>Name</td>
                <td>Category</td>
                <td>Year</td>
                <td>Price</td>
                <td>Edit</td>
            </tr>
        </thead>
        <tr>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
            <td></td>
        </tr>
    </table>
    <form id="add" action="book/showAdd">
        <input type="submit" id="addButton" value="Add"/>
    </form>
</div>
</body>
</html>