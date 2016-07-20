<#if listData?? && listData?size gt 0>
<#list listData?keys as key>
	<span class="${spanClass!}">
        <label class="${labelClass!}">
            <input type="radio"  id="${id!}" class="${class!}" name="${name!}" value="${key}" <#if value?? && value==key>checked</#if>> ${listData[key]}
        </label>
    </span>
</#list>
<#elseif rstlist?? && rstlist?size gt 0>
<#list rstlist?keys as key>
	<span class="${spanClass!}">
        <label class="${labelClass!}">
            <input type="radio"  id="${id!}" class="${class!}" name="${name!}" value="${key}" <#if value?? && value==key>checked</#if>> ${rstlist[key]}
        </label>
    </span>
</#list>
</#if>