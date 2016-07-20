<span class="${spanClass!}">
    <select class="${class!}" ${data!} id="${id!}" name="${name!}" <#rt>
    <#include "${taglibs.ftlctx}/common/control/validate_attributes.ftl"><#rt>
    <#if fieldName?? && fieldName!=""> data-field_name="${fieldName}" </#if><#rt>
    >
    	<#if hasAll?? && hasAll><option value="">All</option></#if>
    	<#if hasChoice?? && hasChoice><option value="">${textChoice!}</option></#if>
    	<#if hasNone?? && hasNone><option value="">无</option></#if>
    	<#if listData?? && listData?size gt 0>
    	<#list listData?keys as key>
			<option value="${key}" <#if value?? && value==key>selected=selected</#if>>${listData[key]}</option>
		</#list>
    	<#elseif rstlist?? && rstlist?size gt 0>
    	<#list rstlist?keys as key>
			<option value="${key}" <#if value?? && value==key>selected=selected</#if>>${rstlist[key]}</option>
		</#list>
    	</#if>
    </select>
</span>
<#include "${taglibs.ftlctx}/common/control/required_star.ftl">