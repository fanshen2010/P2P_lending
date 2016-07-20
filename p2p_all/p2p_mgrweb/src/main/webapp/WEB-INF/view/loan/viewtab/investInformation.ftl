<div class="form_wrp">    
    <table class="table table-striped table-hover form_wrp_padding" cellpadding="0" cellspacing="0">
        <thead class="thead">
            <tr>
                <th class=" tl">Investor</th>
                <th class=" tl">Invest time</th>
                <th class=" tl">Invest amount(dollar)</th>
                <#assign lstatus = loanDto.projectInfoDto.loanStatus >
                <#if lstatus != "04" && lstatus != "05" && lstatus != "07" && lstatus !="08" >
	                <th class=" tl">Repay amount(dollar)</th>
	                <th class=" tl">Repay principal(dollar)</th>
	                <th class=" tl">Repay interest(dollar)</th>
                </#if>
            </tr>
        </thead>
        <tbody class="tbody" id="">
        	<#list loanDto.investInformations as investInformation>
	            <tr>
	                <td class="">${(investInformation.login)!}</td>
	                <td class=""><@h.datetimef value=investInformation.investmentTime /></td>
	                <td class="">${(investInformation.investmentAmount)!}</td>
	                <#if lstatus != "04" && lstatus != "05" && lstatus != "07" && lstatus !="08" >
		                <td class="">${(investInformation.receivableAmount)!}</td>
		                <td class="">${(investInformation.receivablePrincipal)!}</td>
		                <td class="">${(investInformation.receivableInterest)!}</td>
	                </#if>
	            </tr>
            </#list>
        </tbody>
    </table>
</div>

