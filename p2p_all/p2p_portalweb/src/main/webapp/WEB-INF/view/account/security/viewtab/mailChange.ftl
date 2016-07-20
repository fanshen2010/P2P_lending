<!-- 邮箱修改 dialog -->
<form action="" name="" id="alterMailForm" method="post">
    <#if updateMailStep=="1">
    <div id="amd-bd-step1" class="alter-mail-pop">
    <#elseif updateMailStep=="new">
    <div id="bmd-bd-step1" class="bind-mail-pop">
    </#if>
    	<#if updateMailStep=="1">
    	<#--
        <p class="text">为了您的安全，更换邮箱时请先进行安全验证</p>
        -->
        <div class="form-group">
            <label class="frm-label">Original email:</label>
            <span class="frm-controls">
                <input class="input-text" type="text" name="originalMail" data-rule-required="true" data-rule-email="true" data-rule-remote="checkOldMailAjax.htm" data-field_name="email" data-remote_msg="email is not valid"/>
            </span>
        </div>
        <div class="form-group">
            <label class="frm-label">New email:</label>
            <span class="frm-controls">
                <input class="input-text" type="text" name="updateMail" data-rule-required="true" data-rule-email="true" data-rule-remote="checkNewMailAjax.htm" data-field_name="email" data-remote_msg="email has been used"/>
            </span>
        </div>
        <#elseif updateMailStep=="new">
        <div class="form-group">
            <label class="frm-label">Email:</label>
            <span class="frm-controls">
                <input class="input-text" type="text" name="updateMail" data-rule-required="true" data-rule-email="true" data-rule-remote="checkNewMailAjax.htm" data-field_name="email" data-remote_msg="email has been used"/>
            </span>
        </div>
        </#if>
        <input type="hidden" name="updateMailStep" value="${(updateMailStep)!}"/>
        
        <div class="form-group vfc-code">
        	<span class="frm-label">Captcha:</span>
            <span class="frm-controls frm-verifycode">
            	<#-- name="verifyCode" 为固定值 -->
            	<input class="input-text" type="text" name="verifyCode" data-rule-required="true" data-rule-remote="${taglibs.allctx}/checkVerifyCodeAjax.htm" data-field_name="Captcha" data-remote_msg="Captcha Error"/>
                <span  class="code-img">
                	<@ctl.verifyCode />
                </span>
                <a id="changeImg" class="code-change" href="javascript:;" title="">refresh</a>
            </span>
        </div>
        
        <div class="form-group form-group-action">
            <label class="frm-label">&nbsp;</label>
            <span class="frm-controls submit-btn">
                <button type="button" class="btn btn-primary" id="mail_submit">Submit</button>
            </span>
        </div>
    </div>
</form>


<script type="text/javascript">

$("#mail_submit").on("click",function(){
	if($("#alterMailForm").valid()){
		$.ajax({
	        type: "POST",
	        url: "sendActivationMail.htm",
	        async:false,
	        data: $("#alterMailForm").serialize(),
	        error: function (data, transport) {
	        	alert("Error");Error      },
	        success: function (result) {
	        	if(result.msg=="success"){
	        		var updateMailLock = result.updateMailLock;       	
		        	//修改密码(第二页)
		       		info({
						title:"Email Set",
						msg: ["Email has been sent to "+updateMailLock+"！","Please click the link in email to set Email address！"],
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

$("#changeImg").click(function(){
	var timenow = new Date().getTime();
	$("#img_verify").attr("src", "${taglibs.allctx}" + "/verifyCode.htm?d="+timenow);
});

</script>