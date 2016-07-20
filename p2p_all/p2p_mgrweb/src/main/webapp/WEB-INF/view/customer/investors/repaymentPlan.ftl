<div class="form_wrp" id="repaymentPlan"> 
	<table class="table table-striped table-hover form_wrp_padding" cellpadding="0" cellspacing="0">
	    <thead class="thead">
	        <tr>
	            <th class="	tl">Repay plan date</th>
	            <th class=" tl">Repaid amount（dollar）</th>
	            <th class=" tl">Repaying amount（dollar）</th>
	            <th class=" tl">Repaying total amount(dollar)</th>
	            <th class=" tl"></th>
	        </tr>
	    </thead>
	    <tbody class="tbody" id="">
	    	<#if detail.repaymentPlan?has_content>
	    	<#list detail.repaymentPlan as plan>
	            <tr class="">
	                <td class="">${plan.recievePlanDate?string("yyyy-MM")}</td>
	                <td class=""><@h.numf value=plan.recievedPayment /></td>
	                <td class=""><@h.numf value=plan.collectPayment /></td>
	                <td class=""><@h.numf value=plan.recievedPayment+plan.collectPayment /></td>
	                <td>
	                <@ctl.operateButton  title="detail" class="operate_icon audit" text="detail" data={"yearmonth":"${plan.recievePlanDate?string('yyyyMM')}","userid":"${id}"} />
	                </td>
	            </tr>
	        </#list>
	        <#else>
			  <tr><td colspan="5">No data</td></tr>
			</#if>
	    </tbody>
	</table>
</div>