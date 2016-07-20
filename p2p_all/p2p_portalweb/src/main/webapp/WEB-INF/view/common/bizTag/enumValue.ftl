<#macro enumValue
	enumName="" key=""
>
<#local enumItem=getEnum(enumName, key)>
<#if enumItem != null>
${enumItem.getValue()}
</#if>

</#macro>