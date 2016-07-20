<div class="form_wrp">    
    <table class="table table-striped table-hover form_wrp_padding" id="tabRepayDetailView" cellpadding="0" cellspacing="0">
        <thead class="thead">
            <tr>
                <th class="td_period tl">Period</th>
                <th class="td_date tl">Repay plan</th>
                <th class="td_date tl">Repay real</th>
                <th class=" tl">principal(dollar)</th>
                <th class=" tl">interest(dollar)</th>
                <th class=" tl">Repay plan amount(dollar)</th>
                <th class=" tl">Repay real amount(dollar)</th>
                <th class=" tl">Status</th>
                <th class=" tr"></th>
            </tr>
        </thead>
        <tbody class="tbody" id="">
        	<#list loanDto.repayDetailList as item>
            <tr <#if item.status=="3" || item.status=="4" || item.status=="1">class="success"</#if>>
                <td class="">${item.num}/${(loanDto.projectInfoDto.totalRepayNumber)!}</td>
                <td class=""><@h.datef value=item.repayPlanDate default="-"/></td>
                <td class=""><@h.datef value=item.repayRealDate default="-"/></td>
                <#if item.status=="1">
                <td class="">${item.receivedPrincipal}</td>
                <td class="">${item.receivedInterest}</td>
                <#else>
                <td class="">${item.receivablePrincipal}</td>
                <td class="">${item.receivableInterest}</td>
                </#if>
                <td class=""><@h.numf value=item.receivableSum /></td>
                <td class=""><@h.numf value=item.receivedSum /></td>
                <td class=""><@bizTag.enumValue enumName="RepaymentStatusEnum" key=item.status  /></td>
                <td class=" tr">
                    <@ctl.operateButton id="repayment_detail_btn" title="detail" text="detail" />
                    <input type="hidden" value=${item.num} />
                    <input type="hidden" id="loanCode" value=${item.loanCode} />
                </td>
            </tr>
            </#list>
        </tbody>
    </table>
</div>
<!-- 投资人回款详细 -->
<div class="popsize_l fancybox_pop" id="repayment_pop">
</div>

<script type="text/javascript">
	$(function(){
		$("#tabRepayDetailView tbody tr a").click(function(){
			$.ajax({
	            type: "POST",
	            url: "${taglibs.ctx}/loan/afterLoan/repayManage/repayDetailView.htm",
	            dataType: "json",
	            data: {"curNum":$(this).siblings(":hidden").val(),"curLoanCode":$("#loanCode").val()},
	            error: function (data, transport) {
	               
	            },
	            success: function (result) {
	            		$("#repayment_pop").html(result.html);
						$.fancybox.open($("#repayment_pop"),{"title":"Repay Detail","scrolling":"no","afterLoad":function(){
							this.inner.addClass("overflow-auto");
						}});
	            }
	        });
		});
	});

</script>
