<#import "parts/common.ftl" as c>
<#import "parts/table.ftl" as t>

<@c.page>
<div style="background-color: #ffffff; padding: 15px; margin-bottom: 10px; border-radius: 10px">
    <@t.table "/parts_list" false false></@t.table>
</div>
<div style="background-color: #ffffff; padding: 15px; margin-bottom: 10px; border-radius: 10px">
<table class="table">
    <thead>
    <tr>
        <th scope="col"></th>
        <th scope="col">Можно собрать</th>
        <th scope="col">${computerCompleteCount}</th>
        <th scope="col"></th>
        <th scope="col"></th>
        <th scope="col">компьютеров</th>
    </tr>
    </thead>
</table>
</div>
</@c.page>
