<#macro table path isEditForm isAddForm>
<#if !isEditForm && !isAddForm>
<div class="row" xmlns="http://www.w3.org/1999/html">
    <div class="col"><#include "pagination.ftl"></div>
    <div class="col">
        <form method="get">
            <select class="form-control" name="sorted">
                <option selected>Все компоненты</option>
                <option>Необходимые компоненты</option>
                <option>Опциональные компоненты</option>
            </select>
    </div>
    <div class="col">
        <button type="submit" class="btn btn-light">Фильтровать</button>
        </form>
    </div>
    <div class="col"></div>

    <div class="col"><a href="/parts_list/add_part" class="btn btn-outline-info">Добавить компонент</a></div>
</div>

</#if>
<table class="table" id="tableParts">
    <thead>
    <#if !isEditForm && !isAddForm>
        <h1 align="center"><span class="about">Список компонентов</span></h1>
    <#else>
    <form class="form-inline">
        <div class="form-group">
            <a href="/parts_list" class="btn btn-outline-secondary">Hазад</a>
        </div>
        <div class="form-group">
            <#if isEditForm>
            <h3 align="center"><span class="about">Редактирование компонента</span></h3>
            </#if>
            <#if isAddForm>
            <h3 align="center"><span class="about">Добавление компонента</span></h3>
            </#if>
        </div>
    </form>
    </#if>
    <tr>
        <th scope="col">id</th>
        <th scope="col">Наименование</th>
        <th scope="col">Необходимость</th>
        <th scope="col">Количество</th>
    </tr>
    </thead>
    <tbody>
    <#if !isEditForm && !isAddForm>
    <#list partsList as part>
    <tr class="box-shadow-hover" onclick="window.location.href='/parts_list/edition/${part.id}'">
        <th scope="row">${part.id}</th>
        <td>${part.name}</td>
        <td><#if part.need>Да<#else>Нет</#if></td>
        <td>${part.quantityInStock}</td>
    </tr>
    </#list>
    <#else>
    <div class="container">
        <div class="row">
            <div class="col-8">
    <form method="post">
      <tr>
          <th scope="row"><#if isEditForm>${part.id}<#else>#</#if></th>

          <td><div class="form-group">
              <input type="text" name="name" class="form-control ${(partIsError??)?string('is-invalid', ' ')}"
                     <#if part??> value="${part.name}" <#else> placeholder="Введите название:"</#if> required/>
              <#if partIsAdded??>
                            <div class="invalid-feedback d-block">
                                ${partIsAdded}
                            </div>
              </#if>
          </div>
          </td>

          <div class="form-group row">
          <td>
              <select class="form-control ${(neededError??)?string('is-invalid', ' ')}" name="needed">
                  <#if isEditForm>
                         <#if part.need><option selected>Да</option>
                          <option>Нет</option>
                         <#else> <option selected>Нет</option>
                          <option>Да</option> </#if>
                              <#else>
                              <option selected></option>
                          <option>Да</option>
                          <option>Нет</option>
                  </#if>
              </select>
               <#if neededError??>
                            <div class="invalid-feedback d-block">
                                ${neededError}
                            </div>
               </#if>
          </td>
          </div>

          <div class="form-group">
          <td>
              <input type="number" name="quantityInStock" class="form-control ${(quantityInStockError??)?string('is-invalid', ' ')}"
                     <#if part??>value="${part.quantityInStock}"<#else> placeholder="Введите количество"</#if> required/>
                <#if quantityInStockError??>
                            <div class="invalid-feedback d-block">
                                ${quantityInStockError}
                            </div>
                </#if>
          </td>
          </div>

          <div class="form-group">
          <td>
              <button type="submit" class="btn btn-outline-success">
                  <#if isEditForm>Сохранить<#else>Добавить</#if></button>
              <#if isEditForm><button type="submit" form="delete" class="btn btn-outline-danger" name="deleteBook">Удалить</button></#if>
          </td>
          </div>
      </tr>
    </form>
        <#if isEditForm><form action="/parts_list/delete/${part.id}" method="post" id="delete"></form></#if>
            </div>
        </div>
    </div>
    </#if>
    </tbody>
</table>
</#macro>