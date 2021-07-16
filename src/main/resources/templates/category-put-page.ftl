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
<form action="/putCategory" method="post">
    <label for="name">Type Of Category</label>
    <input id="name" type="text" name="type_of_product" value="<#if category.getType_of_product()??>${category.getType_of_product()}</#if>" required>
    <button type="submit">Save Product</button>
    <input type="hidden" name="id" value="${category.getId()}">
    <input type="hidden" name="_csrf" value="${_csrf.token}">
</form>

</body>
</html>