	<form action="${taglibs.ctx}/invest/gopay.htm"   method="post" target="_blank">
        <!-- 投资确认 Content -->
        <div class="trueorder-info clearfix">
        	<#--
	    	<div class="trueorder-info-hd">
	    		<#if ciccAccount??>
	        	<span class="ti-hd-prompt">您本次投资所得收益将直接汇入您注册的中金账户:${(ciccAccount.phoneNumber)!}</span>
	        	</#if>
	        </div>
            -->
            <div class="trueorder-info-bd">
            	<ul class="trueorder-list">
                	<li class="head">
                    	<div class="col trueorder-list-name">loanName</div>
                        <div class="col trueorder-list-total">investAmount</div>
                        <div class="col trueorder-list-yields">interest</div>
                    </li>
                    <li>
                    	<div class="col trueorder-list-name"><a class="ci-td1" href="${taglibs.ctx}/invest/investDetail.htm?loanCode=${invest.loanCode}" target="_blank" title="">${invest.loanName}</a></div>
                        <div class="col trueorder-list-total">$<@h.numf value=invest.investmentAmount /></div>
                        <div class="col trueorder-list-yields">$<@h.numf value=invest.receivableInterest /></div>
                    </li>
                </ul>
            </div>
            <p class="trueorder-paid">payment:$<em><@h.numf value=invest.investmentAmount /></em></p>
            <a class="btn btn-primary payfor" id="payfor" href="javascript:;" title="">pay</a>
            <a class="payfor-back" href="javascript:;" title="">cancel</a>
            <div class="clear"></div>
        </div>
        <input  type="hidden"   name="loanCode" value="${(invest.loanCode)!}" />
        <input  type="hidden"   name="investAmount" value="${(invest.investmentAmount)!}" />
	</form> 
	
	<div class="dn" id="investPay_dialog"></div>
     <script type="text/javascript">
	$("a.payfor").click(function(){
		$("div.dialog").hide();
		$(this).parents("form").submit();
		info({
			title:"Payment Success",
			msg: ["Payment Success"],
			btnName:"return",
			btnUrl: "${taglibs.ctx}/invest/investDetail.htm?loanCode=${(invest.loanCode)!}",
			showTelephone: true
		});
		
		/*
		$.ajax({
				type : "POST",
				url : "${taglibs.ctx}/invest/paytrue.htm",
				dataType : "json",
				data : {},
				success : function(json) {
					$("div.dialog").hide();
					$("#investPay_dialog").html(json.html);
					$.fancybox.open($("#investPay_dialog"),{"title":"Invest payment"});
					
				}
			});
		*/
		
	});     
	</script>
	