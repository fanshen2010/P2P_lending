<#--
融资期限标签
<@bizTag.loanLimit limit unit />
-->
<#macro loanLimit
	limit="" unit=""
>
	<#if limit?? && limit!="">
		<#if unit?? && unit=="0">
		<em>${limit}</em>Days
		<#elseif unit?? && unit=="1">
		<em>${limit}</em>Months
		</#if>
	</#if>
</#macro>