<div class="dn" id="description_dialog"></div>
<div class="dn" id="investConfirm_dialog"></div>    
    <script>
    	$("#trueorder_view").click(function(){
    		//投资的前台check
    	<#--
                                 // 业务处理 错误信息   开发可以去掉此内容
                                 
                                if	                            显示内容
                                1.没输入金额，输入的金额合法	    预期收益
                                2.输入金额小于起投金额	            投资金额必须大于200元
                                3.输入金额大于最大可投	            投资金额不能大于400000元
                                4.输入金额不是递增金额的整数倍	    投资金额必须为100的整数倍
                                -->
            var investAmount=$("#investAmount").val();
            
    		<#-- 最小投资金额check
    		var minAmount=${(loanDto.projectInfoDto.loanStartShare)!100};
    		var maxAmount=${(loanDto.projectInfoDto.currentSurplusShare)!};
    		
    		if(investAmount==""){
    			investAmount=0;
    		}
    		if(parseInt(investAmount) <	parseInt(minAmount)){
    			$("div#err").removeClass("cart-expecte-earnings").removeClass("cart-errormsg");
				$("div#err").addClass("cart-errormsg");
    			$("div#err").html("投资金额必须大于等于"+minAmount+"dollar");
    			$("div#err").show();
    			return false;
    		}
    		if(parseInt(investAmount) >	parseInt(maxAmount)){
    			$("div#err").removeClass("cart-expecte-earnings").removeClass("cart-errormsg");
				$("div#err").addClass("cart-errormsg");
    			$("div#err").html("投资金额必须小于"+maxAmount+"dollar");
    			$("div#err").show();
    			return false;
    		}
    		if(parseInt(investAmount) %100 > 0){
    			$("div#err").removeClass("cart-expecte-earnings").removeClass("cart-errormsg");
				$("div#err").addClass("cart-errormsg");
    			$("div#err").html("投资金额必须为100的整数倍");
    			$("div#err").show();
    			return false;
    		}
    		-->
			var params={"investAmount":investAmount,"loanCode":$("#loanCode").val()};
				$.ajax({
					type : "POST",
					url : "${taglibs.ctx}/invest/investVerify.htm",
					dataType : "json",
					data : params,
					beforeSend: function (xhr) {
									xhr.setRequestHeader("requst_ajax_type", "0");
								},
					success : function(json) {
					
						if(json.auth_result=="0"){
							location.href="${taglibs.ctx}/login.htm";
						}else{
							if(json.result){
								$("#investConfirm_dialog").html(json.html);
								$.fancybox.open($("#investConfirm_dialog"),{"title":"Invest Confirm"});
								/*
								$.dialog({
				                    content: json.html,
				                    title: "Invest Confirm"
	                			});
	                			*/
							}else{
								$("div#err").removeClass("cart-expecte-earnings").removeClass("cart-errormsg");
								$("div#err").addClass("cart-errormsg");
							   	$("div#err").html(json.html);
	    						$("div#err").show();
							}
	                	}
					}
				});
		});
		
		$("#investAmount").bind("copy cut paste", function (e) { // 通过空格连续添加复制、剪切、粘贴事件
	          if (window.clipboardData){
	               return !clipboardData.getData('text').match(/\D/);
	          }else{
	              event.preventDefault();
				}
	       });
		
		$("#investAmount").blur(function(){
			var investAmount=$("#investAmount").val();
			var z= /^[0-9]*$/;
    		if(investAmount==""|| !z.test(investAmount)){
    			$("#investAmount").val("")
    			return false;
    		}
			var params={"investAmount":investAmount,"loanCode":$("#loanCode").val()};
			$.ajax({
				type : "POST",
				url : "${taglibs.ctx}/invest/investCheck.htm",
				dataType : "json",
				data : params,
				success : function(json) {
					$("div#err").removeClass("cart-expecte-earnings").removeClass("cart-errormsg");
					if(json.result){
					  	$("div#err").addClass("cart-expecte-earnings");
					}else{
						$("div#err").addClass("cart-errormsg");
					}
				 	$("div#err").html(json.html);
                	$("div#err").show();
				}
			});
		});
		
		$("#project_description_view").click(function(){
			var params={"loanCode":$("#loanCode").val()};
			$.ajax({
				type : "POST",
				url : "${taglibs.ctx}/invest/instructions.htm",
				dataType : "json",
				data : params,
				success : function(json) {
				
					$("#description_dialog").html(json.html);
					$.fancybox.open($("#description_dialog"),{"title":"Instruction"});
					/*
						$.dialog({
		                    content: json.html,
		                    title: "Instruction"
            			});
            		*/
				}
			});
		});
    </script>