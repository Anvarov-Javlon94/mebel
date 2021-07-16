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
<h2>${error?if_exists}</h2>
<h2>Данные заказчика</h2>
<form action="/updateOrder" method="post" id="myForm">
    <label for="name">Name</label>
    <input type="text" name="name" required>
    <label for="address">Address</label>
    <input type="text" name="address" required>
    <label for="city">City</label>
    <input type="text" name="city" required>
    <label for="phone">Phone +998</label>
    <input type="text" name="phone" pattern="\d*" placeholder="94123456" required>
    <input type="hidden" name="id" value="${order_id}">
    <input type="hidden" name="_csrf" value="${_csrf.token}">
</form>
<div>
    <h1>Ваш Заказ</h1>
</div>
<table>
    <thead>
    <tr>
        <th>Название товара</th>
        <th>Оригинальная цена товара</th>
        <th>Скидка на продукт %</th>
        <th>Цена товара после скидки</th>
        <th>Количество для покупки</th>
        <th>Обшая сумма товара со скидкой</th>
    </tr>
    </thead>
    <tbody>
    <#list order_details?if_exists as od>
    <tr>
        <td><#if od.getProduct().getName()??>${od.getProduct().getName()}</#if></td>
        <td><#if od.getProduct().getPrice()??>${od.getProduct().getPrice()}</#if></td>
        <td>

            <#if od.getProduct().getDiscount()??>${od.getProduct().getDiscount()}
            <#else>
               0
            </#if>
            %</td>
        <td>
            <#if od.getProduct().getAfter_discount()??>
                ${od.getProduct().getAfter_discount()}
                <#else>
                ${od.getProduct().getPrice()}
            </#if>
        </td>
        <td><#if od.getQuantity()??>${od.getQuantity()}</#if></td>
        <td><b>
            <#if od.getProduct().getAfter_discount()??>
                ${od.getProduct().getAfter_discount() * od.getQuantity()}
            <#else >
                ${od.getProduct().getPrice() * od.getQuantity()}
            </#if></b> сум
        </td>
    </tr>
    </#list>
    </tbody>
    <tfoot>
    <tr>
        <td>Обшая сумма корзины</td>
        <td></td>
        <td></td>
        <td></td>
        <td></td>
        <td><b>${total_price}</b> сум</td>
    </tr>
    </tfoot>
</table>
<div>

</div>
<input type="submit" form="myForm" value="отправить">
</body>
</html>