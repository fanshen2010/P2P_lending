<#macro pageType type="" >
<#if type??>
		<#if type=="0">
		Page
		<#elseif type=="1">
		List
		<#elseif type="2">
		Link
		</#if>
</#if>
</#macro>