<script type="text/javascript">
	$(function(){

		// 企业融资申请
		$("#a_submit").click(function(){
			if ($("#loanFrm").valid() == false) {
				return;
			}
			$("#a_submit").unbind("click");
			$("#a_submit").removeClass("btn_primary").addClass("btn_disabled").text("processing");
			$("#loanFrm").submit();
		});
		
	});
</script>