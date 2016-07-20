<!---放款原因 submit -->
<div class="popsize_l fancybox_pop" id="loan_pop">
	<form action="loan.htm" method="POST">
	<@ctl.token />
    	<div class="form_wrp handle_area">
        	<table class="table" cellpadding="0" cellspacing="0">
            	<tbody class="tbody" id="">
                    <tr class="last">
                        <td class="border_bottom_none size1of1">
                            <label class="frm_label">Effect Reason:</label>
                            <div class="frm_controls">
                            	<input  type="hidden"  id="loanCode" name="loanCode" value="${(loanDto.projectInfoDto.loanCode)!}" />
        						<input  type="hidden"  id="loanId" name="loanId" value="${(loanDto.id)!}" />
                                <@ctl.textarea id="loanReason" name="loanReason"   class="frm_textarea" required=true validate={"maxlength":"100"} fieldName="Effect Reason"/>
                            </div>	
                        </td>
                    </tr>
                </tbody>
            </table>
        </div>
	    <div class="form_wrp confirm_area  pt20">
	        <table class="table" cellpadding="0" cellspacing="0">
	            <tbody class="tbody" id="">
	                <tr class="last">
	                    <td class="border_bottom_none size1of1" colspan="2" style="border:0;">
	                        <label class="frm_label">Password:</label>
	                        <div class="frm_controls">
	                            <@ctl.password   name="passWord" required="true" validate={"remote":"${taglibs.ctx}/checkPassword.htm"} fieldName="password"/>
	                        </div>	
	                    </td>
	                </tr>
	            </tbody>
	        </table>
	    </div>
		<p class="form_btn">
	    	<@ctl.submit id="loan_submit"  text="confirm"/>
			<@ctl.button id="loan_close" class="btn" text="cancel"/>
		</p>
	</form>
</div>
<script type="text/javascript">
	$("#loan_close").click(function(){
		$.fancybox.close();
	});
</script>