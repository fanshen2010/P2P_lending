<#macro level value=value>
    <#if value>
        <#if value=1>
        1st level
        <#elseif value=2>
       	second level
        <#elseif value=3>
       	third level
        </#if>
    </#if>
</#macro>