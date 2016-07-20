<!-- 手机号码修改 dialog -->
<form action="" name="" id="alterPhoneForm" method="post">
    <div class="alter-phonenum-pop">
    	<#--
    	<#if updateCellphoneStep=="1">
    	<p class="text">为了您的安全，更换绑定的手机号时请先进行安全验证</p>
    	</#if>
    	-->
        <div class="form-group">
        	<#if updateCellphoneStep=="1">
	            <label class="frm-label">Original phone:</label>
	            <span class="frm-controls">
		                <span class="frm-val">${(originalPhoneLock)!}</span>
		                <input class="input-text" type="hidden" id="updatePhone" name="updatePhone" value="${(originalPhone)!}">
	            </span>
            <#elseif updateCellphoneStep=="2">
            	<label class="frm-label">New phone:</label>
	            <span class="frm-controls">
	        		<input class="input-text" type="text" id="updatePhone" name="updatePhone" data-rule-required="true" data-rule-cellphone="true" data-rule-remote="checkCellphoneAjax.htm" data-field_name="phone number" data-remote_msg="phone number is locked" >
	            </span>
        	</#if>
        </div>
        <div class="form-group">
            <label class="frm-label">Captcha:</label>
            <span class="frm-controls frm-verifycode">
            	<#if updateCellphoneStep=="1">
                <@ctl.sendPhoneVerifyCode phoneNumberDivId="updatePhone" sendType="4" verifyCodeFlag=phoneVerifyCode/>
                <#elseif updateCellphoneStep=="2">
                <@ctl.sendPhoneVerifyCode phoneNumberDivId="updatePhone" sendType="5" countClean="true" />
                </#if>
            </span>
        </div>
        <div class="form-group">
            <label class="frm-label">&nbsp;</label>
            <span class="frm-controls submit-btn">
                <#if updateCellphoneStep=="1">
                	<button type="button" class="btn btn-primary" id="cellphone_ok">Submit</button>
                <#elseif updateCellphoneStep=="2">
                	<button type="button" class="btn btn-primary" id="cellphone_submit">Submit</button>
                </#if>
                </button>
            </span>
        </div>
    </div>
</from>



<script type="text/javascript">
$("#cellphone_ok").on("click",function(){
	if($("#alterPhoneForm").valid()){
		$.ajax({
	        type: "POST",
	        url: "checkOriginalPhone.htm",
	        async:false,
	        data: $("#alterPhoneForm").serialize(),
	        error: function (data, transport) {
	        	alert("Error");
	        },
	        success: function (result) {
	            if(result.msg=="success"){
	            	//修改电话(第二页)
	        		$("#alter-phonenum").html(result.html);
	        		$.fancybox.open($("#alter-phonenum"),{"title":"Cellphone Set"});
	            }else{
	            	alert(result.msg);
	            }
	    	}
		});
	}
});

$("#cellphone_submit").on("click",function(){
	if($("#alterPhoneForm").valid()){
		$.ajax({
			type: "POST",
	        url: "updatePhone.htm",
	        async:false,
	        data: $("#alterPhoneForm").serialize(),
	        error: function (data, transport) {
	        	alert("Error");
	        },
	        success: function (result) { 
	        	 if(result.msg=="success"){
	        	 	//修改电话(第三页)
		        	var updatePhoneLock = result.updatePhoneLock;
		       		info({
						title:"Cellphone Set",
						msg: ["cellphone set success！","your cellphone is "+updatePhoneLock],
						btnName:"return to security",
						btnUrl: "${taglibs.allctx}/account/security/index.htm",
						showTelephone: true
					});
	        	 }else{
	        	 	alert(result.msg);
	        	 }       	
	    	}
		});
	}
});


</script>