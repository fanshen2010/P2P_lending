<#--
融资期限标签
<@bizTag.loanLimit limit unit />
-->
<#macro loanLimit
	limit="" unit=""
>
	<#if limit?? && limit!="">
		<#if unit?? && unit=="0">
		${limit} days
		<#elseif unit?? && unit=="1">
		${limit} months
		</#if>
	</#if>
</#macro>