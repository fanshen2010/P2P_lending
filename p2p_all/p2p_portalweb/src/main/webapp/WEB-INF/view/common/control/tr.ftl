<#macro tr rowindex changeClass="last">
<#if rowindex % 2 == 1>
<tr class=${changeClass}>
<#else>
<tr>
</#if>
<#nested/>
</tr>
</#macro>
