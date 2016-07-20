<#--
根据身份证号码获取年龄
<@bizTag.countAge identity  />
-->
<#macro countAge
	identity="" 
>
	<@countAgeByIdentity identity=identity commonResultPath="${commonResultPath}" />
</#macro>