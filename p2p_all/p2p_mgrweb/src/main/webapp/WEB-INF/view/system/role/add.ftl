<form id="searchForm" action="${taglibs.allctx}/system/role/save.htm" method="post">
	<div class="form_wrp">
        <table class="table">
            <tbody>        
				<tr>
					<td class="size1of1">
						<label class="frm_label">Role name:</label>
						<div class="frm_controls">
							<@ctl.text name="role.roleName" required=true validate={"maxlength":"32","remote":"checkRoleName.htm"} fieldName="Role name"/>
						</div>
					</td>
				</tr>
				<tr>
					<td class="border_bottom_none size1of1">
						<label class="frm_label">Role status:</label>
						<div class="frm_controls">
							<@ctl.radiobuttonlist groupCode="status" name="role.roleState" dataType="BizCode" value="1"/>
						</div>
					</td>
				</tr>
				<tr class="last">
					<td class=" size1of1">
						<label class="frm_label">Role brief:</label>
						<div class="frm_controls">
							<@ctl.textarea  name="role.roleMem"  rows="1" cols="2" validate={"maxlength":"1000"}/>
						</div>
					</td>
				</tr>
            </tbody>
        </table>
        <div class="form_btn">
            <@ctl.submit class="btn btn_primary" text="save"/>
            <@ctl.button class="btn btn_primary" id="btnClose" text="cancel"/>
        </div>
    </div>    
</form>
<script type="text/javascript">
	$("#btnClose").click(function(){
		$.fancybox.close();
	})
</script>