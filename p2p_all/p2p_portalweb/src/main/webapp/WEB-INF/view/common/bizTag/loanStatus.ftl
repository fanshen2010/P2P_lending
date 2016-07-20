<#--
融资状态标签
<@bizTag.loanStatus statusCode loanAmount loanEndTime />
-->
<#macro loanStatus
	statusCode="" loanAmount="" loanEndTime="" investedAmount="" activeName=""
>
	<@getLoanStatusName statusCode=statusCode loanAmount=loanAmount investedAmount=investedAmount loanEndTime=loanEndTime activeName=activeName commonResultPath="${commonResultPath}" />
</#macro>