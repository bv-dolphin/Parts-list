<div>
            <#include "pagination.ftl">
</div>
<table class="table">
    <thead>
    <h1 align="center"><span class="about">Список компонентов</span></h1>
    <tr>
        <th scope="col">id</th>
        <th scope="col">Наименование</th>
        <th scope="col">Необходимость</th>
        <th scope="col">Количество</th>
    </tr>
    </thead>
    <#list partsList as part>
    <tbody>
    <tr>
        <th scope="row">${part.id}</th>
        <td>${part.name}</td>
        <td><#if part.need>Да<#else>Нет</#if></td>
        <td>${part.quantityInStock}</td>
    </tr>
    </tbody>
    </#list>
</table>