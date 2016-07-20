<#--
融资还款方式标签
<@bizTag.loanInterestManner loanInterestManner />
-->
<#macro loanInterestManner
	loanInterestManner=""
>
	<@getLoanInterestManner loanInterestManner=loanInterestManner  commonResultPath="${commonResultPath}" />
</#macro>