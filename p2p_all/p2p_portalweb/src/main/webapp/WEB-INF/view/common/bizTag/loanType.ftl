<#macro loanType
	type="" eFtl="" pFtl="" 
>
<#if type??>
	<#if eFtl?? && eFtl!="" && pFtl?? && pFtl!="">
		<#if type=="0">
		<#include "${taglibs.ftlctx}${pFtl}" />
		<#elseif type=="1">
		<#include "${taglibs.ftlctx}${eFtl}" />
		</#if>
	<#else>
		<#if type=="0">
		个人
		<#elseif type=="1">
		企业
		</#if>
	</#if>
</#if>
</#macro>