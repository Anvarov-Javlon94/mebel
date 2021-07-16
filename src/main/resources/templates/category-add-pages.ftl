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
<#if error??>
    <h1>
        ERROR
    </h1>
</#if>
<div>
    <form action="/addCategory" method="post">
        <label for="category">Название категории продукта или товара</label>
        <br>
            <input id="category" type="text" name="type_of_product" placeholder="Название категории" required>
        <br>
            <input type="submit" value="Добавить">
        <br>
        <input type="hidden" name="_csrf" value="${_csrf.token}">
    </form>
</div>
<br>
<hr>

</body>
</html>