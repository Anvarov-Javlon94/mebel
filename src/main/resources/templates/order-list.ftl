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
    <div>
        Order
    </div>
    <#list orders as order>
    <#--    <h3>${order}</h3>-->
        <h3><#if order.getName()??>${order.getName()}</#if></h3>
        <h3>
            <#if order.getStatus()??>${order.getStatus()}</#if>
        </h3>
        <h3>Дата обработки заказа<#if order.getDate_of_served()??>${order.getDate_of_served()}</#if></h3>
        <form action="/getOrderById">
            <input type="hidden" name="id" value="<#if order.getId()??>${order.getId()}</#if>">
            <input type="submit" value="посмотреть">
        </form>
        <hr>
        <br>
        <br>
    </#list>
</div>
</body>
</html>