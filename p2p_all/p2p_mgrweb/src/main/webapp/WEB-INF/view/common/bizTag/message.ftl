<#macro message
	userId="" msgType="1" receiveType="1" 
>
	<#local messageCount=getMessage(userId, msgType,receiveType)>
		<#if messageCount??&& messageCount gt 0>
			<i class="icon_dot_notices">${messageCount}</i>
		<#else>
		
		</#if>
	
</#macro>