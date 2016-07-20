 <form action="" name="" id="addBalanceForm" method="post">
	<div class="alter-loginpwd-pop">
		<div class="apply-finance-form">
        <div class="form-group">
            <label class="frm-label"><span class="need">*</span>Balance:</label>
            <span class="frm-controls">
            	<input class="input-text" type="text" id="balance" name="balance" data-rule-required="true" placeholder="balance" data-field_name="balance" />
            </span>
        </div>
        <div class="form-group form-li-password">
            <label class="frm-label"><span class="need">*</span>Password:</label>
            <span class="frm-controls fpw-newpw">
                <input class="input-text" type="password" id="password" name="password" data-rule-required="true" data-rule-rangelength="6,18" placeholder="Password" data-field_name="Password">
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
	if($("#addBalanceForm").valid()){
		$.ajax({
	        type: "POST",
	        url: "addBalance.htm",
	        async:false,
	        data: $("#addBalanceForm").serialize(),
	        error: function (data, transport) {
	        	alert("Error");
	        },
	        success: function (result) {
	        	if(result.msg=="success"){
	        		//修改密码(第二页)
		       		info({
						title:"Add Balance",
						msg: ["Add Balance"],
						btnName:"return to account",
						btnUrl: "${taglibs.allctx}/account/index.htm",
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