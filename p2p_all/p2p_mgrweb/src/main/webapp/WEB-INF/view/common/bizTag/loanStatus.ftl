<#--
融资状态标签
<@bizTag.loanStatus statusCode loanAmount loanEndTime />
-->
<#macro loanStatus
	statusCode="" loanAmount="" loanEndTime="" investedAmount="" activeName="" bizType="0" loanId=""
>
	<@getLoanStatusName statusCode=statusCode loanAmount=loanAmount investedAmount=investedAmount loanEndTime=loanEndTime activeName=activeName commonResultPath="${commonResultPath}" bizType=bizType loanId=loanId />
</#macro>