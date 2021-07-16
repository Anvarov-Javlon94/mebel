<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
</head>
<body>
<h1>LIST</h1>
<hr>
<br>
<div>
    <#if categories?exists>
        <#list categories as category>
            <h2>id: ${category.getId()}</h2>
            <h2>category type: ${category.getType_of_product()}</h2>
            <form action="/putCategoryById">
                <input type="hidden" name="id" value="${category.getId()}">
                <input type="submit" value="edit">
            </form>
            <hr>
        </#list>
    </#if>
</div>
</body>
</html>