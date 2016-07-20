 <!-- 修改登录密码 dialog -->
 <form action="" name="" id="alterPwdForm" method="post">
	<div class="alter-loginpwd-pop">
		<div class="apply-finance-form">
        <div class="form-group">
            <label class="frm-label"><span class="need">*</span>Original Password:</label>
            <span class="frm-controls">
            	<input class="input-text" type="password" id="oldpassword" name="oldpassword" data-rule-required="true" data-rule-rangelength="6,18" placeholder="Original Password" data-field_name="Original Password" />
            </span>
        </div>
        <div class="form-group form-li-password">
            <label class="frm-label"><span class="need">*</span>New Password:</label>
            <span class="frm-controls fpw-newpw">
                <input class="input-text" type="password" id="newpassword" name="newpassword" data-rule-required="true" data-rule-rangelength="6,18" placeholder="New Password" data-field_name="New Password">
            </span>
        </div>
        <div class="form-group">
            <label class="frm-label"><span class="need">*</span>Confirm Password:</label>
            <span class="frm-controls">
                <input class="input-text" type="password" id="repassword" name="repassword" data-rule-required="true" data-rule-rangelength="6,18" data-rule-equalTo="#newpassword" placeholder="Confirm Password" data-field_name="Confirm Password">
            </span>
        </div>
        <div class="form-group">
            <label class="frm-label">&nbsp; </label>
            <span class="frm-controls submit-btn">
                <button type="button" class="btn btn-primary" id="password_submit">Submit</button>
            </span>
        </div>
	</div>
	</div>
</form>
 
<script type="text/javascript">
$("#password_submit").on("click",function(){
	if($("#alterPwdForm").valid()){
		$.ajax({
	        type: "POST",
	        url: "updatePassword.htm",
	        async:false,
	        data: $("#alterPwdForm").serialize(),
	        error: function (data, transport) {
	        	alert("Error");
	        },
	        success: function (result) {
	        	if(result.msg=="success"){
	        		//修改密码(第二页)
		       		info({
						title:"Log in Password Reset",
						msg: ["Password Reset"],
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