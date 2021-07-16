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
<h1>Админ панель заказ(товар) заказчика</h1>
<h3>Заказчик</h3>
<h3>Идентификатор: ${order.getId()}</h3>
<h3>Имя: ${order.getName()}</h3>
<h3>Адресс: ${order.getAddress()}</h3>
<h3>Город: ${order.getCity()}</h3>
<h3>Телефон: ${order.getPhone()}</h3>
<h3>Дата заказа: ${order.getDate()}</h3>
<div>
    Product
</div>
<br>
<h1>Список товаров заказчика</h1>
<table>
    <thead>
    <tr>
        <th>Photo</th>
        <th>Название товара</th>
        <th>Оригинальная цена товара</th>
        <th>Скидка на товар %</th>
        <th>Цена после скидки</th>
        <th>Количество товара</th>
        <th>Обшая сумма товара</th>
    </tr>
    </thead>
    <tbody>
    <#list order_details?if_exists as od>
        <tr>
            <td>photo</td>
            <td><#if od.getProduct().getName()??>${od.getProduct().getName()}</#if></td>
            <td><#if od.getProduct().getPrice()??>${od.getProduct().getPrice()}</#if></td>
            <td>
                <#if od.getProduct().getDiscount()??>
                    ${od.getProduct().getDiscount()}
                <#else >
                    0
                </#if> %</td>
            <td>
                <#if od.getProduct().getAfter_discount()??>${od.getProduct().getAfter_discount()}
                <#else >
                    ${od.getProduct().getPrice()}
                </#if>
            </td>
            <td><#if od.getQuantity()??>${od.getQuantity()}</#if></td>
            <td><b><#if od.total_price??>${od.total_price}</#if></b> сум</td>
        </tr>
    </#list>
    </tbody>
    <tfoot>
    <tr>
        <td><b>Сумма заказа</b></td>
        <td> </td>
        <td> </td>
        <td> </td>
        <td> </td>
        <td> </td>
        <td><b><#if total_price??>${total_price}</#if></b> сум</td>
    </tr>
    </tfoot>
</table>

<div>
    <#if order.getStatus() == "SERVED">
        Заказ был уже обслужен
    <#elseif order.getStatus() == "NOT_SERVED">
        <form action="/changeStatusOfCartToServed">
            <input type="hidden" name="id" value="${order_id}">
            <input type="submit" value="Обслужить заказ">
        </form>
        <form action="/changeStatusOfCartToCanceledByAdmin">
            <input type="hidden" name="id" value="${order_id}">
            <input type="submit" value="Отменить заказ админ">
        </form>
        <form action="/changeStatusOfCartToCanceledByOrder">
            <input type="hidden" name="id" value="${order_id}">
            <input type="submit" value="Отменил заказ покупатель">
        </form>
        <#elseif order.getStatus() == "CANCELED_BY_ADMIN">
        Заказ был отклонён со стороны админа
        <#elseif order.getStatus() == "CANCELED_BY_ORDER">
        Заказ был отклонён со стороны покупателя
    </#if>


</div>

</body>
</html>