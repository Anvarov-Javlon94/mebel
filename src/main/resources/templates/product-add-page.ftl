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
<div>
    <#if error??>
        <h1>
            ERROR
        </h1>
    </#if>
</div>
<form action="/addProduct" method="post" enctype="multipart/form-data">
    <input type="text" name="name" placeholder="product name" required>
    <input type="text" name="product_availability" placeholder="product availability">
    <textarea name="product_character" id="product_character" cols="30" rows="10" required placeholder="Введите характеристику"></textarea>
    <textarea name="product_description" id="product_description" cols="30" rows="10" required placeholder="Введите коментарии"></textarea>
    <input type="number" min="1" max="100000000" name="price" placeholder="price" required>
    <select name="category_id" id="category">
        <#list categories?if_exists as category>
        <option value="${category.getId()}">${category.getType_of_product()}</option>
        </#list>
    </select>
    <input type="file" name="image" required>
    <button type="submit">Save Product</button>
    <input type="hidden" name="_csrf" value="${_csrf.token}">
</form>
</body>
</html>