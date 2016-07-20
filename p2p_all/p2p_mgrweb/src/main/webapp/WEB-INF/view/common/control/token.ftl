<#macro token>
	<#if Session["token"]?exists>
	<#local token = Session["token"] />
	   <input type="hidden" name="token" value="${token}"/>
	</#if>
</#macro>
