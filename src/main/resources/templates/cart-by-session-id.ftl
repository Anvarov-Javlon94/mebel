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
<h1>Эти заказы принадлежат сессии: <#if order??>${order.getSessionId()}</#if></h1>

<table>
    <thead>
    <tr>
        <th>Фото</th>
        <th>Название :</th>
        <th>Цена :</th>
        <th>Скидка %</th>
        <th>Цена после скидки</th>
        <th>Количество</th>
        <th>Обшая сумма</th>
        <th>Удалить</th>
    </tr>
    </thead>
    <tbody>
    <#list order_details?if_exists as od>
        <tr>
            <td>photo</td>
            <td><#if od.getProduct().getName()??>${od.getProduct().getName()}</#if></td>
            <td><#if od.getProduct().getPrice()??>${od.getProduct().getPrice()}</#if></td>
            <td>
                <#if od.getProduct().getDiscount()??>${od.getProduct().getDiscount()}

                </#if>

                %</td>
            <td>
                <#if od.getProduct().getAfter_discount()??>${od.getProduct().getAfter_discount()}
                <#else >
                    ${od.getProduct().getPrice()}
                </#if> сум
            </td>
            <td>
                <form action="/putQuantity" method="post">
                    <input type="number" min="1" max="10" name="quantity" value="<#if od.getQuantity()??>${od.getQuantity()}</#if>">
                    <input type="hidden" name="id" value="<#if od.getId()??>${od.getId()}</#if>">
                    <input type="hidden" name="_csrf" value="${_csrf.token}">
                    <input type="submit" value="отправить">
                </form>
            </td>

            <td>
                <#if od.getTotal_price()??></#if>${od.getTotal_price()}
            </td>
            <td>
                <form action="/deleteProductFromCart">
                    <input type="hidden" name="id" value="<#if od.getProduct().getId()??>${od.getProduct().getId()}</#if>">
                    <input type="submit" value="удалить">
                </form>
            </td>
        </tr>
    </#list>
    </tbody>
</table>
<br>
<div>
    <h1>вы набрали на сумму: <#if total_price??> ${total_price}</#if></h1>
</div>
<br>
<div>
    <form action="/checkout">
        <input type="hidden" name="id" value="<#if order??>${order.getId()}</#if>">
        <#if order??>
            <input type="submit" value="оформить заказ">
            <#else>
            Нет заказа для оформление
        </#if>

    </form>
</div>
</body>
</html>