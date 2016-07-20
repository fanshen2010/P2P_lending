<form id="searchForm" action="${taglibs.allctx}/system/role/update.htm" method="post">
	<div class="form_wrp">
        <table class="table">
            <tbody>        
				<tr>
					<td class="size1of1">
						<label class="frm_label">Role name:</label>
						<div class="frm_controls">
							<input type="hidden" id="id"  name="role.roleId" value="${role.roleId}" />
							<@ctl.text name="role.roleName"  value="${role.roleName}" required=true validate={"maxlength":"32","remote":"checkRoleName.htm?role.roleId=${role.roleId}"} fieldName="Role name"/>
						</div>
					</td>
				</tr>
				<tr>
					<td class="border_bottom_none size1of1">
						<label class="frm_label">Role status:</label>
						<div class="frm_controls">
							<@ctl.radiobuttonlist groupCode="status" name="role.roleState" dataType="BizCode" value="${role.roleState}"/>
						</div>
					</td>
				</tr>
				<tr class="last">
					<td class=" size1of1">
						<label class="frm_label">Role brief:</label>
						<div class="frm_controls">
							<@ctl.textarea  name="role.roleMem"  rows="1" cols="2" validate={"maxlength":"1000"} value="${role.roleMem}" validate={"maxlength":"100"}/>
						</div>
					</td>
				</tr>
            </tbody>
        </table>
        <div class="form_btn">
        	<@ctl.submit class="btn btn_primary" text="save"/>
            <@ctl.button class="btn btn_primary" text="cancel" id="cancel"/>
        </div>
    </div>    
</form>