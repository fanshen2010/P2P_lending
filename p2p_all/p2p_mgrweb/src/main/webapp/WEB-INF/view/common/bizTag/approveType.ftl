<#--审批类型Tag
loanCode Loan code
loanStatus Loan status
curNum 当前还款期次
-->
<#macro getApproveType
	loanCode="" loanStatus="" curNum=""
>
<@approveType loanCode=loanCode loanStatus=loanStatus curNum=curNum  commonResultPath="${commonResultPath}"/>
</#macro>