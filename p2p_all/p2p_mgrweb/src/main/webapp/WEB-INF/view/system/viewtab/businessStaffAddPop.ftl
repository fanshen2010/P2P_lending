<form id="formAdd" method="POST">
        <table class="table" id="tab_partner_info_add" cellpadding="0" cellspacing="0">
            <tbody class="tbody" id="">
                    <tr>
                        <td class="table_right_border size1of2">
                            <label class="frm_label">User name:</label>
                            <div class="frm_controls">
                            	<@ctl.text id="txtUserName" name="pfmUser.userName" autocomplete=false required="true" validate={"maxlength":"20","remote":"userNameCheck.htm"} placeholder="max length is 20" fieldName="User name"/>
                            </div>
                        </td>
                        <td class=" size1of2">
                             <label class="frm_label">Password:</label>
                            <div class="frm_controls">
                            	<@ctl.password id="txtPassword" name="pfmUser.password" required="true" validate={"rangelength":"[6,18]"} fieldName="Password"/>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td class="table_right_border size1of2" colspan="2">
                             <label class="frm_label">Name:</label>
                            <div class="frm_controls">
                            	<@ctl.text id="name" name="pfmUser.realName" required="true" validate={"maxlength":"20"} placeholder="max length is 20" fieldName="Name"/>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td class="table_right_border size1of2">
                            <label class="frm_label">Department:</label>
                            <div class="frm_controls">
                                <@ctl.dropdownlist id="ddlDepartCd" listData=departMap name="pfmUser.departCd"  hasChoice=true />
                            </div>
                        </td>
                        <td class=" size1of2">
                            <label class="frm_label">Position:</label>
                            <div class="frm_controls">
                                <@ctl.dropdownlist id="ddlPostCd" listData=postMap name="pfmUser.postCd"  hasChoice=true />
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td class="table_right_border size1of2">
                            <label class="frm_label">Contact phone:</label>
                            <div class="frm_controls">
                            	<@ctl.text id="contactPhone" name="pfmUser.contactPhone" validate={"telephoneOrCellphone":"true"} required="true" fieldName="Contact phone"/>
                            </div>
                        </td>
                        <td class=" size1of2">
                             <label class="frm_label">Email:</label>
                             <div class="frm_controls">
                             	<@ctl.text id="emailAddress" name="pfmUser.emailAddress" validate={"email":"true","maxlength":"64"} />
                             </div>
                        </td>
                    </tr>
                    <tr class="last">
                        <td class="border_bottom_none size1of2" colspan="2">
                            <label class="frm_label">Status:</label>
                            <div class="frm_controls">
                            	<@ctl.radiobuttonlist id="active"dataType="BizCode" spanClass="frm_radio_box uniformjs supplement_info_radio" groupCode="status" name="pfmUser.active" value="1" />
                                <span class="required-star">*</span>
                            </div>
                        </td>
                    </tr>
            </tbody>
        </table>
        <div class="form_btn" id="prepayment_apply">
        	<@ctl.button id="btnOkSubmitAdd" text="save" />
        	<@ctl.button id="btnCancel" class="btn" text="cancel" />
        </div>
    </form>
<script type="text/javascript">
	$(function(){ 
		$("#txtUserName").val("");
	}); 
	$("#btnCancel").click(function(){
		$.fancybox.close();
	});
	$("#btnOkSubmitAdd").click(function(){
		if($("#formAdd").valid()){
			$.ajax({
	            type: "POST",
	            url: "okSubmitAdd.htm",
	            dataType: "json",
	            data: $("form#formAdd").serialize(),
	            error: function (data, transport) {
						alert("save error");			               
	            },
	            success: function (result) {
	            
	          	 	if(result.retMes=="1") {
	          	 		alert("save success");
		            	$("#searchForm").submit();
		            	$.fancybox.close();
	          	 	} else if(result.retMes=="2") {
	          	 		alert("save error，user exist。");
	          	 	} else {
	          	 		alert("save error");
	          	 	}
	            }
	        });
		}
	});
</script>