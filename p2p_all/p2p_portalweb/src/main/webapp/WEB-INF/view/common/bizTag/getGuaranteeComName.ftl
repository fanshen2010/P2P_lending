<#--
根据身份证号码获取年龄
<@bizTag.guaranteeComName guaranteeComCode  />
-->
<#macro guaranteeComName
	guaranteeComCode="" 
>
	<@getGuaranteeComName guaranteeComCode=guaranteeComCode commonResultPath="${commonResultPath}" />
</#macro>