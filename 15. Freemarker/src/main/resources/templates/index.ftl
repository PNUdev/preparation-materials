<#include "header.ftl">
<#if name??>
    <#assign nameWithDot = name + ".">

    ${name}<br>
    ${nameWithDot}
</#if>

${name!"name"}<br>

<div class="container">
    <#list people as person>
        <div class="row bg-black">
            ${person.name}
            ${person.alive?string("Живий", "Вмер")}
        </div>
    </#list>
</div>

<#include "footer.ftl">