<#import "parts/common.ftl" as c>
<#import "parts/table.ftl" as t>

<@c.page>
<div style="background-color: #ffffff; padding: 15px; margin-bottom: 10px; border-radius: 10px">
      <#if nameError??>
          <div class="alert alert-danger" role="alert">
              ${nameError}
          </div>
      </#if>
    <@t.table "/parts_list/edition/${part.id}" true false></@t.table>
</div>
</@c.page>