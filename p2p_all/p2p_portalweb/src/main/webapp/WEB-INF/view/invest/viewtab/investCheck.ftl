<div class="invest-group-label">Interest rates:</div>
<div class="invest-group-control">
	<span class="expecte-earnings-num">$ <em><@h.numf value=invest.receivableInterest /></em></span>
    <a class="expecte-earnings-link dialog-link" id="payment_plan_view" href="javascript:void(0);" title="">RepayPlan</a>
</div>

<div class="dn" id="payment_plan_dialog"></div>

<script type="text/javascript">
	$("#payment_plan_view").on("click",function(){
		var investAmount=$("#investAmount").val();
		var params={"investAmount":investAmount,"loanCode":$("#loanCode").val()};
			$.ajax({
				type : "POST",
				url : "${taglibs.ctx}/invest/investPlan.htm",
				dataType : "json",
				data : params,
				success : function(json) {
					$("#payment_plan_dialog").html(json.html);
					$.fancybox.open($("#payment_plan_dialog"),{"title":"RepayPlan"});
					/*
					$.dialog({
	                    content: json.html,
	                    title: "RepayPlan"
        			});
        			*/
				}
			});
	});
</script>