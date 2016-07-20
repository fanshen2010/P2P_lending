<#--
融资状态标签
<@bizTag.loanStatus statusCode loanAmount loanEndTime />
-->
<#macro handleStatus
	code isView=true data={}
>
	<#if code == "0">
		<#if isView>
			待处理
		<#else>
			<@ctl.operateButton linkType="order" data=data text="Edit" />
		</#if>
	<#elseif code == "1">
		<#if isView>
		processing
		<#else>
			<@ctl.operateButton linkType="order" data=data text="Edit" />
		</#if>
	<#elseif code == "2">
		<#if isView>
		处理完成
		<#else>
			<@ctl.operateButton linkType="view" data=data text="view" />
		</#if>
	</#if>
</#macro>