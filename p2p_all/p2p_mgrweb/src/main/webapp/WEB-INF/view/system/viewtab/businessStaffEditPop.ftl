<form id="formEdit" method="POST">
    <table class="table" id="tab_partner_info" cellpadding="0" cellspacing="0">
        <tbody class="tbody" id="">
                    <tr>
                        <td class="table_right_border size1of2">
                            <label class="frm_label">User name:</label>
                            <div class="frm_controls">
                            	<span class="frm_val">${pfmUser.userName}</span>
                            	<span class="required-star">*</span>
                            	<input type="hidden" name="pfmUser.id" value="${pfmUser.id}" />
                            	<input type="hidden" name="pfmUser.userName" value="${pfmUser.userName}" />
                            </div>
                        </td>
                        <td class=" size1of2">
                             <label class="frm_label">Password:</label>
                            <div class="frm_controls">
                            	<@ctl.password id="password" name="pfmUser.password" validate={"rangelength":"[6,18]"} />
                            	<#-- <p class="mark">如果不修改，请保持为空。</p>-->
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td class="table_right_border size1of2" colspan="2">
                             <label class="frm_label">Name:</label>
                            <div class="frm_controls">
                            	<@ctl.text id="name" name="pfmUser.realName" value="${pfmUser.realName}" required="true" validate={"maxlength":"10"} placeholder="max length is 10" fieldName="Name"/>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td class="table_right_border size1of2">
                            <label class="frm_label">Department:</label>
                            <div class="frm_controls">
                          	 	<@ctl.dropdownlist listData=departMap hasChoice=true name="pfmUser.departCd"  value="${pfmUser.departCd}" />
                            </div>
                        </td>
                        <td class=" size1of2">
                            <label class="frm_label">Position:</label>
                            <div class="frm_controls">
                            	<@ctl.dropdownlist listData=postMap hasChoice=true name="pfmUser.postCd"  value="${pfmUser.postCd}" />
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td class="table_right_border size1of2">
                            <label class="frm_label">Contact phone:</label>
                            <div class="frm_controls">
                            	<@ctl.text id="contactPhone" name="pfmUser.contactPhone" value="${pfmUser.contactPhone}" validate={"telephoneOrCellphone":"true"} required="true" fieldName="Contact phone"/>
                            </div>
                        </td>
                        <td class=" size1of2">
                             <label class="frm_label">Email:</label>
                             <div class="frm_controls">
                             	<@ctl.text id="emailAddress" name="pfmUser.emailAddress" value="${pfmUser.emailAddress}" validate={"email":"true","maxlength":"64"} />
                             </div>
                        </td>
                    </tr>
                    <tr class="last">
                          <td class="border_bottom_none size1of2" colspan="2">
                            <label class="frm_label">Status:</label>
                            <div class="frm_controls">
                            	<@ctl.radiobuttonlist id="active" spanClass="frm_radio_box uniformjs supplement_info_radio" dataType="BizCode" groupCode="status" name="pfmUser.active" value="${pfmUser.active}" />
                                <span class="required-star">*</span>
                            </div>
                        </td>
                    </tr>
        </tbody>
    </table>
    <div class="form_btn" id="prepayment_apply">
    	 <@ctl.button id="btnOkSubmitEdit" text="save" />
         <@ctl.button id="btnCancelPop"  class="btn" text="cancel" />
    </div>
</form>
<script type="text/javascript">
	$("#btnOkSubmitEdit").click(function(){
	
			if($("#formEdit").valid()){
				$.ajax({
			            type: "POST",
			            url: "okSubmitEdit.htm",
			            dataType: "json",
			            data: $("form#formEdit").serialize(),
			            error: function (data, transport) {
			               alert("error");
			            },
			            success: function (result) {
			            	if(result.retMes=="1") {
			            		alert("success");
			            		$("#searchForm").submit();
			            	} else {
			            		alert("error");
			            	}
			            }
			        });
			        
			        $.fancybox.close();
			}
		});
		$("#btnCancelPop").click(function(){
			$.fancybox.close();
		});
	</script>