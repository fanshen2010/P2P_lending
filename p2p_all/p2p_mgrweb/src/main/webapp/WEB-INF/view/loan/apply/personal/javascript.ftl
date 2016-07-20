       	<script type="text/javascript">
			$(function(){
					
					$("body").on("change","#premises",function(){
						var select = $("#premises").find("option:selected").val();
						var param ={"premises":select};
							$.ajaxLoadFtl({
					            type: "POST",
					            url: "${taglibs.ctx}/loan/apply/rent.htm",
					            dataType: "json",
					            data:  param,
					            error: function (data, transport) {
					            },
					            success: function (result) {
					                $("#premisesTr").html(result.html);
					            }
					      });
					});
					
					// 个人融资提交
					$("#a_submit").click(function(){
						if ($("#loanFrm").valid() == false) {
							return;
						}
						$("#a_submit").unbind("click");
						$("#a_submit").removeClass("btn_primary").addClass("btn_disabled").text("processing");
						//$("#a_submit").text("提交中");
						$("#loanFrm").submit();
					});
					
				});
		</script>