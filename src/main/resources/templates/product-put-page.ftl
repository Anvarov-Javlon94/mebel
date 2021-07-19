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
<form action="/putProduct" method="post">
    <label for="name">name</label>
    <input id="name" type="text" name="name" value="<#if product.getName()??>${product.getName()}</#if>">
    <label for="product_availability">availability</label>
    <select name="product_availability" id="">
        <#if product.getProduct_availability()>
            <option selected value="true">true</option>
            <option value="false">false</option>
        <#else>
            <option selected value="false">false</option>
            <option value="true">true</option>
        </#if>
    </select>
    <label for="character">character</label>
    <textarea name="character" id="character" cols="30" rows="10"><#if product.getProduct_character()??>${product.getProduct_character()}</#if></textarea>
    <label for="description">description</label>
    <textarea name="description" id="description" cols="30" rows="10"><#if product.getProduct_description()??>${product.getProduct_description()}</#if></textarea>
    <label for="price">Old price <#if product.getPrice()??>${product.getPrice()}</#if></label>
    <input id="price" type="text"  maxlength="9" pattern="\d*" name="p_price" value="<#if product.getPrice()??>${product.getPrice()}</#if>">
    <input type="number" name="discount" value="<#if product.getDiscount()??>${product.getDiscount()}</#if>" placeholder="Процент % скидки">
    <label for="category">category</label>
    <p><#if product.getCategory()??>${product.getCategory().getType_of_product()}</#if></p>
    <select name="category_id" id="category">
        <#list categories?if_exists as category>
            <option value="${category.getId()}">${category.getType_of_product()}</option>
        </#list>
    </select>
    <button type="submit">Save Product</button>
    <input type="hidden" name="id" value="${product.getId()}">
    <input type="hidden" name="_csrf" value="${_csrf.token}">
</form>
</body>
</html>